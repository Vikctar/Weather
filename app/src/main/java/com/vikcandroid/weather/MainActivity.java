package com.vikcandroid.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ForecastFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        // Handle the map menu option
        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String location = preferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));

        // Using the URI scheme for showing a location found on a map. This super-handy
        // intent is detailed in the "Common Intents" page of Android's developer site:
        // http://developer.android.com/guide/components/intents-common.html#Maps
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed");
        }
    }

    /**
     * The activity is about to become visible
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "in onStart");
    }

    /**
     * The activity has become visible (it is now "resumed")
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "in onResume");
    }

    /**
     * Another activity is taking focus (this activity is about to be paused)
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "in onPause");
    }

    /**
     * The activity is no longer visible (it is now stopped)
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "in onStop");
    }

    /**
     * The activity is about to be destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "in onDestroy");
    }
}
