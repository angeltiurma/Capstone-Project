package com.dicoding.nettoietonvisage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.dicoding.nettoietonvisage.treatment.Level;
import com.dicoding.nettoietonvisage.treatment.LevelAct;
import com.dicoding.nettoietonvisage.treatment.LevelAdapt;
import com.dicoding.nettoietonvisage.treatment.LevelData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Treatment extends AppCompatActivity {

    private RecyclerView rvFigure;
    private ArrayList<Level> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_treatment);

        rvFigure = findViewById(R.id.rvFigure);
        rvFigure.setHasFixedSize(true);

        list.addAll(LevelData.getListData());
        showRecyclerList();

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selector
        bottomNavigationView.setSelectedItemId(R.id.treatment);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.treatment:
                        return true;
                    case R.id.howtouse:
                        startActivity(new Intent(getApplicationContext()
                                ,HowToUse.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.aboutus:
                        startActivity(new Intent(getApplicationContext()
                                ,AboutUs.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }

    private void showRecyclerList(){
        rvFigure.setLayoutManager(new LinearLayoutManager(this));
        LevelAdapt levelAdapt = new LevelAdapt(list);
        rvFigure.setAdapter(levelAdapt);

        levelAdapt.setOnItemClickCallBack(new LevelAdapt.OnItemClickCallBack(){
            public void onItemClicked(com.dicoding.nettoietonvisage.treatment.Level level){
                Intent moveIntent = new Intent(Treatment.this, LevelAct.class);
                moveIntent.putExtra(LevelAct.ITEM_EXTRA, level);
                startActivity(moveIntent);
            }
        });
    }
}