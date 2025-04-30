package ui;

import model.Transaction;
import service.TransactionService;

import java.util.List;
import java.util.Scanner;

public class LedgerScreen {
    Scanner scanner = new Scanner(System.in);
    TransactionService transactionService = new TransactionService();
    ReportsScreen reportsScreen = new ReportsScreen();

    public void showLedgerScreen() {
        boolean onLedgerScreen = true;

        while (onLedgerScreen) {
            System.out.println("\nYou are on Ledger Screen");
            System.out.println("Select an option:");
            System.out.println("A) Show All Transactions");
            System.out.println("D) Show only Deposits");
            System.out.println("P) Show only Payments");
            System.out.println("R) Show Reports");
            System.out.println("H) Go Home");

            String userChoice = scanner.nextLine().trim().toUpperCase();
            List<Transaction> transactions = transactionService.readTransactions();

            switch (userChoice) {
                case "A" -> {
                    System.out.println("Showing all transactions...");
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                }
                case "D" -> {
                    System.out.println("Showing only Deposits...");
                    for (Transaction t : transactions) {
                        if (t.getAmount() > 0) {
                            System.out.println(t);
                        }
                    }
                }
                case "P" -> {
                    System.out.println("Showing only Payments...");
                    for (Transaction t : transactions) {
                        if (t.getAmount() < 0) {
                            System.out.println(t);
                        }
                    }
                }
                case "R" -> reportsScreen.showReportsScreen();

                case "H" -> {
                    System.out.println("Going Home...");
                    onLedgerScreen = false;
                }
                default -> System.out.println("Invalid option. Please try again.");

            }
        }
    }
}
