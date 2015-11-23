package com.example.dashenka.mytestapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.List;

/**
 * Created by Dashenka on 19.11.15.
 */
public class PersonalData extends BaseActivity {

    String[] StringDiet = {"No food", "Breakfast", "Banana diet", "Meat diet"};

    private Long mRowId;
    private TextView mTitleText;
    private TextView mBodyText;
    private TextView meditWeight;
    private TextView meditHight;
    private Spinner mCategory;

    private Cursor cursor;
    private DataBase mDbHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        Button saveButton = (Button) findViewById(R.id.buttonEdit);
        //TextView
        mTitleText = (TextView) findViewById(R.id.Name);
        mBodyText = (TextView) findViewById(R.id.Age);
        meditWeight = (TextView) findViewById(R.id.Weight);
        meditHight = (TextView) findViewById(R.id.Hight);
        mCategory = (Spinner) findViewById(R.id.Diete);
        // заголовок
        //mDiet.setPrompt("Diet");

        mDbHelper = new DataBase(this);

        mDbHelper.createNewTodo("blabla","blabla","blabla");

        mDb = mDbHelper.getWritableDatabase();

        fillData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PersonalData.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                createNewTask();
            }
        });
    }

    private void createNewTask() {
        Intent intent = new Intent(this, PersonalDataEdit.class);
        startActivityForResult(intent, 1);
    }

    /*private void fillData() {
        mDb = mDbHelper.getWritableDatabase();
        Cursor cursor = mDb.rawQuery("SELECT count(*) FROM itemtable", null);
        cursor.moveToFirst();
        if (cursor.getInt(0) > 0) {
            mDbHelper.fetchAllReminders();
            Cursor remindersCursor = mDbHelper.fetchAllReminders();
            if (remindersCursor != null) {
                String[] from = new String[]{DataBase.COLUMN_SUMMARY};
                int[] to = new int[]{1};
                SimpleCursorAdapter reminders = new SimpleCursorAdapter(this, R.layout.activity_personal, remindersCursor, from, to, 0);
                //setListAdapter(reminders);
            }
        }
        else
        {
        }
    }*/

    private void fillData() {
        //mDbHelper.onCreate(mDb);
        /*Cursor cursor = mDb.rawQuery("SELECT count(*) FROM itemtable", null);
        cursor.moveToFirst();
        if (cursor.getInt(0) > 0)
        {mTitleText.setText("LALAALAL");}
            else
        {mTitleText.setText("APAPAP");}
        */

            cursor = mDbHelper.getAllTodos();
            startManagingCursor(cursor);
            String[] summary = new String[]{ DataBase.COLUMN_SUMMARY };
            String[] description = new String[]{ DataBase.COLUMN_DESCRIPTION};
            String[] id = new String[]{ DataBase.COLUMN_ID};
        if (summary[0].length() == 0 ) {
            mTitleText.setText("LALAALAL");
        }
        else {
            //mTitleText.setText(summary[1]);
            //mBodyText.setText(description[1]);
            mTitleText.setText(summary.toString());
            mBodyText.setText(id.toString());
        }
    }

}

