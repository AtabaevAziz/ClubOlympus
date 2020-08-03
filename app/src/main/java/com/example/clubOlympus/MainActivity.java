package com.example.clubOlympus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.clubOlympus.data.ClubOlympusContract.MemberEntry;

public class MainActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<Cursor> {

        ListView dataListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataListView = findViewById(R.id.dataListView);

        FloatingActionButton floatingActionButton =
                findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener((v) {
                Intent intent = new Intent(MainActivity.this,
                AddMemberActivity.class);
        startActivity(intent);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        displayData();
    }

    private void displayData() {
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_GENDER,
                MemberEntry.COLUMN_SPORT,
        };

        Cursor cursor = getContentResolver().query(
                MemberEntry.COLUMN_URI,
                projection,
                null,
                null,
                null
        );

        MemberCursorAdapter cursorAdapter = new MemberCursorAdapter(this, cursor, false);
        dataListView.setAdapter(cursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
