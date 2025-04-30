package ui;

import model.Transaction;
import service.ReportService;
import java.util.*;

public class ReportsScreen {
    private final Scanner scanner;
    private final ReportService reportService;

    public ReportsScreen(List<Transaction> transactions) {
        this.scanner = new Scanner(System.in);
        this.reportService = new ReportService(transactions);
    }

    public void showReportsScreen () {
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
                case "0" -> { onReportsPage = false; }
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
        // Define column widths
        final int DATE_WIDTH = 12;
        final int TIME_WIDTH = 10;
        final int DESC_WIDTH = 25;
        final int VENDOR_WIDTH = 20;
        final int AMOUNT_WIDTH = 12;

        // Print report header
        System.out.printf("\n=== %s (%d transactions) ===\n", title, transactions.size());

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        // Print table header
        String header = String.format("%-" + DATE_WIDTH + "s %-" + TIME_WIDTH + "s %-" +
                        DESC_WIDTH + "s %-" + VENDOR_WIDTH + "s %" + AMOUNT_WIDTH + "s",
                "Date", "Time", "Description", "Vendor", "Amount");
        String divider = "-".repeat(header.length());

        System.out.println(divider);
        System.out.println(header);
        System.out.println(divider);

        // Print transactions
        for (Transaction t : transactions) {
            System.out.printf("%-" + DATE_WIDTH + "s %-" + TIME_WIDTH + "s %-" +
                            DESC_WIDTH + "s %-" + VENDOR_WIDTH + "s %," + AMOUNT_WIDTH + ".2f\n",
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount());
        }

    }


    private void searchByVendor() {
        System.out.print("\nEnter vendor name: ");
        String vendor = scanner.nextLine().trim();
        List<Transaction> results = reportService.getByVendor(vendor);
        displayReport("Vendor: " + vendor, results);
    }


}