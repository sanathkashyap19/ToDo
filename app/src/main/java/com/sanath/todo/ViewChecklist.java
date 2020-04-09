package com.sanath.todo;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Sanath on 13-11-2016.
 */
public class ViewChecklist extends AppCompatActivity{

    LinearLayout checklistView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_checklist);

        checklistView = (LinearLayout) findViewById(R.id.checklist_container);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Bundle i = getIntent().getExtras();
        String title = i.getString("tit");
        String content[] = i.getStringArray("it");
        int len = i.getInt("i");

        TextView t = new TextView(this);
        
        t.setText(title);
        t.setTextSize(35);
        t.setGravity(Gravity.CENTER_HORIZONTAL);
        t.setPadding(5, 15, 5, 15);
        t.setTextColor(Color.BLACK);

        checklistView.addView(t);

        TextView tv[] = new TextView[len];
        CheckBox cb[] = new CheckBox[len];

        for(int j=0; j<len; j++)
        {
            tv[j] = new TextView(this);
            cb[j] = new CheckBox(this);

            tv[j] = new TextView(ViewChecklist.this);
            cb[j] = new CheckBox(ViewChecklist.this);

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams Innerparams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            Innerparams.setMargins(5, 5, 5, 5);

            for(int k = 1; k <= 2; k++)
            {
                if(k==1)
                {
                    cb[j].setLayoutParams(params);
                    cb[j].setPadding(5, 15, 5, 15);
                    cb[j].setGravity(Gravity.CENTER);

                    layout.addView(cb[j]);
                }

                else if(k==2)
                {
                    tv[j].setText(content[j]);
                    tv[j].setPadding(10, 15, 5, 15);
                    tv[j].setTextSize(30);
                    tv[j].setGravity(Gravity.CENTER);
                    tv[j].setTextColor(Color.BLACK);

                    layout.addView(tv[j]);
                }
            }

            checklistView.addView(layout);
        }
    }
}
