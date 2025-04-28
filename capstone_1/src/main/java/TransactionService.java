// Class responsible for saving and reading transactions from the "transactions.csv" file.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TransactionService {
    private static final String FILE_NAME = "transactions.csv";

    public void saveTransaction(Transaction transaction) {
        try {
            // Create a FileWriter object that appends text to the transactions.csv file
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);

            // Wrap the FileWriter with a BufferedWriter for better performance
            BufferedWriter writerWithBuffer = new BufferedWriter(fileWriter);

            // Wrap the BufferedWriter with a PrintWriter for easier writing
            PrintWriter printWriter = new PrintWriter(writerWithBuffer);

            // Prepare the line to write to the file
            String transactionLine = "my first new line1";

            // Write the line to the file
            printWriter.println(transactionLine);

            // Always close the writer to save changes and free resources
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
