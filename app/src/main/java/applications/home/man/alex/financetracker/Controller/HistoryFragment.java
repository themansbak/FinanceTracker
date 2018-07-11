package applications.home.man.alex.financetracker.Controller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import applications.home.man.alex.financetracker.Model.Spending;
import applications.home.man.alex.financetracker.R;

public class HistoryFragment extends Fragment {
    private ArrayList<Spending> spendings;
    private ListView spendings_lv;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);



        return v;
    }

    public class HistoryListAdapter extends ArrayAdapter<Spending> {
        private ArrayList<Spending> mSpendings;
        private Context c;
        public HistoryListAdapter(ArrayList<Spending> s, Context c) {
            super(getActivity(), 0, s);
            this.c = c;
            mSpendings = s;
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


            return convertView;
        }
    }

}
