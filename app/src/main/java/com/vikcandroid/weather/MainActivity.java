package com.vikcandroid.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view
     */
    public static class PlaceholderFragment extends Fragment {

        // Adapter
        ArrayAdapter<String> forecastAdapter;

        // String array to hold dummy forecast
        String[] forecastArray = {"Today - Sunny - 88/63", "Tomorrow - Foggy - 70/46", "Weds - Cloudy - 72/46",
                "Thurs - Rainy - 64/51", "Fri - Foggy - 70/46", "Sat - Sunny - 76/68"};

        // List to hold the strings
        List<String> forecastList = new ArrayList<>(Arrays.asList(forecastArray));

        public PlaceholderFragment() {
        }

        /**
         * Called to have the fragment instantiate its user interface view.
         * This is optional, and non-graphical fragments can return null (which
         * is the default implementation).  This will be called between
         * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
         * <p>
         * <p>If you return a View from here, you will later be called in
         * {@link #onDestroyView} when the view is being released.
         *
         * @param inflater           The LayoutInflater object that can be used to inflate
         *                           any views in the fragment,
         * @param container          If non-null, this is the parent view that the fragment's
         *                           UI should be attached to.  The fragment should not add the view itself,
         *                           but this can be used to generate the LayoutParams of the view.
         * @param savedInstanceState If non-null, this fragment is being re-constructed
         *                           from a previous saved state as given here.
         * @return Return the View for the fragment's UI, or null.
         */
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Now that we have some data, create an {@link ArrayAdapter}
            // The {@link ArrayAdapter} will take data from a source and use it to populate
            // the ListView it's attached to.
            forecastAdapter =
                    new ArrayAdapter<>(getActivity(),
                            R.layout.list_item_forecast,
                            R.id.list_item_forecast_text_view,
                            forecastList);

            // Get a reference to the ListView and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
            listView.setAdapter(forecastAdapter);
            return rootView;
        }
    }
}
