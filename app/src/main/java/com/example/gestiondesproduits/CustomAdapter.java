package com.example.gestiondesproduits;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList livre_id, livre_titre, livre_auteur, livre_pages;

    CustomAdapter(Activity activity, Context context, ArrayList livre_id, ArrayList livre_titre, ArrayList livre_auteur,
                  ArrayList livre_pages){
        this.activity = activity;
        this.context = context;
        this.livre_id = livre_id;
        this.livre_titre = livre_titre;
        this.livre_auteur = livre_auteur;
        this.livre_pages = livre_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.book_id_txt.setText(String.valueOf(livre_id.get(position)));
        holder.book_title_txt.setText(String.valueOf(livre_titre.get(position)));
        holder.book_author_txt.setText(String.valueOf(livre_auteur.get(position)));
        holder.book_pages_txt.setText(String.valueOf(livre_pages.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModifierProduit.class);
                intent.putExtra("id", String.valueOf(livre_id.get(position)));
                intent.putExtra("titre", String.valueOf(livre_titre.get(position)));
                intent.putExtra("auteur", String.valueOf(livre_auteur.get(position)));
                intent.putExtra("pages", String.valueOf(livre_pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return livre_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

