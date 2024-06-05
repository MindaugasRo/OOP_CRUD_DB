package org.example.models;

import org.example.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    private long id;
    private String genre;
    private String title;
    private long author_id;

    public Book() {
    }


    public static ArrayList<Book> selectAll() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try {
            Connection con = Main.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Book bk = new Book(
                        rs.getLong("id"),
                        rs.getString("genre"),
                        rs.getString("title"),
                        rs.getLong("author_id")
                );
                books.add(bk);
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("kaboom!");
        }
        return books;
    }

    public static Book findById(long id) {
        String query = "SELECT * FROM books where id = ?";
        Book bk = null;
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bk = new Book(rs.getLong("id"), rs.getString("title"), rs.getString("genre"), rs.getLong("author_id"));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Failed to retrieve book!");
        }
        return bk;

    }

    public static void create(String title, String genre, long author_id) {
        String query = "INSERT INTO `books` (`title`, `genre`, author_id) VALUES (?,?,?)";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, genre);
            pst.setLong(3, author_id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println("Failed to retrieve book!");
        }
    }

    public static void createNewBook(Scanner sc) {
        System.out.println("Naujos knygos įvedimas");
        System.out.println("Įveskite knygos pavadinimą");
        String title = sc.nextLine();
        System.out.println("Įveskite knygos žanrą");
        String genre = sc.nextLine();
        Author.printAuthors();
        System.out.println("-----------------------------------");
        System.out.println("Priskirkite autorių įvesdami jo ID");
        long author_id = sc.nextInt();
        create(title, genre, author_id);
    }

    public static void updateBook(Scanner sc) {
        printBooks();
        System.out.println("-------------------------------------------");
        System.out.println("Įveskite knygos ID kurią knygą redaguosite");
        Book a = findById(sc.nextLong());
        System.out.println(a);
        sc.nextLine();
        System.out.println("Įveskite knygos pavadinimą");
        String title = sc.nextLine();
        a.setTitle(title);
//        // --
//        System.out.println("Ar knygos žanrą redaguosite?");
//        System.out.println("pažymėkite `T`-taip  arba  `N`-ne");
//        boolean input = sc.nextBoolean();
//        boolean T = true;
//        boolean N = false;
//        while (input == T) {
//            System.out.println("Įveskite knygos žanrą");
//            String genre = sc.nextLine();
//            a.setGenre(genre);
//            input = sc.nextBoolean();
//            if (input == N){
//                break;
//            }
//        }
//        // --
        System.out.println("Įveskite knygos žanrą");
        String genre = sc.nextLine();
        a.setGenre(genre);
        a.update();
    }

    public void update() {
        String query = "UPDATE `books` SET `author_id`= ?, `genre`= ?, `title` = ? WHERE id = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, this.author_id);
            pst.setString(2, this.genre);
            pst.setString(3, this.title);
            pst.setLong(4, this.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to update book!");
        }
    }

    public static void delete(long id) {
        String query = "DELETE FROM `books` WHERE id = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println("Failed to retrieve books!");
        }
    }

    public static void deleteAuthor(Scanner sc) {
        printBooks();
        System.out.println("Įveskite knygos ID kurią norite ištrinti");
        Book a = findById(sc.nextLong());
        sc.nextLine();
        a.delete(a.id);
        System.out.println("Knyga " + a + " ištrinta.");
    }

    public Book(long id, String genre, String title, long author_id) {
        this.id = id;
        this.genre = genre;
        this.title = title;
        this.author_id = author_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public static void printBooks() {
        ArrayList<Book> books = Book.selectAll();
        for (Book book : books) {
            System.out.println(book.getId() + " " + book.getTitle() + " " + book.getGenre());
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
