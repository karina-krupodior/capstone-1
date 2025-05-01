package service;

import model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private static final String FILE_NAME = "transactions.csv";

    /**
     * Saves a single transaction to the transactions.csv file.
     * The format for each transaction is: date|time|description|vendor|amount
     *
     * @param transaction The transaction object to be saved to the file.
     */
    public void saveTransaction(Transaction transaction) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)))) {
            // Prepare the transaction string by concatenating its fields.
            String transactionLine = transaction.getDate() + "|" +
                    transaction.getFormattedTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount();

            // Write the transaction string to the file.
            writer.println(transactionLine);
        } catch (IOException e) {
            // Print an error message if writing to the file fails.
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads all transactions from the transactions.csv file and returns them as a list.
     * Each line is expected to be in the format: date|time|description|vendor|amount
     *
     * @return A list of Transaction objects read from the file.
     */
    public List<Transaction> readTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Read each line from the file until the end
            while ((line = reader.readLine()) != null) {
                // Split the line by the pipe character (|) to extract individual fields.
                String[] parts = line.split("\\|");

                // If the line does not contain exactly 5 parts, skip it.
                if (parts.length != 5) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    // Parse the date, time, description, vendor, and amount fields.
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time = LocalTime.parse(parts[1].trim());
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());

                    // Create a new Transaction object and add it to the list.
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                } catch (DateTimeParseException | NumberFormatException e) {
                    // If there is an error while parsing a line, print a message and skip it.
                    System.out.println("Skipping line due to parsing error: " + line);
                }
            }
        } catch (IOException e) {
            // Print an error message if reading from the file fails.
            System.out.println("Error reading from file: " + e.getMessage());
        }

        // Return the list of transactions read from the file.
        return transactions;
    }
}
