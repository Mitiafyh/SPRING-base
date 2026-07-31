package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.modele.Cheque;

public class ChequeDAO {

    private UtilDB utilDB;

    public UtilDB getUtilDB() {
        return utilDB;
    }

    public void setUtilDB(UtilDB utilDB) {
        this.utilDB = utilDB;
    }

    public Cheque getCheque(Connection conn, String num) throws Exception {
        Cheque c = new Cheque();
        String sql = "SELECT * FROM CHEQUE WHERE nomCompte = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, num);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    c.setNumeroCheque(rs.getString("numeroCheque"));
                    c.setNomCompte(rs.getString("nomCompte"));
                    c.setId(rs.getInt("id_cheque")); // si tu as id_cheque dans CHEQUE
                    c.setDaty(rs.getObject("daty", java.time.LocalDate.class));
                } else {
                    throw new Exception("Aucun cheque trouve pour : " + num);
                }
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

        return c;
    }

    public  Cheque getCheque(String num) throws Exception {
        Cheque cheque;
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            cheque = getCheque(conn, num);

        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return cheque;
    }

    public  List<Cheque> getAllCheque(Connection conn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Connexion null dans ChequeDAO !");
        }

        List<Cheque> all = new ArrayList<>();
        String sql = "SELECT * FROM CHEQUE AS CQ JOIN CHEQUESTATUS AS C ON C.id_cheque = CQ.id_cheque JOIN STATUS AS S ON C.id_status = S.id_status WHERE S.id_status < 4 GROUP BY CQ.id_cheque";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                all.add(new Cheque(
                        rs.getInt("id_cheque"),
                        rs.getString("nomCompte"),
                        rs.getString("numeroCheque"),
                        rs.getObject("daty", java.time.LocalDate.class)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // on laisse remonter l'erreur
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return all;
    }

    public  List<Cheque> getAllCheque() throws SQLException {
        List<Cheque> all = new ArrayList<>();
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            if (conn == null) {
                throw new SQLException("Impossible d'obtenir la connexion à la base de données !");
            }
            all = getAllCheque(conn);

        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return all;
    }

    public static void removeCheckId(Connection c, int id_cheque) throws Exception {
        String sql = "DELETE FROM CHEQUE WHERE id_cheque = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id_cheque);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void removeCheckId(int id_cheque) throws Exception {
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            removeCheckId(conn, id_cheque);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public  void updateCheque(Connection c, Cheque ch) throws SQLException {
        String sql = "UPDATE CHEQUE SET numeroCheque = ?, nomCompte = ? , daty = ? WHERE id_cheque = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = c.prepareStatement(sql);
            pstmt.setString(1, ch.getNumeroCheque());
            pstmt.setString(2, ch.getNomCompte());
            pstmt.setObject(3, ch.getDaty());
            pstmt.setInt(4, ch.getId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public  void updateCheque(Cheque ch) throws Exception {
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            updateCheque(conn, ch);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Cheque getChequeById(Connection conn, int idCheque) throws SQLException {
        if (conn == null) {
            throw new SQLException("Connexion null dans ChequeDAO !");
        }

        Cheque cheque = null;
        String sql = "SELECT * FROM CHEQUE WHERE id_cheque = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCheque);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                cheque = new Cheque(
                        rs.getInt("id_cheque"),
                        rs.getString("nomCompte"),
                        rs.getString("numeroCheque"),
                        rs.getObject("daty", java.time.LocalDate.class)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cheque;
    }

    public Cheque getChequeById(int idCheque) throws SQLException {
        Connection conn = null;
        Cheque cheque = null;

        try {
            conn = utilDB.getConnection();
            if (conn == null) {
                throw new SQLException("Impossible d'obtenir la connexion à la base de données !");
            }

            cheque = getChequeById(conn, idCheque);

        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cheque;
    }

    public int insertCheque(Connection conn, Cheque c) throws SQLException {
        if (conn == null) {
            throw new SQLException("Connexion null dans insertCheque(Connection) !");
        }

        String sql = "INSERT INTO CHEQUE (nomCompte, numeroCheque, daty) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        int idGenere = -1;

        try {
            // 🔹 Préparer la requête avec RETURN_GENERATED_KEYS
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, c.getNomCompte());
            pstmt.setString(2, c.getNumeroCheque());
            pstmt.setObject(3, c.getDaty()); // ou java.sql.Date.valueOf(c.getDaty())

            pstmt.executeUpdate();

            // 🔹 Récupérer la clé générée
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenere = generatedKeys.getInt(1);
                c.setId(idGenere); // optionnel : mettre à jour l'objet
            }

        } finally {
            if (generatedKeys != null) try {
                generatedKeys.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return idGenere;
    }

    public void insertChequeStatus(Connection conn, int id, int id_status, LocalDate daty) throws SQLException {
        if (conn == null) {
            throw new SQLException("Connexion null dans insertCheque(Connection) !");
        }

        String sql = "INSERT INTO CHEQUESTATUS (id_cheque, id_status, daty) VALUES (?, ?, ?)";

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id_status);
            pstmt.setObject(3, daty);
            pstmt.executeUpdate();

        } finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertCheque(Cheque c, int id_status) throws SQLException {
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            if (conn == null) {
                throw new SQLException("Impossible d'obtenir la connexion !");
            }

            int id = insertCheque(conn, c);
            insertChequeStatus(conn, id, id_status, c.getDaty());

        } finally {
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
       public void insertCheque(Cheque c) throws SQLException {
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            if (conn == null) {
                throw new SQLException("Impossible d'obtenir la connexion !");
            }

            int id = insertCheque(conn, c);

        } finally {
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    


    public void removeCheque(Connection c, int id) throws SQLException {
        String sql = "UPDATE CHEQUESTATUS SET id_status = ? WHERE id_cheque = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, 4);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void removeCheque(int id) throws Exception {
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            removeCheque(conn, id);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void removeChequeStatus(Connection c, int id) throws SQLException {
        String sql = "DELETE FROM CHEQUESTATUS WHERE id_chequestatus = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public void removeChequeStatus(int id) throws Exception {
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            removeChequeStatus(conn, id);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Cheque> getAllChequeEncaisse(Connection conn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Connexion null dans ChequeDAO !");
        }

        List<Cheque> all = new ArrayList<>();
        String sql = "SELECT CQ.*, C.id_status, S.status\n"
                + //
                "FROM CHEQUE AS CQ\n"
                + //
                "JOIN (\n"
                + //
                "  SELECT id_cheque, MAX(id_chequestatus) AS last_id\n"
                + //
                "  FROM CHEQUESTATUS\n"
                + //
                "  GROUP BY id_cheque\n"
                + //
                ") AS L ON L.id_cheque = CQ.id_cheque\n"
                + //
                "JOIN CHEQUESTATUS AS C ON C.id_chequestatus = L.last_id\n"
                + //
                "JOIN STATUS AS S ON S.id_status = C.id_status\n"
                + //
                "WHERE C.id_status = 1\n"
                + //
                "  AND CQ.daty >= CURDATE()";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                all.add(new Cheque(
                        rs.getInt("id_cheque"),
                        rs.getString("nomCompte"),
                        rs.getString("numeroCheque"),
                        rs.getObject("daty", java.time.LocalDate.class)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // on laisse remonter l'erreur
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return all;
    }

    public List<Cheque> getAllChequeEncaisse() throws SQLException {
        List<Cheque> all = new ArrayList<>();
        Connection conn = null;

        try {
            conn = utilDB.getConnection();
            if (conn == null) {
                throw new SQLException("Impossible d'obtenir la connexion à la base de données !");
            }
            all = getAllChequeEncaisse(conn);

        } finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return all;
    }

}
