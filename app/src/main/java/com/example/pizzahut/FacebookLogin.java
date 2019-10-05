package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FacebookLogin extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);

        email = findViewById(R.id.editText5);
        password = findViewById(R.id.editText8);
        login = findViewById(R.id.button3);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });

    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered(){
        if(isEmpty(email)){
            email.setError("Email is required");
        }

        if(isEmpty(password)){
            password.setError("Password is required");
        }

        else
        {
            startActivity(new Intent(FacebookLogin.this, MainActivity.class));
        }
    }
}
