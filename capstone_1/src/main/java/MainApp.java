import service.TransactionService;
import ui.HomeScreen;

public class MainApp {
    public static void main(String[] args) {
//        HomeScreen homeScreen = new HomeScreen();
//        homeScreen.showHomeScreen();

        TransactionService transactionService = new TransactionService();
        HomeScreen homeScreen = new HomeScreen(transactionService);
        homeScreen.showHomeScreen();

    }

}
