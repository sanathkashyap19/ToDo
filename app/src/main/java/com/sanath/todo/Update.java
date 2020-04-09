package com.sanath.todo;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by Sanath on 31-10-2016.
 */
public class Update extends AppCompatActivity{

    EditText title, body;
    DBAdapter db;
    int tag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);


        db = new DBAdapter(this);

        title = (EditText) findViewById(R.id.title);
        body = (EditText) findViewById(R.id.body);

        Bundle i = getIntent().getExtras();
        tag = i.getInt("tag");

        db.open();

        Cursor c = db.getQueryResult("SELECT * FROM LISTS WHERE Id="+tag);

        c.moveToFirst();
        //Toast.makeText(Update.this, ""+c.getCount(), Toast.LENGTH_SHORT).show();
        title.append(c.getString(1));
        body.append(c.getString(2));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_todo_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.done: boolean check = checkValues();
                if(check)
                {

                    String t, b;

                    t = title.getText().toString();
                    b = body.getText().toString();

                    db.open();
                    db.update(t, b, tag);
                    db.close();

                    clear();
                    finish();

                }

                break;

            case R.id.refresh: clear();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void clear() {

        title.setText("");
        body.setText("");
    }


    private boolean checkValues() {

        boolean check = true;

        if(title.getText().toString().isEmpty())
        {
            title.setError("Please enter name");
            check = false;
        }

        if(body.getText().toString().isEmpty())
        {
            body.setError("Please enter address");
            check = false;
        }

        return check;

    }
}
