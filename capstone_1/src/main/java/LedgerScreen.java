import java.util.List;
import java.util.Scanner;

public class LedgerScreen {
    Scanner scanner = new Scanner(System.in);
    TransactionService transactionService = new TransactionService();

   public  void showLedgerScreen () {
      boolean onLedgerScreen = true;
       while (onLedgerScreen){
           System.out.println("You are on LedgerScreen");
           System.out.println("Select an option");
           System.out.println("A) Show All Transactions");
           System.out.println("D) Show only Deposits");
           System.out.println("P) Show only Payments");
           System.out.println("R) Show Reports");
           System.out.println("H) Go Home");

          String  userChoice = scanner.nextLine().trim().toUpperCase();
        List<Transaction> transactions = transactionService.readTransactions();

          switch (userChoice) {
              case "A" -> {
                  System.out.println("Showing all transactions....");
                  transactions.forEach(t -> System.out.println(t));
              }
              case "D" -> {
                  System.out.println("Showing only Deposits ....");
                  for ( Transaction transaction: transactions ) {
                      if(transaction.getAmount() > 0 ) {
                          System.out.println(transaction);
                      }

                  }
              }


          }

       }



    }


}
