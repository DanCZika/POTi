package com.example.dani.poti;

import com.example.dani.poti.net.PotData;

/**
 * Created by Dani on 2017. 03. 22..
 */

public class Adattar {

    private static Adattar ourInstance = new Adattar();


    public static Adattar getInstance() {
        return ourInstance;
    }

    private Adattar() {
    }

    PotData[] mutatni;
    int finished=0;

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public PotData[] getMutatni() {
        return mutatni;
    }

    public void setMutatni(PotData[] mutatni) {
        this.mutatni = mutatni;
    }


}
