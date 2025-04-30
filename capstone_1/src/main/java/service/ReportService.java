package service;
import model.Transaction;
import java.time.*;
import java.util.*;

public class ReportService {

    private final List<Transaction> transactions;

    public ReportService(List<Transaction> transactions) {
        this.transactions = transactions;
    }

//     Gets all transactions from the first day of the current month to today.
//     * Example:
//     * If today is June 15, 2023, returns transactions from June 1 - June 15, 2023
    public List<Transaction> getMonthToDate() {
        LocalDate now = LocalDate.now();
        return getTransactionsBetween(now.withDayOfMonth(1), now);
    }
//  Example:
//   If current month is June 2023, returns all May 2023 transactions
    public List<Transaction> getPreviousMonth() {
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        return getTransactionsBetween(
                lastMonth.atDay(1),
                lastMonth.atEndOfMonth()
        );
    }

    public List<Transaction> getYearToDate() {
        LocalDate now = LocalDate.now();
        return getTransactionsBetween(now.withDayOfYear(1), now);
    }

//* Example:
//* If today is June 15, 2023, returns transactions from Jan 1 - June 15, 2023
    public List<Transaction> getPreviousYear() {
        Year lastYear = Year.now().minusYears(1);
        return getTransactionsBetween(
                lastYear.atDay(1),
                lastYear.atDay(lastYear.length())
        );
    }

    public List<Transaction> getByVendor(String vendor) {
        List<Transaction> results = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                results.add(t);
            }
        }
        return results;
    }

    private List<Transaction> getTransactionsBetween(LocalDate start, LocalDate end) {
        List<Transaction> results = new ArrayList<>();
        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate());
            if (!date.isBefore(start) && !date.isAfter(end)) {
                results.add(t);
            }
        }
        return results;
    }


}