package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    public Button backBtn;

    EditText mobile;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backBtn = findViewById(R.id.back_button);

        mobile = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        login = findViewById(R.id.login);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
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

        if (isEmpty(mobile)) {
            mobile.setError("mobile number is required!");
        }

        if (isEmpty(password)) {
            password.setError("Password is required!");
        }

        else
        {
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }



    public void goBack() {
        Intent intent = new Intent(this, LoginMainActivity.class);
        startActivity(intent);
    }

}
