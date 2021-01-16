 package com.example.soccer_0124;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 public class LastMatchAdapter extends RecyclerView.Adapter<LastMatchAdapter.ListViewHolder> {
     private Context context;
     private ArrayList<Match> listMatch;

     public LastMatchAdapter(ArrayList<Match> listMatch) {
         this.listMatch = listMatch;
     }


     @NonNull
     @Override
     public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_match,parent,false);

         return new ListViewHolder(view);
     }

     @Override
     public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
         Match match = listMatch.get(position);

         holder.DateEvent.setText(match.getDateEvent());
         holder.strHomeTeam.setText(match.getStrHomeTeam());
         holder.strAwayTeam.setText(match.getStrAwayTeam());
         if(match.getIntAwayScore()==null){
             holder.Score.setText("0 VS 0");
         } else {
             holder.Score.setText(match.getIntHomeScore()+" VS "+match.getIntAwayScore());
         }


         holder.itemView.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 Intent detailActivity = new Intent(holder.itemView.getContext(), DetailActivity.class);
                 detailActivity.putExtra("Match",match);

                 holder.itemView.getContext().startActivity(detailActivity);
             }
         });
     }

     @Override
     public int getItemCount() {
         return listMatch.size();
     }

     public class ListViewHolder extends RecyclerView.ViewHolder {
         TextView DateEvent;
         TextView strHomeTeam;
         TextView strAwayTeam;
         TextView Score;


         public ListViewHolder(@NonNull View itemView) {
             super(itemView);

             DateEvent = itemView.findViewById(R.id.DateEvent);
             strHomeTeam = itemView.findViewById(R.id.strHomeTeam);
             strAwayTeam = itemView.findViewById(R.id.strAwayTeam);
             Score = itemView.findViewById(R.id.Score);

         }
     }
 }
