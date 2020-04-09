package com.sanath.todo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sanath on 13-11-2016.
 */

public class AddChecklist extends AppCompatActivity {

    int i = 0;
    ListView lv;
    EditText et, title;
    Button add, done;
    TextView tv;
    String mylist[] = new String [100];
    ArrayAdapter<String> m_adapter;
    ArrayList<String> m_listItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_checklist);

        lv = (ListView) findViewById(R.id.list);
        et = (EditText) findViewById(R.id.item);
        title = (EditText) findViewById(R.id.title);
        add = (Button) findViewById(R.id.button1);
        done = (Button) findViewById(R.id.done);
        tv = (TextView) findViewById(R.id.textView1);

        m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, m_listItems);
        lv.setAdapter(m_adapter);

        m_listItems.clear();

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i==EditorInfo.IME_ACTION_DONE || i==EditorInfo.IME_ACTION_NEXT)
                {
                    if(check())
                    {
                        m_listItems.add(et.getText().toString());

                        mylist[i] = et.getText().toString();
                        i++;

                        m_adapter.notifyDataSetChanged();

                    }

                    et.setText("");
                }
                return true;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(check())
                {
                    m_listItems.add(et.getText().toString());

                    mylist[i] = et.getText().toString();
                    i++;

                    m_adapter.notifyDataSetChanged();

                }

                et.setText("");
            }
        });

        done.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(check())
                {
                    String t = title.getText().toString();
                    Intent intent1 = new Intent(AddChecklist.this, ViewChecklist.class);
                    intent1.putExtra("tit",t);
                    intent1.putExtra("it", mylist);
                    intent1.putExtra("i", i);
                    startActivity(intent1);
                    //finish();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_checklist_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String input = et.getText().toString();

        switch (item.getItemId())
        {
            case R.id.add:
                if(check())
                {
                    m_listItems.add(input);

                    mylist[i] = input;
                    i++;

                    m_adapter.notifyDataSetChanged();

                }

                    et.setText("");

                break;

            case R.id.done:
                if(check())
                {
                    String t = title.getText().toString();
                    Intent intent1 = new Intent(AddChecklist.this, ViewChecklist.class);
                    intent1.putExtra("tit",t);
                    intent1.putExtra("it", mylist);
                    intent1.putExtra("i", i);
                    startActivity(intent1);
                    //finish();
                }

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private boolean check() {

        boolean result = true;

        if(mylist.length==0 && m_listItems.isEmpty())
        {
            Toast.makeText(AddChecklist.this, "Enter a List Item", Toast.LENGTH_SHORT).show();
            et.setError("Enter List Items");
            result = false;
        }

        if(title.getText().toString().isEmpty())
        {
            Toast.makeText(AddChecklist.this, "Enter Title", Toast.LENGTH_SHORT).show();
            title.setError("Enter Title");
            result = false;
        }
        return result;
    }
}
