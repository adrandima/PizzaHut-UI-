package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    public Button firstBtn;
    public Button lastBtn;
    public Button mobileBtn;
    public Button emailBtn;
    public Button dobBtn;
    public Button genderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        firstBtn = findViewById(R.id.firstname);
        lastBtn = findViewById(R.id.lastname);
        mobileBtn = findViewById(R.id.mobile);
        emailBtn = findViewById(R.id.email);
        dobBtn = findViewById(R.id.dob);
        genderBtn = findViewById(R.id.gender);

    }
        /*
        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFirstName();
            }
        });

        lastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLastName();
            }
        });

        mobileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMobile();
            }
        });

       emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEmail();
            }
        });

        dobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDOB();
            }
        });

        genderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editGender();
            }
        });
    }

    public void editFirstName() {
        Intent intent = new Intent(this, EditFirstName.class);
        startActivity(intent);
    }

    public void editLastName() {
        Intent intent = new Intent(this, EditLastName.class);
        startActivity(intent);
    }

    public void editMobile() {
        Intent intent = new Intent(this, EditMobile.class);
        startActivity(intent);
    }

    public void editEmail() {
        Intent intent = new Intent(this, EditEmail.class);
        startActivity(intent);
    }

    public void editDOB() {
        Intent intent = new Intent(this, EditDOB.class);
        startActivity(intent);
    }

    public void editGender() {
        Intent intent = new Intent(this, EditGender.class);
        startActivity(intent);
    }
    */
}
