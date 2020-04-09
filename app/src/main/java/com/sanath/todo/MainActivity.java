package com.sanath.todo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InterfaceClass.onRecyclerViewItemClick{

    DBAdapter dbAdapter;
    ArrayList<ModelInfo> arrayList;
    Cursor cursor;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerAdapter;
    List id = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new DBAdapter(this);
        arrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.view);

        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        genRecyclerView();
    }

    private void genRecyclerView() {

        dbAdapter.open();
        cursor = dbAdapter.getQueryResult("SELECT * FROM LISTS");

        cursor.moveToFirst();

        arrayList.clear();

        for (int i = 0; i < cursor.getCount(); i++) {
            ModelInfo model = new ModelInfo(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            arrayList.add(model);

            cursor.moveToNext();
        }

        dbAdapter.close();

        recyclerAdapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.details_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.add_entry:

                choice();

                //startActivity(new Intent(MainActivity.this, AddTodo.class));

                break;

            case R.id.del:
                dbAdapter.open();

                for(int i=0; i<id.size(); i++)
                    dbAdapter.delete(Integer.parseInt(id.get(i).toString()));

                recyclerAdapter.notifyDataSetChanged();
                genRecyclerView();
        }

        return super.onOptionsItemSelected(item);
    }

    private void choice() {

        FragmentManager fm = getSupportFragmentManager();
        ChoiceActivity choiceActivity = new ChoiceActivity();
        choiceActivity.show(fm, "ChoiceActivity");

    }

    @Override
    public void OnItemClick(int ID) {

        dbAdapter.open();
        dbAdapter.delete(ID);
        recyclerAdapter.notifyDataSetChanged();
        genRecyclerView();

    }

    @Override
    public void OnCheckBoxClick(int ID) {

        id.add(ID);

    }

    @Override
    public void OnCardViewClick(int ID) {

        String title = null, body = null;
        int id = 0;

        dbAdapter.open();

        cursor = dbAdapter.getQueryResult("SELECT * FROM LISTS WHERE LISTS.ID="+ID);

        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            ModelInfo model = new ModelInfo(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            
            id = model.getID();
            title = model.getTitle();
            body = model.getBody();

            Toast.makeText(MainActivity.this, ""+title+"\n"+body, Toast.LENGTH_SHORT).show();

            cursor.moveToNext();
        }

        dbAdapter.close();

        Intent i = new Intent(MainActivity.this, ViewTodo.class);
        i.putExtra("ID", id);
        i.putExtra("Title", title);
        i.putExtra("Body", body);
        startActivity(i);

    }
}