package com.example.dani.poti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dani.poti.net.Downloader;
import com.example.dani.poti.net.PotData;
import com.example.dani.poti.net.StringDownloader;

public class PlantActivity extends AppCompatActivity {

    TextView tv;
    TextView temperature;
    TextView humidity;
    TextView ss;
    TextView LDR1;
    TextView LDR2;
    TextView LDR3;
    TextView LDR4;
    TextView sh;
    Button refresh;
    StringDownloader listaLetolto;
    PotData[] adatok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        PotData[] a = Adattar.getInstance().getMutatni();

        if (a != null){

            temperature = (TextView)findViewById(R.id.tvTemperature);
            temperature.setText("Temperature: " + a[0].getTemperature() +"°C");
            humidity = (TextView)findViewById(R.id.tvHumidity);
            humidity.setText("Humidity " + a[0].getHumidity() + "%");
            LDR1 = (TextView)findViewById(R.id.ldr1);
            LDR1.setText("                          "+a[0].getLDR1()+"%");
            LDR2 = (TextView)findViewById(R.id.ldr2);
            LDR2.setText("\n \n \n \n \n" + a[0].getLDR2()+"%");
            LDR3 = (TextView)findViewById(R.id.ldr3);
            LDR3.setText("\n \n \n \n \n" +a[0].getLDR3()+"%");
            LDR4 = (TextView)findViewById(R.id.ldr4);
            LDR4.setText("                          "+a[0].getLDR4()+"%");
            sh = (TextView)findViewById(R.id.tvSH);
            sh.setText( "Soilhumidity " + Double.toString(a[0].getSoilHumidity()) + "%");

        }
    }

    public void frissit(View v)
    {
        final ProgressDialog pd =  ProgressDialog.show(PlantActivity.this, "Please wait", "Downloading online data", true);
        pd.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    listaLetolto = new StringDownloader("http://midas.ktk.bme.hu/poti", new Downloader.DownloaderListener() {
                        @Override
                        public void error(String message) {

                        }

                        @Override
                        public void progressChanged(int prog) {

                        }

                        @Override
                        public void finished() {

                        }
                    },
                            new StringDownloader.StringDownloaderListener() {
                                @Override
                                public void stringLoaded(final String string) {

                                    try{
                                        String[] sorok = string.replace("\r", "").split("\n");
                                        adatok = new PotData[sorok.length];

                                        for (int i = 0; i < sorok.length; i++) {
                                            PotData D = new PotData();
                                            String[] reszek = sorok[i].split(";");

                                            D.setTemperature(Double.parseDouble(reszek[0]));
                                            D.setHumidity(Double.parseDouble(reszek[1]));
                                            D.setLDR1(Integer.parseInt(reszek[2]));
                                            D.setLDR2(Integer.parseInt(reszek[3]));
                                            D.setLDR3(Integer.parseInt(reszek[4]));
                                            D.setLDR4(Integer.parseInt(reszek[5]));
                                            D.setSoilHumidity(Double.parseDouble(reszek[6]));
                                            D.setDesiredSH(Double.parseDouble(reszek[7]));
                                            D.setEmergency(Integer.parseInt(reszek[8]));
                                            D.setMusic(Integer.parseInt(reszek[9]));
                                            D.setTime(Long.parseLong(reszek[10]));
                                            D.setDevic(Integer.parseInt(reszek[11]));
                                            adatok[i] = D; //így az adatokba beolvassuk a honlapon megadott dolgokat és le tudjuk már kérni ezután belőle
                                        }
                                    }
                                    catch (Exception e){}
                                    Adattar.getInstance().setMutatni(adatok);
                                    //indítás
                                    pd.dismiss();
                                    //goplant();

                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }



                            });


                    listaLetolto.start();
                }
                catch (Exception e){}

                //szerintem ide kell majd, nem biztos

            }

        }).start();
    }

}
