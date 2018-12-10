package com.example.radek.menu;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class menu extends AppCompatActivity {
    private Button przycisk_1;
    private Button przycisk_2;
    private Button przycisk_3;
    private Button przycisk_4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        przycisk_1 = (Button) findViewById(R.id.rozpocznij_4);
        przycisk_2 = (Button) findViewById(R.id.rozpocznij_8);
        przycisk_3 = (Button) findViewById(R.id.rozpocznij_12);
        przycisk_4 = (Button) findViewById(R.id.rozpocznij_16);

        przycisk_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengra_4();
            }
        });
        przycisk_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengra_8();
            }
        });
        przycisk_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengra_12();
            }
        });
        przycisk_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengra_16();
            }
        });

    }
    public void opengra_4(){
        Intent intent = new Intent(this, gra.class);
        startActivity(intent);
    }
    public void opengra_8(){
        Intent intent = new Intent(this, gra_8.class);
        startActivity(intent);
    }
    public void opengra_12(){
        Intent intent = new Intent(this, gra_12.class);
        startActivity(intent);
    }
    public void opengra_16(){
        Intent intent = new Intent(this, gra_16.class);
        startActivity(intent);
    }

}

