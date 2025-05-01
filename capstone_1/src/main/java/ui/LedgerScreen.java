package ui;

import model.Transaction;
import service.TransactionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.TextFormatter;

public class LedgerScreen {
    private final Scanner scanner;
    private final TransactionService transactionService;

    public LedgerScreen(TransactionService transactionService) {
        this.scanner = new Scanner(System.in);
        this.transactionService = transactionService;
    }

    public void showLedgerScreen() {
        boolean onLedgerScreen = true;

        while (onLedgerScreen) {
            printMenu();
            String userChoice = scanner.nextLine().trim().toUpperCase();
            List<Transaction> transactions = transactionService.readTransactions();

            switch (userChoice) {
                case "A" -> showAllTransactions(transactions);
                case "D" -> showDeposits(transactions);
                case "P" -> showPayments(transactions);
                case "R" -> new ReportsScreen(transactions).showReportsScreen();
                case "H" -> {
                    System.out.println("Returning to Home...");
                    onLedgerScreen = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== YOU ARE ON LEDGER MENU ===");
        System.out.println("A) All Transactions");
        System.out.println("D) Deposits Only");
        System.out.println("P) Payments Only");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.print("Choose an option: ");
    }

    private void showAllTransactions(List<Transaction> transactions) {
        printTransactionHeader("ALL TRANSACTIONS", transactions.size());
        for (Transaction t : transactions) {
            printTransaction(t);
        }
    }

    private void showDeposits(List<Transaction> transactions) {
        List<Transaction> deposits = getDeposits(transactions);
        printTransactionHeader("DEPOSITS", deposits.size());
        for (Transaction t : deposits) {
            printTransaction(t);
        }
    }

    private void showPayments(List<Transaction> transactions) {
        List<Transaction> payments = getPayments(transactions);
        printTransactionHeader("PAYMENTS", payments.size());
        for (Transaction t : payments) {
            printTransaction(t);
        }

    }

    private List<Transaction> getDeposits(List<Transaction> transactions) {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }
        return deposits;
    }

    private List<Transaction> getPayments(List<Transaction> transactions) {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }
        return payments;
    }

    private void printTransactionHeader(String title, int count) {
        System.out.printf("\n=== %s (%d entries) ===%n", title, count);
        System.out.println("-".repeat(70));
        System.out.printf("%-12s %-8s %-20s %-15s %12s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-".repeat(70));
    }

    private void printTransaction(Transaction t) {

        System.out.printf("%-12s %-8s %-20s %-15s %,12.2f%n",
                t.getDate(),
                TextFormatter.formatTime(t.getTime()),
                t.getDescription(),
                t.getVendor(),
                t.getAmount());
    }

}