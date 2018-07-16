package applications.home.man.alex.financetracker.Model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Alex on 7/10/2018.
 */
public class TransactionSingleton {
    private ArrayList<Transaction> transaction_list;
    private Context context;
    private static TransactionSingleton spending_singleton;

    private TransactionSingleton(Context c) {
        this.context = c;
        this.transaction_list = new ArrayList<>();
    }
    public static TransactionSingleton get(Context c) {
        if (spending_singleton == null) {
            spending_singleton = new TransactionSingleton(c);
        }
        return spending_singleton;
    }

    public ArrayList<Transaction> get_spending_list() { return transaction_list; }
    public Transaction get_spending(int index) { return transaction_list.get(index); }
    public void add_spending(Transaction s) { transaction_list.add(s); }
    public int get_num_spendings() { return transaction_list.size(); }
}
