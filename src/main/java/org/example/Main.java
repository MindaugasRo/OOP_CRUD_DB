package org.example;

import org.example.models.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Author.printAuthors();

//        ArrayList<Author> authors = Author.selectAll();
//        System.out.println(Author.selectAll());
//        for (Author author : authors ) {
//           System.out.println(author);
//           //System.out.println(author.getId()  + " " + author.getName() + " " + author.getSurName());
//        }
//        Author a = Author.findById(8);
//        System.out.println(a);
//        a.setName("Valdemaras");
//        a.setSurName("Vunderkindas");
//        a.update();
//        a = Author.findById(8);
//        System.out.println(a);
//        Author.delete(112);
//        Author.create("Valdemaras", "Jaunesnysis Indigo-Vunderkindas");
    }


    public static Connection connect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_231023_library","root","");
        }catch (Exception e){
            System.out.println("Failed to connect to database");
        }

        return connection;
    }


}