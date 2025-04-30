import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;

public class ReportsScreen {

    Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final TransactionService transactionService = new TransactionService();

    public void showReportsScreen() {
        boolean onReportsScreen = true;
        while (onReportsScreen) {
            displayReportMenu();
            String userOption = scanner.nextLine();

            switch (userOption) {
                case "1" -> {

                }
                case "2" -> {

                }
                case "3" -> {

                }
                case "4" -> {

                }
                case "5" -> {

                }
                case "0" -> {
                    System.out.println("Going back to the previous screen...");
                    onReportsScreen = false;
                }
                default -> {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }


    private void displayReportMenu () {
        System.out.println("You are on the Reports Screen");
        System.out.println("Choose an option:");
        System.out.println("Enter 1 to see reports for Month To Date");
        System.out.println("Enter 2 to see reports for Previous Month");
        System.out.println("Enter 3 to see reports for Year To Date");
        System.out.println("Enter 4 to see reports for Previous Year");
        System.out.println("Enter 5 to search reports by Vendor");
        System.out.println("Enter 0 to go back to the previous screen");
    }

        }

