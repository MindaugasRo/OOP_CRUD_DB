package org.example;

import org.example.models.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Author.printAuthors();

        Scanner sc = new Scanner(System.in);

        while (true) {
            menuOptionsMessage();
            int optionInput = numberInput(sc);
            sc.nextLine();

            switch (optionInput) {
                case 1:
                    Author.printAuthors();
                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

    public static void menuOptionsMessage() {
        System.out.println();
        System.out.println("---------------");
        System.out.println(" 1 - Rodyti autorių sąrašą");
        System.out.println(" 2 - Įvesti naują autorių");
        System.out.println(" 3 - Redaguoti autorių sąrašą");
        System.out.println(" 4 - Ištrinti autorių");
        System.out.println(" 5 - Stabdyti programą");
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