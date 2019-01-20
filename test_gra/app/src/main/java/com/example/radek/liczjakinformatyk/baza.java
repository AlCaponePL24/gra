package com.example.radek.liczjakinformatyk;

public class baza {
    public enum TrybyGry {
        GRA_4,
        GRA_8,
        GRA_12,
        GRA_16
    }

    public static int aktualnyWynik = 0;
    public static TrybyGry aktualnyTrybGry;

    public static void ustawTrybGry(TrybyGry trybGry) {
        aktualnyTrybGry = trybGry;
    }
}
