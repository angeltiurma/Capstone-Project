package com.dicoding.nettoietonvisage.treatment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.nettoietonvisage.R;

public class LevelAct extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        TextView levelln = findViewById(R.id.levv);
        TextView descrip = findViewById(R.id.descript);
        TextView recomn = findViewById(R.id.recom);

        com.dicoding.nettoietonvisage.treatment.Level level = getIntent().getParcelableExtra(ITEM_EXTRA);
        if(level != null){
            levelln.setText(level.getLevell());
            descrip.setText(level.getDescription());
            recomn.setText(level.getRecommendation());
        }
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Level");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}