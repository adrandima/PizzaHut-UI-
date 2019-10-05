package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileMenu extends AppCompatActivity {

    public Button logOutBtn;
    public Button editBtn;
    private TextView nameHedding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);

        nameHedding = findViewById(R.id.hedding);
        nameHedding.setText("MY PROFILE");
        logOutBtn = findViewById(R.id.logout);
        editBtn = findViewById(R.id.edit);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
            }
        });
    }

    public void logOut() {
        Intent intent = new Intent(this, LoginMainActivity.class);
        startActivity(intent);
    }

    public void editProfile() {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }
}
