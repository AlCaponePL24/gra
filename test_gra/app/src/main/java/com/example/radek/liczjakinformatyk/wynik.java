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


public class wynik extends AppCompatActivity {

    private Button menu;
    private TextView wynik, info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wynik_1);

        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmenu();
            }
        });

        wynik = findViewById(R.id.wynik);
        wynik.setText(Integer.toString(baza.aktualnyWynik));
        info = findViewById(R.id.info);
        info.setText("Ilość poprawnych odpowiedzi");

        bazaDanych db = new bazaDanych(wynik.getContext(), baza.aktualnyTrybGry.toString());
        aktualizacjaBazy(db);

        wyswietlNajlepszeWyniki(db);
    }

    private void openmenu(){
        Intent intent = new Intent( this, menu.class);
        startActivity(intent);
    }

    private void aktualizacjaBazy(bazaDanych db) {
        if(baza.aktualnyWynik > 0) {
            Cursor cursor = db.dane();
            if (cursor.getCount() > 10) {
                cursor.moveToNext();

                int latest = Integer.parseInt(cursor.getString(1));
                int position = 0;

                for (int i = 1; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    int temp = Integer.parseInt(cursor.getString(1));
                    if (latest > temp) {
                        latest = temp;
                        position = i;
                    }
                }

                if (latest < baza.aktualnyWynik) {
                    db.aktualizujDane(Integer.toString(position), Integer.toString(baza.aktualnyWynik));
                }
            } else {
                db.dopiszDane(Integer.toString(baza.aktualnyWynik));
            }
        }
    }

    private void wyswietlNajlepszeWyniki(bazaDanych db) {

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
