package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GoogleEmail extends AppCompatActivity {



    EditText pmail;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_email);


        pmail = findViewById(R.id.editText9);
        next = findViewById(R.id.button14);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered(){

        if (isEmpty(pmail)) {
            pmail.setError("Email or phone number is required!");
        }
        else
        {
            startActivity(new Intent(GoogleEmail.this, GooglePassword.class));
        }

    }


}
