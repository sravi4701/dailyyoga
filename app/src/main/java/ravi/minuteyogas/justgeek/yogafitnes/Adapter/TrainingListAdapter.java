package ravi.minuteyogas.justgeek.yogafitnes.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ravi.minuteyogas.justgeek.yogafitnes.Model.Exercise;
import ravi.minuteyogas.justgeek.yogafitnes.R;

import java.util.List;

/**
 * Created by Ravi on 03-10-2017.
 */

class TrainingListViewHolder extends RecyclerView.ViewHolder{
    public TextView nameofExercise;
    public TextView time;

    public TrainingListViewHolder(View itemView) {
        super(itemView);
        nameofExercise = (TextView)itemView.findViewById(R.id.training_exercise_list_name);
        time = (TextView)itemView.findViewById(R.id.training_exercise_list_time);
    }
}
public class TrainingListAdapter extends RecyclerView.Adapter<TrainingListViewHolder> {
    Context context;
    List<Exercise> trainingList;

    public TrainingListAdapter(Context context, List<Exercise> trainingList) {
        this.context = context;
        this.trainingList = trainingList;
    }

    @Override
    public TrainingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.training_exercise_list, parent, false);
        return new TrainingListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrainingListViewHolder holder, int position) {
        holder.nameofExercise.setText(trainingList.get(position).getName());
        holder.time.setText("30 s");
    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }
}
