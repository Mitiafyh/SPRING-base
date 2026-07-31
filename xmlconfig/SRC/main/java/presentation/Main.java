package com.tuto;
import main.java.DAO.*;
import main.java.modele.*;
import main.java.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.tuto.Ecole;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.SourceDataLine;
import jdk.jshell.execution.Util;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student std = (Student) context.getBean("student");
        Student std1 = (Student) context.getBean("student");
        Student std2 = (Student) context.getBean("student");
        std.afficher();
        std1.afficher();
        std2.afficher();

        boolean sameInstance = false;

        if(std==std1){
            sameInstance = true;
        }else{
            sameInstance = false;
        }   

        if(sameInstance){
            System.out.println("mitovy ilay objet");
        }else{
            System.out.println("tsy mitovy ilay objet");
        }


        Ecole e1 = new Ecole();
        e1.setNom("John");
        Object e = e1;

        if(std1.equals(e)){
            System.out.println("marina");
        }else{
            System.out.println("tsy marina");
        }

        
        ChequeService chequeService = (ChequeService) context.getBean("chequeService");
        List<Cheque> list = null;
        try {
            list = chequeService.getAllCheque();
            for(Cheque c : list){
                System.out.println(c.getNomCompte() + " - " + c.getDaty() + " - " + c.getNumeroCheque());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserer cheque: ");

        Cheque chq = new Cheque();

        System.out.println("Nom de compte: ");
        chq.setNomCompte(scan.nextLine());

        System.out.println("Numero de cheque: ");
        chq.setNumeroCheque(scan.nextLine());

        System.out.println("Date de vaidite: ");
        String date = scan.nextLine();
        LocalDate d = LocalDate.parse(date);
        chq.setDaty(d);
        try {
            chequeService.insertCheque(chq);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}

