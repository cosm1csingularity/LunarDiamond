package com.nianticlabs.pikmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LDGPL extends AppCompatActivity {

    private ImageView cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    ArrayList<ImageView> cells = new ArrayList<>();
    private TextView rulTxt;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldgpl);

        final Handler handler = new Handler();

        rulTxt = findViewById(R.id.rulTxt);

        restartButton = findViewById(R.id.restartButton);

        cell1 = findViewById(R.id.cell1);
        cell2 = findViewById(R.id.cell2);
        cell3 = findViewById(R.id.cell3);
        cell4 = findViewById(R.id.cell4);
        cell5 = findViewById(R.id.cell5);
        cell6 = findViewById(R.id.cell6);
        cell7 = findViewById(R.id.cell7);
        cell8 = findViewById(R.id.cell8);
        cell9 = findViewById(R.id.cell9);

        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);
        cells.add(cell6);
        cells.add(cell7);
        cells.add(cell8);
        cells.add(cell9);

        for (ImageView cell: cells) {
            if (cell.getTag() != null && cell.getTag().equals("diamond")){
                cell.setImageResource(R.drawable.gemstone);
            } else {
                cell.setImageResource(R.drawable.rabbit);
            }
            cell.setClickable(false);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (ImageView cell: cells){
                    cell.setImageResource(R.drawable.pouch);
                    cell.setClickable(true);
                }
                rulTxt.setVisibility(View.INVISIBLE);
            }
        }, 1000);

        for (ImageView cell : cells) {
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cell.getTag() != null && cell.getTag().equals("diamond")){
                        cell.setImageResource(R.drawable.gemstone);
                        cell.setTag("clicked");
                    }
                    else {
                        cell.setImageResource(R.drawable.rabbit);
                        for (ImageView cell : cells) {
                            cell.setClickable(false);
                        }
                        restartButton.setVisibility(View.VISIBLE);
                        restartButton.setText("Try Again!");
                    }
                    cell.setClickable(false);

                    if (checkForWin()){
                        restartButton.setText("You won!\nStart over?");
                        restartButton.setVisibility(View.VISIBLE);
                        for (ImageView cell : cells){
                            cell.setClickable(false);
                        }
                    }
                }

                private boolean checkForWin() {
                    int count = 0;
                    for (ImageView cell : cells) {
                        if (cell.getTag() != null && cell.getTag().equals("clicked")){
                            count += 1;
                        }
                    }
                    if (count == 3) {
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public void restartGame(View view) {
        startActivity(new Intent(this, LDGPL.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}