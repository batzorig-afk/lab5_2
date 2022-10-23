package com.example.lab5_2;

import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    TextView resultView=null;
    CursorLoader cursorLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView= (TextView) findViewById(R.id.res);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    public void onClickDisplayNames(View view) {
        getSupportLoaderManager().initLoader(1, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        cursorLoader= new CursorLoader(this,
                Uri.parse("content://com.example.lab5_1.MyProvider/cte"), null, null, null, null);
        return cursorLoader;
    }
    @SuppressLint("Range")
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        try {
            cursor.moveToFirst();
            StringBuilder res=new StringBuilder();
            while (!cursor.isAfterLast()) {
                res.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+
                        cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            resultView.setText(res);
        } catch (NullPointerException e1) {
            Toast.makeText(getBaseContext(), String.format("Exception made: %s", e1), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}