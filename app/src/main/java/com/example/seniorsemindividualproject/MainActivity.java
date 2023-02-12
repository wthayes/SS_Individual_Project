package com.example.seniorsemindividualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView identifyTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identifyTxt = (TextView) findViewById(R.id.identifyTxt);
    }

    public String randomString(){
        String s = "";
        Random rand = new Random();
        try{
            DataInputStream stringFile = new DataInputStream(getAssets().open("PictureStrings.txt"));
            Scanner scan = new Scanner(stringFile);
            for(int i = 0; i < rand.nextInt(50); i++){
                scan.nextLine();
            }
            s = scan.nextLine();
            scan.close();
        }catch (Exception e){
            return s;
        }
        return s;
    }

    public void btnPress(View view){
        String s = randomString();
        identifyTxt.setText(s);
    }
}