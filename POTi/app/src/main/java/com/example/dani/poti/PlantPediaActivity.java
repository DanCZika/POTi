package com.example.dani.poti;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlantPediaActivity extends AppCompatActivity {

    Button wiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_pedia);
    }


    public void keres2(View v){
        EditText inp = (EditText)findViewById(R.id.editText2) ;

        String url = "http://www.stackoverflow.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://hu.wikipedia.org/wiki/Special:Search?search=" + inp.getText().toString()));
        startActivity(i);

    }


}
