package com.example.radek.liczjakinformatyk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class WyborRankingow extends AppCompatActivity {

    private static final int LICZBA_RANKINGOW = 4;

    private Button menu;
    private List<Button> rankingi = new ArrayList<Button>(LICZBA_RANKINGOW);
    private int [] buttonsID = {R.id.ranking_4, R.id.ranking_8, R.id.ranking_12, R.id.ranking_16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wybor_rankingow);

        //Powrot do MENU
        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openmenu();
            }
        });

        for(int i = 0; i < LICZBA_RANKINGOW; i++) {
            Button button = findViewById(buttonsID[i]);
            rankingi.add(button);
        }

        rankingi.get(0).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                baza.ustawTrybGry(baza.TrybyGry.GRA_4);
                openWynik();
            }
        });

        rankingi.get(1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                baza.ustawTrybGry(baza.TrybyGry.GRA_8);
                openWynik();
            }
        });

        rankingi.get(2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                baza.ustawTrybGry(baza.TrybyGry.GRA_12);
                openWynik();
            }
        });

        rankingi.get(3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                baza.ustawTrybGry(baza.TrybyGry.GRA_16);
                openWynik();
            }
        });
    }

    // Powrot do menu
    public void openmenu(){
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }

    //Wyswietlanie wyniku
    public void openWynik(){
        Intent intent = new Intent(this, ranking.class);
        startActivity(intent);
    }
}
