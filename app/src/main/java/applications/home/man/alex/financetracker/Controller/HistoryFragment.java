package applications.home.man.alex.financetracker.Controller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import applications.home.man.alex.financetracker.Model.Transaction;
import applications.home.man.alex.financetracker.R;
import applications.home.man.alex.financetracker.Model.DatabaseHelper;
import applications.home.man.alex.financetracker.Util.Constants;

public class HistoryFragment extends Fragment {
    ListView listView;
    ArrayList<Transaction> transactList;
    public static HistoryListAdapter hlAdapter;
    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        transactList = DatabaseHelper.getInstance(getActivity()).getTransactionList();
        hlAdapter = new HistoryListAdapter(transactList, getActivity());
        for ( Transaction transaction : transactList) {
            Log.d("HISTORYFRAGMENT: ", transaction.toString());
        }
        listView = (ListView) v.findViewById(R.id.history_list_view);
        listView.setAdapter(hlAdapter);

        return v;
    }

    public class HistoryListAdapter extends ArrayAdapter<Transaction> {
        private ArrayList<Transaction> mTransactions;
        private Context c;

        private TextView type_tv;
        private TextView amount_tv;
        private TextView desc_tv;
        private TextView date_tv;

        public HistoryListAdapter(ArrayList<Transaction> s, Context c) {
            super(getActivity(), 0, s);
            this.c = c;
            mTransactions = s;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_history_spending, null);
            }
            /***
             * TODO
             * UTILIZE SINGLETON IN HERE
             *
             */
            final Transaction t = mTransactions.get(position);
            Log.d("HISTORYFRAGMENT", "" + position);
            type_tv = (TextView) convertView.findViewById(R.id.list_type);
            amount_tv = (TextView) convertView.findViewById(R.id.list_amount);
            desc_tv = (TextView) convertView.findViewById(R.id.list_description);
            date_tv = (TextView) convertView.findViewById(R.id.list_date);


            type_tv.setText(t.get_type());
            amount_tv.setText("" + t.get_amount());
            desc_tv.setText(t.get_description());
            date_tv.setText(t.get_date());

            return convertView;
        }
    }

}
