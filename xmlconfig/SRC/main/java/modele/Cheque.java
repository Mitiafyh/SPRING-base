package main.java.modele;

import java.time.LocalDate;

public class Cheque {
    int id;
    String nomCompte;
    String numeroCheque;
    LocalDate daty;
    Double montant;

    public Cheque(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCompte() {
        return this.nomCompte;
    }

    public LocalDate getDaty() {
        return daty;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public void setDaty(LocalDate daty) {
        this.daty = daty;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }
    public Cheque(int id, String nomCompte, String numeroCheque, LocalDate date) {
        setDaty(date);
        setId(id);
        setNumeroCheque(numeroCheque);
        setNomCompte(nomCompte);
    }
}