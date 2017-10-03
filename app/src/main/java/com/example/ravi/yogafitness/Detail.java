package com.example.ravi.yogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravi.yogafitness.Model.Exercise;
import com.example.ravi.yogafitness.utils.AllList;

import java.util.List;

public class Detail extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView mDetailImage;
    private TextView mDetailName, mDetailDescription;
    private List<Exercise> allList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        int ex_id = i.getIntExtra("ex_id", 0);

        mToolbar = (Toolbar)findViewById(R.id.detail_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Exercise Name");

        mDetailImage = (ImageView)findViewById(R.id.detail_image);
        mDetailName = (TextView) findViewById(R.id.detail_name);
        mDetailDescription = (TextView)findViewById(R.id.detail_description);

        allList = new AllList().getAllList();

        Exercise single = allList.get(ex_id);
        mDetailImage.setImageResource(single.getImageId());
        mDetailName.setText(single.getName());
        mDetailDescription.setText(single.getDescription());

        Toast.makeText(this, "" + ex_id, Toast.LENGTH_SHORT).show();
    }
}
