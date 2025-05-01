package ui;

import model.Transaction;
import service.TransactionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class HomeScreen {

    private final TransactionService transactionService;

    public HomeScreen(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void showHomeScreen() {
        LedgerScreen ledgerScreen = new LedgerScreen(transactionService);
        Scanner scanner = new Scanner(System.in);
        boolean onHomePage = true;

        while (onHomePage) {
            System.out.println("\n=== YOU ARE ON HOME MENU ===");
            System.out.println("Enter D to Add Deposit");
            System.out.println("Enter P to Make Payment (Debit)");
            System.out.println("Enter L to Ledger");
            System.out.println("Enter X to Exit");
            System.out.print("Choose an option: ");

            String userChoice = scanner.nextLine().trim().toUpperCase();

            switch (userChoice) {
                case "D" -> {
                    System.out.println("Add Deposit");
                    System.out.print("Enter product name: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    System.out.print("Enter an amount of product: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
                    transactionService.saveTransaction(deposit);
                    System.out.println("Your deposit was saved successfully.");
                }

                case "P" -> {
                    System.out.println("Make Payment (Debit)");
                    System.out.print("Enter description of your product: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    System.out.print("Enter amount you want to pay for this product: ");
                    double amount = -Math.abs(Double.parseDouble(scanner.nextLine()));

                    Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
                    transactionService.saveTransaction(payment);
                    System.out.println("Your payment was saved successfully.");
                }

                case "L" -> ledgerScreen.showLedgerScreen();

                case "X" -> {
                    System.out.println("Exiting the app");
                    onHomePage = false;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }
}
