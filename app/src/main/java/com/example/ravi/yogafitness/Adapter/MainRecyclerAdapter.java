package com.example.ravi.yogafitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravi.yogafitness.AllExercise;
import com.example.ravi.yogafitness.Interface.MainItemClickListener;
import com.example.ravi.yogafitness.MainActivity;
import com.example.ravi.yogafitness.Model.MainPageModel;
import com.example.ravi.yogafitness.R;
import com.example.ravi.yogafitness.Training;
import com.example.ravi.yogafitness.YogaVideo;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Ravi on 01-10-2017.
 */

class MainRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView image;
    public TextView name;
    MainItemClickListener itemClickListener;
    public MainRecyclerViewHolder(View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.main_card_view_image);
        name = (TextView)itemView.findViewById(R.id.main_card_name);
        itemView.setOnClickListener(this);
    }

    public void setMainItemClickListener(MainItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder>{

    private List<MainPageModel> mainList;
    private Context context;

    public MainRecyclerAdapter(List<MainPageModel> mainList, Context context) {
        this.mainList = mainList;
        this.context = context;
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.main_card_view, parent, false);
        return new MainRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {
        holder.image.setImageResource(mainList.get(position).getImage_id());
        holder.name.setText(mainList.get(position).getName());
        holder.setMainItemClickListener(new MainItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                if(position == 0){
                    Intent trainingIntent = new Intent(context, Training.class);
                    context.startActivity(trainingIntent);
                }
                else if(position == 1){
                    Intent allExerciseIntent = new Intent(context, AllExercise.class);
                    context.startActivity(allExerciseIntent);
                }
                else if(position == 2){
                    Intent videoIntent = new Intent(context, YogaVideo.class);
                    context.startActivity(videoIntent);
                }
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }
}