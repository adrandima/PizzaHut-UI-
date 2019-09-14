package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gifImageView = (GifImageView) findViewById(R.id.GifImageView);

        gifImageView.setGifImageResource(R.drawable.loading);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //refresh long-time task in background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //dummy delay for 2 second
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //update ui on UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this,MainMenu.class);
                        startActivity(intent);
                    }
                });

            }
        }).start();
    }
}
