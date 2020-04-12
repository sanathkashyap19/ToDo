package com.sanath.todo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sanath on 31-10-2016.
 */

public class AddTodo extends AppCompatActivity{

    EditText title, body;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);

        db = new DBAdapter(this);

        title = (EditText) findViewById(R.id.title);
        body = (EditText) findViewById(R.id.body);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        boolean check = checkValues();
        if(check)
        {

            String t, b;

            t = title.getText().toString();
            b = body.getText().toString();

            db.open();
            db.insert(t, b);
            db.close();

            clear();

            Toast.makeText(AddTodo.this, "List has been saved", Toast.LENGTH_LONG).show();

            finish();


        }

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
                    db.insert(t, b);
                    db.close();

                    clear();
                    startActivity(new Intent(AddTodo.this, MainActivity.class));
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
            title.setError("Please enter title");
            check = false;
        }

        if(body.getText().toString().isEmpty())
        {
            body.setError("Please enter some content");
            check = false;
        }

        return check;

    }
}
