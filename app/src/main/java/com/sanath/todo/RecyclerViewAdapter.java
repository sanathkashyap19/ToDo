package com.sanath.todo;

import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    List<ModelInfo> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private InterfaceClass.onRecyclerViewItemClick listner;
    private int lastPosition = -1;

    public RecyclerViewAdapter(Context context, ArrayList<ModelInfo> data,
                               InterfaceClass.onRecyclerViewItemClick listner) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.listner = listner;
    }

    //Another way to delete entries
    /*public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }*/

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cards_display, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ModelInfo info = data.get(position);
        holder.cardView.setTag(info.getID());
        holder.delete.setTag(info.getID());
        holder.title.setText(info.getTitle());

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.slide_down_from_top : R.anim.slide_up_from_bottom);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageButton delete;
        RelativeLayout cardView;
        CardView parent;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            delete = (ImageButton) itemView.findViewById(R.id.delete);
            cardView = (RelativeLayout) itemView.findViewById(R.id.everything);
            parent = (CardView) itemView.findViewById(R.id.card_view);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listner.OnItemClick(Integer.parseInt(v.getTag().toString()));
                }
            });

            /*cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listner.OnCheckBoxClick(Integer.parseInt(v.getTag().toString()));
                }
            });*/

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(context, ""+v.getTag().toString(), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(context, Update.class);
                    i.putExtra("tag", (Integer) v.getTag());
                    context.startActivity(i);

                    return false;
                }
            });

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listner.OnCardViewClick(Integer.parseInt(v.getTag().toString()));

                }
            });

        }

        public void clearAnimation() {
            parent.clearAnimation();
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        ((MyViewHolder) holder).clearAnimation();
    }
}
