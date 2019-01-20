package com.example.radek.liczjakinformatyk;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ranking extends AppCompatActivity {

    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        //Powrot do MENU
        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openmenu();
            }
        });

        bazaDanych db = new bazaDanych(getBaseContext(), baza.aktualnyTrybGry.toString());

        wyswietlNajlepszeWyniki(db);
    }

    // Powrot do menu
    public void openmenu(){
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }

    private void wyswietlNajlepszeWyniki(bazaDanych db) {
        //setContentView(R.layout.wynik_1);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        Cursor cursor = db.dane();
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            Log.d("WyswietlNajlepszeWyniki", "pokaz: " + i + " wynik");
            TextView textView = new TextView(this);
            textView.setTextColor(getResources().getColor(R.color.colorText));
            textView.setText(i+1 + ": " + cursor.getString(1));

            linearLayout.addView(textView);
        }
    }
}
