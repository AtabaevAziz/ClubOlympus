package com.example.clubOlympus;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import com.example.clubOlympus.data.ClubOlympusContract.MemberEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final int MEMBER_LOADER = 123;
    MemberCursorAdapter memberCursorAdapter;

    TextView dataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataTextView = findViewById(R.id.dataTextView);

        FloatingActionButton floatingActionButton =
                findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        AddMemberActivity.class);
                startActivity(intent);
            }
        });

        memberCursorAdapter = new MemberCursorAdapter(this,
                null, false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }

    private void displayData() {
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_GENDER,
                MemberEntry.COLUMN_SPORT
        };

        Cursor cursor = getContentResolver().query(
                MemberEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        dataTextView.setText("All memebers\n\n");
        dataTextView.append( MemberEntry._ID + " " +
                MemberEntry.COLUMN_FIRST_NAME + " " +
                MemberEntry.COLUMN_LAST_NAME + " " +
                MemberEntry.COLUMN_GENDER + " " +
                MemberEntry.COLUMN_SPORT);

        int idColumnIndex = cursor.getColumnIndex(MemberEntry._ID);
        int firstNameColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_LAST_NAME);
        int genderColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_GENDER);
        int sportColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_SPORT);

        while (cursor.moveToNext()) {
            int currentId = cursor.getInt(idColumnIndex);
            String currentFirstName = cursor.getString(firstNameColumnIndex);
            String currentLastName = cursor.getString(lastNameColumnIndex);
            int currentGender = cursor.getInt(genderColumnIndex);
            String currentSport = cursor.getString(sportColumnIndex);

            dataTextView.append("\n" +
                    currentId + " " +
                    currentFirstName + " " +
                    currentLastName + " " +
                    currentGender + " " +
                    currentSport);
        }

        cursor.close();
    }
}