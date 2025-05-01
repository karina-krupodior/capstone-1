import service.TransactionService;
import ui.HomeScreen;
// This class contains the main entry point of the program.
public class MainApp {
    public static void main(String[] args) {
        // Create an instance of the transaction service
        TransactionService transactionService = new TransactionService();
        // Create the home screen and pass the transaction service to it
        HomeScreen homeScreen = new HomeScreen(transactionService);
        homeScreen.showHomeScreen();
    }

}
