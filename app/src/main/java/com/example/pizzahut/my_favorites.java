package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class my_favorites extends AppCompatActivity {
    private TextView hedding;
    private GridView gridView;
    private ImageButton NavButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        hedding = findViewById(R.id.hedding);
        hedding.setText("FAVORITES");

        gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new Favorites_Items(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(getApplicationContext(), Customise_Item.class);
                i.putExtra("id",position);
                startActivity(i);

            }
        });

        NavButton = (ImageButton)findViewById(R.id.NavButoon);
        final Intent intent = new Intent(this,MainMenu.class);

        NavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
