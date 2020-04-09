package com.sanath.todo;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Sanath on 12-11-2016.
 */

public class ChoiceActivity extends DialogFragment {

    ImageButton todo, checklist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.choice_fragment, container);

        todo = (ImageButton) rootView.findViewById(R.id.todo);
        checklist = (ImageButton) rootView.findViewById(R.id.checklist);

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), AddTodo.class));
                getDialog().dismiss();
            }
        });

        checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), AddChecklist.class));
                getDialog().dismiss();
            }
        });

        return rootView;
    }
}
