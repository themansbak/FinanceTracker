package applications.home.man.alex.financetracker.Model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Alex on 7/10/2018.
 */
public class SpendingSingleton {
    private ArrayList<Spending> spending_list;
    private Context context;
    private static SpendingSingleton spending_singleton;

    private SpendingSingleton(Context c) {
        this.context = c;
        this.spending_list = new ArrayList<>();
    }
    public static SpendingSingleton get(Context c) {
        if (spending_singleton == null) {
            spending_singleton = new SpendingSingleton(c);
        }
        return spending_singleton;
    }

    public ArrayList<Spending> get_spending_list() { return spending_list; }
    public Spending get_spending(int index) { return spending_list.get(index); }
    public void add_spending(Spending s) { spending_list.add(s); }
    public int get_num_spendings() { return spending_list.size(); }
}
