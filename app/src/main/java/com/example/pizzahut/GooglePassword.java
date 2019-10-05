package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GooglePassword extends AppCompatActivity {


    EditText pass;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_password);

        pass = findViewById(R.id.editText10);
        next = findViewById(R.id.button16);

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

        if (isEmpty(pass)) {
            pass.setError("Password is required!");
        }
        else
        {
            startActivity(new Intent(GooglePassword.this, LoginMainActivity.class));
        }

    }
}
