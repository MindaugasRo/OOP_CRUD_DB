package org.example;

import org.example.models.Author;
import org.example.models.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book.selectAll();
        mainMenu();
    }

    public static void mainMenu(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            menuOptionMessage();
            int optionInput = numberInput(sc);
            sc.nextLine();
            switch (optionInput) {
                case 1:
                    menuForAuthors();
                    break;
                case 2:
                    menuForBooks();
                    break;
                case 3:
                    programExit();
                    break;
            }
        }
    }

    public static void menuForAuthors(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            authorsMenuOptionMessage();
            int optionInput = numberInput(sc);
            sc.nextLine();
            switch (optionInput) {
                case 1:
                    Author.printAuthors();
                    break;
                case 2:
                    Author.createNewAuthor( sc);
                    break;
                case 3:
                    Author.editAuthor(sc);
                    break;
                case 4:
                    Author.deleteAuthor(sc);
                    break;
                case 5:
                    mainMenu();
                    break;
                case 6:
                    programExit();
                    break;
            }
        }
    }

    public static void menuForBooks() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            booksMenuOptionMessage();
            int optionInput = numberInput(sc);
            sc.nextLine();
            switch (optionInput) {
                case 1:
                    Book.printBooks();
                    break;
                case 2:
                    Book.createNewBook(sc);
                    break;
                case 3:
                    Book.updateBook(sc);
                    break;
                case 4:
                    Book.deleteAuthor(sc);
                    break;
                case 5:
                    mainMenu();
                    break;
                case 6:
                    programExit();
                    break;
            }
        }
    }


    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ats_231023_library", "root", "");
        } catch (Exception e) {
            System.out.println("Failed to connect to database");
        }

        return connection;
    }

    public static void menuOptionMessage() {
        System.out.println();
        System.out.println("---------------");
        System.out.println(" 1 - Autorių valdymas");
        System.out.println(" 2 - Knygų valdymas");
        System.out.println(" 3 - Uždaryti programą");
        System.out.println("---------------");
    }

    public static void authorsMenuOptionMessage() {
        System.out.println();
        System.out.println("---------------");
        System.out.println(" 1 - Rodyti autorių sąrašą");
        System.out.println(" 2 - Įvesti naują autorių");
        System.out.println(" 3 - Redaguoti autorių sąrašą");
        System.out.println(" 4 - Ištrinti autorių");
        System.out.println(" 5 - Pagrindinis meniu");
        System.out.println(" 6 - Uždaryti programa");
        System.out.println("---------------");
    }
    public static void booksMenuOptionMessage() {
        System.out.println();
        System.out.println("---------------");
        System.out.println(" 1 - Rodyti knygų sąrašą");
        System.out.println(" 2 - Įvesti naują knygą");
        System.out.println(" 3 - Redaguoti knygą");
        System.out.println(" 4 - Ištrinti knygą");
        System.out.println(" 5 - Pagrindinis meniu");
        System.out.println(" 6 - Uždaryti programa");
        System.out.println("---------------");
    }

    public static int numberInput(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Įveskite savo pasirinkimą skaitmenimis");
                sc.nextLine();
            }
        }
    }

    public static void programExit() {
        System.out.println("Programa uždaryta");
        System.exit(1);
    }


}