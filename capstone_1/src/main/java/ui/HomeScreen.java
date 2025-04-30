package ui;

import model.Transaction;
import service.TransactionService;

import java.util.Scanner;

public class HomeScreen {
    public void showHomeScreen() {
        TransactionService transactionService = new TransactionService();
        Scanner scanner = new Scanner(System.in);
        LedgerScreen ledgerScreen = new LedgerScreen();
        boolean onHomePage = true;
        while (onHomePage) {
            System.out.println("You on the HOME SCREEN");
            System.out.println("Chose an option");
            System.out.println("Enter D to Add Deposit");
            System.out.println("Enter P to  Make Payment (Debit)");
            System.out.println("Enter L to  Ledger(to see tour info)");
            System.out.println("Enter X to Exit");

            String userChoice = scanner.nextLine().trim().toUpperCase();


            switch (userChoice) {
                case "D" -> {
                    System.out.println("Add Deposit");
                    System.out.println("Enter product name");
                    String description = scanner.nextLine();
                    System.out.println("Enter vendor  name");
                    String vendor = scanner.nextLine();
                    System.out.println("Enter an amount of product");
                    Double amount = Double.parseDouble(scanner.nextLine());

                    String date = java.time.LocalDate.now().toString();
                    String time = java.time.LocalTime.now().toString();

                    Transaction deposit = new Transaction(date, time, description, vendor, amount);
                    transactionService.saveTransaction(deposit);
                    System.out.println("Your deposit successfully saved ");

                }
                case "P" -> {
                    System.out.println("Make Payment (Debit)");
                    System.out.print("Enter description of your product");
                    String description = scanner.nextLine();
                    System.out.println("Enter vendor name");
                    String vendor = scanner.nextLine();
                    System.out.println("Enter amount you wanna pay for this product");
                    Double amount = Double.parseDouble(scanner.nextLine());
                    amount = -Math.abs(amount);
                    String date = java.time.LocalDate.now().toString();
                    String time = java.time.LocalTime.now().toString();

                    Transaction payment = new Transaction(date, time, description, vendor, amount);
                    transactionService.saveTransaction(payment);
                    System.out.println("Your payment saved successfully");
                }
                case "L" -> ledgerScreen.showLedgerScreen();

                case "X" -> {
                    System.out.println("Exit");
                    System.out.println("Exiting the app");
                    onHomePage = false;
                }

                default -> System.out.println("Invalid option");
            }
        }


    }
}

