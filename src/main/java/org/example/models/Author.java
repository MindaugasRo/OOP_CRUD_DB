package org.example.models;

import org.example.Main;

import javax.xml.namespace.QName;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Author {
    private long id;
    private String name;
    private String surName;

    public Author() {
    }

    public Author(long id, String name, String sureName) {
        this.id = id;
        this.name = name;
        this.surName = sureName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public static ArrayList<Author> selectAll(){
        ArrayList<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM authors";
        try {
            Connection con = Main.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Author aut = new Author(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname")
                );
                authors.add(aut);
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception e){
            System.out.println("kaboom!");
        }
        return authors;
    }

    public static Author findById(long id) {
        String query = "SELECT * FROM authors where id = ?";
        Author aut = null;
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1,id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                aut = new Author (rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            }
            con.close();
            pst.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Failed to retrieve author!");
        }
        return aut;

    }

    public static void editAuthor(Scanner sc) {
        printAuthors();
        System.out.println("Įveskite autoriaus ID kurį norite redaguoti");
        Author a = findById(sc.nextLong());
        sc.nextLine();
        System.out.println("Įveskite naują Vardą");
        String name = sc.nextLine();
        a.setName(name);
        System.out.println("Įveskite naują Pavardę");
        String surname = sc.nextLine();
        a.setSurName(surname);
        a.update();
    }

    public static void create(String name, String surname) {
        String query = "INSERT INTO `authors` (`name`, `surname`) VALUES (?,?)";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println("Failed to retrieve author!");
        }
    }

    public static void createNewAuthor(Scanner sc) {
        System.out.println("Naujo autoriaus įvedimas");
            System.out.println("Įveskite autoriaus vardą");
            String name = sc.nextLine();
            System.out.println("Įveskite autoriaus pavardę");
            String surName = sc.nextLine();
            create(name,surName);
    }

    public void update(){
        String query = "UPDATE `authors` SET `name`= ?, `surname` = ? WHERE id = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement (query);
            pst.setString(1, this.name);
            pst.setString(2, this.surName);
            pst.setLong (3, this.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Failed to update author!");
        }
    }

    public static void delete(long id) {
        String query = "DELETE FROM `authors` WHERE id = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println("Failed to retrieve author!");
        }
    }

    public static void deleteAuthor(Scanner sc) {
        printAuthors();
        System.out.println("Įveskite autoriaus ID kurį norite ištrinti");
        Author a = findById(sc.nextLong());
        sc.nextLine();
        a.delete(a.id);
        System.out.println("Autorius " + a + " ištrintas.");
    }
    public static void printAuthors (){
        ArrayList<Author> authors = Author.selectAll();
        for (Author author : authors ) {
            System.out.println(author.getId()  + " " + author.getName() + " " + author.getSurName());
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}