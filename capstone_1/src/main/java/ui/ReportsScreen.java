package ui;

import model.Transaction;
import service.ReportService;

import java.util.*;

import util.TextFormatter;

public class ReportsScreen {
    private final Scanner scanner;
    private final ReportService reportService;

    public ReportsScreen(List<Transaction> transactions) {
        this.scanner = new Scanner(System.in);
        this.reportService = new ReportService(transactions);
    }

    public void showReportsScreen() {
        boolean onReportsPage = true;
        while (onReportsPage) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> displayReport("Month To Date", reportService.getMonthToDate());
                case "2" -> displayReport("Previous Month", reportService.getPreviousMonth());
                case "3" -> displayReport("Year To Date", reportService.getYearToDate());
                case "4" -> displayReport("Previous Year", reportService.getPreviousYear());
                case "5" -> searchByVendor();
                case "0" -> {
                    onReportsPage = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== YOU ARE ON REPORTS MENU ===");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back");
        System.out.print("Choose an option: ");
    }

    private void displayReport(String title, List<Transaction> transactions) {
        System.out.printf("\n=== %s (%d transactions) ===\n", title, transactions.size());

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        printTableHeader();
        for (Transaction t : transactions) {
            printTransaction(t);
        }
    }

    private void printTableHeader() {
        System.out.println("Date        Time     Description         Vendor             Amount");
        System.out.println("--------------------------------------------------------------");
    }

    private void printTransaction(Transaction t) {
        System.out.printf("%-12s %-8s %-20s %-18s %,.2f\n",
                t.getDate(),
                t.getFormattedTime(),
                TextFormatter.truncate(t.getDescription(), 20),
                TextFormatter.truncate(t.getVendor(), 18),
                t.getAmount());
    }

    private void searchByVendor() {
        System.out.print("\nEnter vendor name: ");
        String vendor = scanner.nextLine().trim();
        List<Transaction> results = reportService.getByVendor(vendor);
        displayReport("Vendor: " + vendor, results);
    }

}