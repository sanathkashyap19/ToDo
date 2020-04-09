package com.sanath.todo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Sanath on 01-11-2016.
 */
public class ViewTodo extends AppCompatActivity{

    TextView title, body;
    String t, b;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_todo);

        title = (TextView) findViewById(R.id.view_title);
        body = (TextView) findViewById(R.id.view_body);

        Bundle i = getIntent().getExtras();

        id = i.getInt("ID", 0);
        t = i.getString("Title");
        b = i.getString("Body");

        title.setText(t);
        body.setText(b);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.view_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_edit:

                Intent i = new Intent(ViewTodo.this, Update.class);
                i.putExtra("tag", id);
                startActivity(i);
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
