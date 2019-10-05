package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    public Button backBtn;

    EditText firstname;
    EditText lastname;
    EditText mobile;
    EditText email;
    EditText dob;
    Button sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backBtn = findViewById(R.id.back_button);

        firstname = findViewById(R.id.editText2);
        lastname = findViewById(R.id.editText3);
        mobile = findViewById(R.id.editText4);
        email = findViewById(R.id.editText7);
        dob = findViewById(R.id.editText6);
        sub = findViewById(R.id.submit);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
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

    void checkDataEntered() {

        if (isEmpty(firstname)) {
            firstname.setError("first name is required!");

        }

        if (isEmpty(lastname)) {
            lastname.setError("last name is required!");
        }

        if (isEmpty(mobile)) {
            mobile.setError("mobile number is required!");
        }

        if (isEmpty(email)) {
            email.setError("email is required!");
        }


        if (isEmpty(dob)) {
            dob.setError("Date of birth is required!");
        }

        else
        {
            startActivity(new Intent(SignUp.this, MainActivity.class));
        }

    }


    public void goBack() {
        Intent intent = new Intent(this, LoginMainActivity.class);
        startActivity(intent);
    }

}
