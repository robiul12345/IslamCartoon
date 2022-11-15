package com.smilei.islamcartoon.itemactivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.smilei.islamcartoon.R;
import com.smilei.islamcartoon.ScendActivityYoutubePlayActivity;
import com.smilei.islamcartoon.YoutubePlayActivity;
import com.smilei.islamcartoon.itemactivity.model.Model_item;
import com.smilei.islamcartoon.model.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Costom_item_Adpter extends RecyclerView.Adapter<Costom_item_Adpter.ViewHolder> {

    Context context;
    ArrayList<Model_item> arrayList;

    public Costom_item_Adpter(Context context, ArrayList<Model_item> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cartoon_activity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model_item model = arrayList.get(position);
        holder.textView.setText(model.getTitle());
        Picasso.get().load(model.getUri()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ScendActivityYoutubePlayActivity.class);
                i.putExtra("videoid",model.getVideoId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.titleId);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
