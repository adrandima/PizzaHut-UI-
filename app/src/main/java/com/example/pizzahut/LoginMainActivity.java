package com.example.pizzahut;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class LoginMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public Button signup;
    public Button loginBtn;
    public Button facebookLoginBtn;
    public Button googleLoginBtn;
    public Button skipLogin;


    EditText phone;
    Button sub1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        phone = findViewById(R.id.editText);
        sub1 = findViewById(R.id.button4);


        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country_codes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        signup = findViewById(R.id.signup);
        loginBtn = findViewById(R.id.button7);
        facebookLoginBtn = findViewById(R.id.fbloginbutton);
        googleLoginBtn = findViewById(R.id.button6);
        skipLogin = findViewById(R.id.skipLogin);

        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMainActivity();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivity();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity();
            }
        });


        facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbLoginActivity();
            }
        });

        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLoginActivity();
            }
        });


        sub1.setOnClickListener(new View.OnClickListener() {
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

        if(isEmpty(phone)){
            phone.setError("phone number is required");
        }
        else
        {
            startActivity(new Intent(LoginMainActivity.this, MainActivity.class));
        }
    }




    public void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void loginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void newActivity(){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
    public void fbLoginActivity() {
        Intent intent = new Intent(this, FacebookLogin.class);
        startActivity(intent);
    }

    public void googleLoginActivity() {
        Intent intent = new Intent(this, GoogleEmail.class);
        startActivity(intent);
    }
}
