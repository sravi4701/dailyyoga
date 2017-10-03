package com.example.ravi.yogafitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ravi.yogafitness.Detail;
import com.example.ravi.yogafitness.Interface.MainItemClickListener;
import com.example.ravi.yogafitness.Model.Exercise;
import com.example.ravi.yogafitness.R;
import com.example.ravi.yogafitness.Training;

import java.util.List;

/**
 * Created by Ravi on 04-10-2017.
 */

class AllExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView itemImage;
    public TextView itemName;
    MainItemClickListener itemClickListener;
    public AllExerciseViewHolder(View itemView) {
        super(itemView);
        itemName = (TextView)itemView.findViewById(R.id.item_excercise_name);
        itemImage = (ImageView) itemView.findViewById(R.id.item_exercise_image);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(MainItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
public class AllExerciseAdapter extends RecyclerView.Adapter<AllExerciseViewHolder> {
    private Context context;
    private List<Exercise> itemList;

    public AllExerciseAdapter(Context context, List<Exercise> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public AllExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.all_exercise_cardview, parent, false);
        return new AllExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllExerciseViewHolder holder, int position) {
        holder.itemImage.setImageResource(itemList.get(position).getImageId());
        holder.itemName.setText(itemList.get(position).getName());

        holder.setItemClickListener(new MainItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent detailIntent = new Intent(context, Detail.class);
                detailIntent.putExtra("ex_id", position);
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
