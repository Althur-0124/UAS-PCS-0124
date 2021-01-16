package com.example.soccer_0124;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Match match = getIntent().getParcelableExtra("Match");

        TextView strHomeTeam = findViewById(R.id.strHomeTeam);
        TextView strAwayTeam = findViewById(R.id.strAwayTeam);
        TextView IntHomeScore = findViewById(R.id.txt_home_goals);
        TextView IntAwayScore = findViewById(R.id.txt_away_goals);
        TextView IntHomeShot = findViewById(R.id.txt_home_shots);
        TextView IntAwayShot = findViewById(R.id.txt_away_shots);
        TextView strGoalKeeperHome = findViewById(R.id.txt_home_goalkeeper);
        TextView strGoalKeeperAway = findViewById(R.id.txt_away_goalkeeper);
        TextView strHomeDefense = findViewById(R.id.txt_home_defense);
        TextView strAwayDefense = findViewById(R.id.txt_away_defense);
        TextView strHomeMid = findViewById(R.id.txt_home_midfield);
        TextView strAwayMid = findViewById(R.id.txt_away_midfield);
        TextView strHomeFor = findViewById(R.id.txt_home_forward);
        TextView strAwayFor = findViewById(R.id.txt_away_forward);
        TextView strHomeSub = findViewById(R.id.txt_home_substitutes);
        TextView strAwaySub = findViewById(R.id.txt_away_subtitutes);
        Button btn_fav = findViewById(R.id.btn_fav);
        Button btnHapus = findViewById(R.id.btn_hapus);

        strHomeTeam.setText(match.getStrHomeTeam());
        strAwayTeam.setText(match.getStrAwayTeam());
        IntHomeScore.setText(match.getIntHomeScore());
        IntAwayScore.setText(match.getIntAwayScore());

        btn_fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.addFavorite(match);
                Toast.makeText(getApplicationContext(),"Menyimpan Pertandingan "+match.getStrHomeTeam(),Toast.LENGTH_SHORT).show();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.deleteFav(match.getIdEvent());
                Toast.makeText(getApplicationContext(),"Menghapus Pertandingan "+match.getStrHomeTeam(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
