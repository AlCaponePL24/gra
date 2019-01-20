package com.example.radek.liczjakinformatyk;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class gra_8 extends AppCompatActivity {

    private static final int LICZBA_PRZYCISKOW = 9;
    private static final long START_CZASU = 60000;
    private static final int ZAKRES_LICZBY = 255;

    private long mOdliczanieMili = START_CZASU;


    private Button menu;

    private List<Button> buttons = new ArrayList<Button>();
    private int [] buttonsIndex = {R.id.los_1, R.id.los_2, R.id.los_3,
            R.id.los_4, R.id.los_5, R.id.los_6,
            R.id.los_7, R.id.los_8, R.id.los_9};

    // Aktualnie szukana liczba
    private int szukanaLiczba;
    private int licznikPunktow;

    private TextView liczba;
    private TextView odliczanie;
    private TextView punkty;

    private CountDownTimer odliczanieCzasu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gra_8);

        Log.d("gra_8","onCreate");

        liczba = findViewById(R.id.liczba);
        punkty = findViewById(R.id.punkty);

        licznikPunktow = 0;

        //Powrot do MENU
        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openmenu();
            }
        });

        // tworzenie przycisków
        for(int i = 0; i < LICZBA_PRZYCISKOW; i++) {
            buttons.add((Button) findViewById(buttonsIndex[i]));

            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button)v;
                    int wartoscPrzycisku = Integer.parseInt(button.getText().toString());

                    if(wartoscPrzycisku == szukanaLiczba){
                        licznikPunktow++;
                    } else{
                        openWynik();
                    }
                    losujNowaLiczbe();
                }
            });
        }

        losujNowaLiczbe();

        //start zegara
        odliczanie = findViewById(R.id.textView3);
        startTimera();

        baza.ustawTrybGry(baza.TrybyGry.GRA_8);
    }

    // Rozpoczaecie odliczania zegara
    private void startTimera() {
        odliczanieCzasu = new CountDownTimer(mOdliczanieMili, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mOdliczanieMili = millisUntilFinished;
                aktualizujCzas();
            }

            @Override
            public void onFinish() {
                cancel();
                openWynik();
            }
        }.start();
    }

    // Powrot do menu
    public void openmenu(){
        odliczanieCzasu.cancel();
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }
    //Wyswietlanie wyniku
    public void openWynik(){
        odliczanieCzasu.cancel();
        baza.aktualnyWynik = licznikPunktow;
        Intent intent = new Intent(this, wynik.class);
        startActivity(intent);
    }

    // Aktualizowanie stanu zegara
    private void aktualizujCzas() {
        int sekunda = (int) (mOdliczanieMili / 1000) % 60;

        String wypisz = String.format(Locale.getDefault(), "%02d", sekunda);
        odliczanie.setText(wypisz);
    }

    // losowanie nowych liczb do odgadniecia na przyciskach
    private void losujNowaLiczbe() {

        Random random = new Random();

        for(int i = 0; i < LICZBA_PRZYCISKOW; i++) {
            int wylosowana;
            boolean exit;
            do {
                exit = false;
                wylosowana = random.nextInt(ZAKRES_LICZBY);

                for(int k = 0; k < i; k++) {
                    if(wylosowana == Integer.parseInt(buttons.get(k).getText().toString())) {
                        exit = true;
                        break;
                    }
                }

            } while(exit);

            buttons.get(i).setText(Integer.toString(wylosowana));
        }

        int temp = random.nextInt(LICZBA_PRZYCISKOW);
        szukanaLiczba = Integer.parseInt(buttons.get(temp).getText().toString());

        //wypisywanie liczby binarnej
        String liczba_bitow = Integer.toBinaryString(szukanaLiczba);
        liczba.setText(dopiszZero(liczba_bitow));

        punkty.setText(Integer.toString(licznikPunktow));
    }

    //dodawanie brakujących zer do liczby binarnej
    private CharSequence dopiszZero(CharSequence binarna){
        int bit = binarna.length();
        String zera = "";
        for (int i=0; i<8-bit; i++){
            zera += '0';
        }

        String number = zera + binarna;
        String ret = "";

        for(int i = 0; i < number.length(); i++) {
            if((i) % 4 == 0) ret += " ";
            ret += number.charAt(i);
        }

        return ret;
    }
}

