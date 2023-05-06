package com.example.gestiondesproduits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifierProduit extends AppCompatActivity {

    EditText titre, auteur, pages_input;
    Button modifier_button, supprimer_button;

    String id, titres, auteurs, pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_produit);

        titre = findViewById(R.id.title_input2);
        auteur = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        modifier_button = findViewById(R.id.update_button);
        supprimer_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(titres);
        }

        modifier_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
                titres = titre.getText().toString().trim();
                auteurs = auteur.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                myDB.updateData(id, titres, auteurs, pages);
            }
        });
        supprimer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("titre") &&
                getIntent().hasExtra("auteur") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            titres = getIntent().getStringExtra("titre");
            auteurs = getIntent().getStringExtra("auteur");
            pages = getIntent().getStringExtra("pages");

            //Setting Intent Data
            titre.setText(titres);
            auteur.setText(auteurs);
            pages_input.setText(pages);
            Log.d("Sonia", titres+" "+auteurs+" "+pages);
        }else{
            Toast.makeText(this, "Pas de donnes.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer " + titres + " ?");
        builder.setMessage("Vous etes sur de supprimer " + titres + " ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
