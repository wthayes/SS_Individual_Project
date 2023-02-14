package com.example.seniorsemindividualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView identifyTxt;
    ImageView loadedImg;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identifyTxt = (TextView) findViewById(R.id.identifyTxt);
        loadedImg = (ImageView) findViewById(R.id.loadedImg);
        rand = new Random();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            try {
                InputStream IS = getContentResolver().openInputStream(data.getData());
                Bitmap BM = BitmapFactory.decodeStream(IS);
                loadedImg.setImageBitmap(BM);
                String s = "That is ";
                s += randomString();
                s += ".";
                identifyTxt.setText(s);
            } catch (Exception e) {
                identifyTxt.setText(e.toString());
            }
        }
    }

    public void loadImage(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");

        startActivityForResult(Intent.createChooser(i, "Pick and Image"), 1);
    }

    public String randomString() {
        String s = "";
        try {
            DataInputStream stringFile = new DataInputStream(getAssets().open("PictureStrings.txt"));
            Scanner scan = new Scanner(stringFile);
            int num = (int) (rand.nextDouble() * 50);
            for (int i = 0; i < num; i++) {
                scan.nextLine();
            }
            s = scan.nextLine();
            scan.close();
        } catch (Exception e) {
            return s;
        }
        return s;
    }
}
