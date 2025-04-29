// Class responsible for saving and reading transactions from the "transactions.csv" file.

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            String transactionLine =  transaction.getDate() + '|' + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount();

            // Write the line to the file
            printWriter.println(transactionLine);

            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


   public List<Transaction> readTransactions () {


     List<Transaction>  readTransactions = new ArrayList<>();
     try {
         FileReader fileReader = new FileReader(FILE_NAME);
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         String line;
         while((line = bufferedReader.readLine()) != null) {
             String [] stringParts = line.split("\\|");
             if(stringParts.length == 5) {
                 readTransactions.add(new Transaction(stringParts[0],stringParts[1],stringParts[2],stringParts[3],Double.parseDouble(stringParts[4])));
             }

         }
     }catch(IOException e) {
         System.out.println("Error reading from file: " + e.getMessage());
     }
       return readTransactions;
    }
}

