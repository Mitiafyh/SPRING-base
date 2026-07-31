package com.tuto;


public class Student {
    private String name;
    private int number;
    private String email;
    private Ecole e;


    public Ecole getE() {
        return e;
    }
    public void setE(Ecole e) {
        this.e = e;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void afficher(){
        System.out.println("Nom : " + name + " - Numéro : " + number + " - Email : " + email + " - Ecole : " + e.getNom());
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Student)){
            return false;
        }
        Student s = (Student) obj;
        return s.getName().equals(this.name);
    }
    

   
}