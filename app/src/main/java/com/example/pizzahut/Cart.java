package com.example.pizzahut;


import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Cart extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

    }




    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(), Payment.class);
        startActivity(myIntent);
    }

    public void onButtonClick2(View v){
        Intent myIntent = new Intent(getBaseContext(), Edit.class);
        startActivity(myIntent);
    }
}
