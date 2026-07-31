package main.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import main.java.DAO.ChequeDAO;
import main.java.modele.Cheque;

public class ChequeService {

    private ChequeDAO chequeDAO;

    public ChequeDAO getChequeDAO() {
        return chequeDAO;
    }

    public void setChequeDAO(ChequeDAO chequeDAO) {
        this.chequeDAO = chequeDAO;
    }

    public Cheque getCheque(Connection conn, String num) throws Exception {
        Cheque c = chequeDAO.getCheque(conn, num);
        return c;
    }

    public Cheque getCheque(String num) throws Exception {
        Cheque c = chequeDAO.getCheque(num);
        return c;
    }

    public List<Cheque> getAllCheque(Connection conn) throws Exception {
        List<Cheque> list = chequeDAO.getAllCheque(conn);
        return list;
    }

    public List<Cheque> getAllCheque() throws Exception {
        List<Cheque> list = chequeDAO.getAllCheque();
        return list;
    }

    public void insertCheque(Connection conn, Cheque c) throws SQLException {
         chequeDAO.insertCheque(conn, c);
    }

    public void insertCheque(Cheque c) throws SQLException {
         chequeDAO.insertCheque(c);
    }

}
