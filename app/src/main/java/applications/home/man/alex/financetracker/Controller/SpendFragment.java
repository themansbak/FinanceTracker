package applications.home.man.alex.financetracker.Controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import applications.home.man.alex.financetracker.Model.Transaction;
import applications.home.man.alex.financetracker.Model.TransactionSingleton;
import applications.home.man.alex.financetracker.R;
import applications.home.man.alex.financetracker.Model.DatabaseHelper;
import applications.home.man.alex.financetracker.Util.Constants;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SpendFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "SPEND FRAGMENT: ";
    public static final String RESULT_FAILED = "Failed to store transaction";
    public static final String RESULT_PASSED = "Stored transaction";

    EditText amount_et;
    EditText description_et;
    Spinner type_spinner;
    Button spend_bt;
    ArrayList<String> type_list = new ArrayList<>();
    ArrayAdapter<String> type_spinner_array_adapter;

    public SpendFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_spend, container, false);

        type_spinner = (Spinner) v.findViewById(R.id.spending_spinner);
        amount_et = (EditText) v.findViewById(R.id.amount_spent_et);
        description_et = (EditText) v.findViewById(R.id.description_et);
        spend_bt = (Button) v.findViewById(R.id.spend_button);

        type_list.add("Food");
        type_list.add("Clothing");
        type_list.add("Gas");
        type_list.add("Entertainment");
        type_list.add("Rent");

        type_spinner_array_adapter = new ArrayAdapter<>(
                this.getActivity(), android.R.layout.simple_spinner_dropdown_item, type_list);
        type_spinner_array_adapter.setDropDownViewResource(
                R.layout.support_simple_spinner_dropdown_item);
        type_spinner.setAdapter(type_spinner_array_adapter);

        set_event_listeners();

        return v;
    }

    /**
     * Sets event listeners for required UI elements
     */
    public void set_event_listeners()
    {
        spend_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(amount_et) || isEmpty(description_et)) {
                    Toast.makeText(getActivity(),
                            getString(R.string.inalid_input), Toast.LENGTH_LONG).show();
                }
                else {
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                    String formattedDate = df.format(c);
                    Log.d(TAG, "Date: " + formattedDate);

                    String type = type_spinner.getSelectedItem().toString();
                    double amount_spent = Double.parseDouble(amount_et.getText().toString());
                    String description = description_et.getText().toString();

                    boolean result = DatabaseHelper.getInstance(getActivity()).addTransaction(formattedDate, type,
                            amount_spent, description);
                    if (!result)
                        Toast.makeText(getActivity(), RESULT_FAILED, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), RESULT_PASSED, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "description: " + description);
                    TransactionSingleton.get(getActivity()).add_spending(
                            new Transaction(amount_spent, formattedDate, type, description));
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            HistoryFragment.hlAdapter.notifyDataSetChanged();
                            Log.d("SPENDFRAGMENT: ", "adapter should have been notified");
                        }
                    });
                    hideKeyboardFrom(getContext(), getView());
                    clear();
                }
            }
        });
    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void clear() {
        amount_et.setText("");
        description_et.setText("");
    }
    public boolean isEmpty(EditText et) {
        return et.getText().toString().trim().isEmpty();
    }
}
