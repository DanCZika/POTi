package com.example.dani.poti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dani.poti.net.Downloader;
import com.example.dani.poti.net.PotData;
import com.example.dani.poti.net.StringDownloader;

public class MainActivity extends AppCompatActivity {

    public Button connect;
    public Button add;
    public Button pedia;
    PotData[] adatok;
    StringDownloader listaLetolto;



    void goplant()
    {
        Intent i = new Intent(MainActivity.this,PlantActivity.class);
        startActivity(i);
    }

    public void init()
    {
        connect = (Button)findViewById(R.id.connectbtn);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd =  ProgressDialog.show(MainActivity.this, "Please wait", "Downloading online data", true);
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
                                            goplant();
                                        }



                                    });


                            listaLetolto.start();
                        }
                        catch (Exception e){}

                        //szerintem ide kell majd, nem biztos

                    }

                }).start();
            }
        });

        add = (Button)findViewById(R.id.addbtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AddActivity.class);

                startActivity(i);

            }
        });

        pedia = (Button)findViewById(R.id.pediabtn);
        pedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent i = new Intent(MainActivity.this, PlantPediaActivity.class);
               startActivity(i);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }


}
