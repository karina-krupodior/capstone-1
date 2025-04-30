import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Transaction {
    // date       |time    |description       |vendor|amount
    // 2023-04-15 |10:13:25|ergonomic keyboard|Amazon|-89.50
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {

        setDate(date);
        setTime(time);
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }


    public String getDate() {
        return this.date.toString();
    }

    public String getTime() {
        return this.time.toString();
    }



    public String getDescription() {
        return this.description;
    }

    public String getVendor() {
        return this.vendor;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setDate(String date) {
        try {
            this.date = LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD");
        }
    }

    public void setTime(String time) {
        try {
            this.time = LocalTime.parse(time, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please use HH:MM:SS");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;

    }

    public void setAmount(double amount) {
        this.amount = amount;

    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %,.2f",
                getDate(),
                getTime(),
                description,
                vendor,
                amount);
    }
    public String toCSV() {
        return String.join("|",
                getDate(),
                getTime(),
                description,
                vendor,
                String.valueOf(amount));
    }



}
