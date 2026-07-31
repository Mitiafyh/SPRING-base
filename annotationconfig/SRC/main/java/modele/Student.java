package com.tuto;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class Student {
   @Value("John")
   private String name;

    @Value("10")
    private int number;

    @Value("john@gmail.com")
    private String email;

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

    public void display(){
        System.out.println("Name : " +name);
        System.out.println("No : " +number);
        System.out.println("Email : " +email);

    }

   
}