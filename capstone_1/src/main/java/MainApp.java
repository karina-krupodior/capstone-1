import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();

        Transaction newTransaction = new Transaction("2025-04-28","10:13:25","mouse","apple",120);
        Transaction t1 = new Transaction("2025-04-28", "12:30", "Lunch", "Restaurant ABC", 18.50);
        Transaction t2 = new Transaction("2025-04-28", "15:00", "Books", "Bookstore XYZ", 45.99);

        transactionService.saveTransaction(newTransaction);
        transactionService.saveTransaction(t1);
        transactionService.saveTransaction(t2);


        List<Transaction> transactions = transactionService.readTransactions();

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getDate() + " " + transaction.getTime() + " " +
                    transaction.getDescription() + " " + transaction.getVendor() + " " + transaction.getAmount());
        }

    }
}
