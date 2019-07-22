package com.example.dani.poti.net;


import android.view.View;
/**
 * Created by Dani on 2017. 03. 13..
 */

public class PotData {

    private double Temperature;
    private double Humidity;
    private int LDR1;
    private int LDR2;
    private int LDR3;
    private int LDR4;
    private double SoilHumidity;
    private double DesiredSH;
    private int Emergency;
    private int Music;
    private long Time;
    private int Devic;






    public int getDevic() {
        return Devic;
    }

    public void setDevic(int devic) {
        Devic = devic;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double temperature) {
        Temperature = temperature;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        Humidity = humidity;
    }

    public int getLDR1() {
        return LDR1;
    }

    public void setLDR1(int LDR1) {
        this.LDR1 = LDR1;
    }

    public int getLDR2() {
        return LDR2;
    }

    public void setLDR2(int LDR2) {
        this.LDR2 = LDR2;
    }

    public int getLDR3() {
        return LDR3;
    }

    public void setLDR3(int LDR3) {
        this.LDR3 = LDR3;
    }

    public int getLDR4() {
        return LDR4;
    }

    public void setLDR4(int LDR4) {
        this.LDR4 = LDR4;
    }

    public double getSoilHumidity() {
        return SoilHumidity;
    }

    public void setSoilHumidity(double soilHumidity) {
        SoilHumidity = soilHumidity;
    }

    public double getDesiredSH() {
        return DesiredSH;
    }

    public void setDesiredSH(double desiredSH) {
        DesiredSH = desiredSH;
    }

    public int getEmergency() {
        return Emergency;
    }

    public void setEmergency(int emergency) {
        Emergency = emergency;
    }

    public int getMusic() {
        return Music;
    }

    public void setMusic(int music) {
        Music = music;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }
}
