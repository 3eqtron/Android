package com.example.gestiondesproduits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AjouterProduit extends AppCompatActivity {
    EditText title_input, author_input, pages_input;
    Button ajouter_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        title_input = findViewById(R.id.titre);
        author_input = findViewById(R.id.auteur);
        pages_input = findViewById(R.id.pages_input);
        ajouter_button = findViewById(R.id.ajouter);
        ajouter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
                myDB.addBook(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        Integer.valueOf(pages_input.getText().toString().trim()));
            }
        });
    }
}