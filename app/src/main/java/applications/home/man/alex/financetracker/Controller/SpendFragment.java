package applications.home.man.alex.financetracker.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import applications.home.man.alex.financetracker.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SpendFragment extends android.support.v4.app.Fragment {
    EditText amount_et;
    EditText description_et;
    Spinner type_spinner;
    Button spend_bt;
    String type = "";
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
                String amount_spent = amount_et.getText().toString();
                String description = description_et.getText().toString().trim();
                if (amount_spent.isEmpty() || description.isEmpty()) {
                    Toast.makeText(getActivity(),
                            getString(R.string.inalid_input), Toast.LENGTH_LONG).show();
                }
                else {
                    //connection to database should be set
                    //send type, amount spent, description, and date to database
                    //update database

                }
            }
        });
    }

}
