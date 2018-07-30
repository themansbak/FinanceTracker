package applications.home.man.alex.financetracker.Model;

import java.io.Serializable;

import applications.home.man.alex.financetracker.Util.Constants;

/**
 * Created by Alex on 7/8/2018.
 */
public class Transaction implements Serializable{
    private static long SERIALIZABLE_UID = 0L;
    private static String type;
    private static String description;
    private static String date;
    private static double amount;

    public Transaction() {
        type = null;
        description = null;
        date = null;
        amount = 0;
    }
    public Transaction(double amount, String type, String description, String date) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
    }
    public double get_amount() { return amount; }
    public String get_type() { return type; }
    public String get_description() {return description; }
    public String get_date() { return date; }
    public String toString() {
        return "Amount: " + get_amount() + ",\n" +
                "Type: " + get_type() + ",\n" +
                "Desc: " + get_description() + ",\n" +
                "Date: " + get_date();
    }

    /**
     * DON'T THINK I NEED ANY SETTERS FOR THIS
     */
//    public void set_amount(int amount) { this.amount = amount; }
//    public void set_type(String type) { this.type = type; }
//    public void set_description(String description) { this.description = description; }
//    public void set_date(String date) { this.date = date; }
}
