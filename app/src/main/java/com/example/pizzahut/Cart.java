package com.example.pizzahut;


import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Cart extends AppCompatActivity  {


    private TextView hedding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        hedding = findViewById(R.id.hedding);
        hedding.setText("CART");

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
