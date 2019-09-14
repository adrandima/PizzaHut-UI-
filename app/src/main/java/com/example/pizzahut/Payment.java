package com.example.pizzahut;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

        import android.text.TextUtils;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class Payment extends AppCompatActivity {

    EditText title;
    EditText firstName;
    EditText lastName;
    EditText phone;
    Button conti;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        title = findViewById(R.id.editText3);
        firstName = findViewById(R.id.editText4);
        lastName = findViewById(R.id.editText2);
        phone = findViewById(R.id.editText5);
        conti = findViewById(R.id.button10);


        conti.setOnClickListener(new View.OnClickListener() {
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

        if (isEmpty(title)) {
            title.setError("title is required!");
            Toast t = Toast.makeText(this, "You must enter title to continue!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(firstName)) {
            firstName.setError("First name is required!");
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }

        if (isEmpty(phone)) {
            phone.setError("Phone Number is required!");
        }

        else
        {
            startActivity(new Intent(Payment.this, PaymentDetails.class));
        }

    }


  /*  public void onButtonClick(View view){
        startActivity(new Intent(Payment.this, PaymentDetails .class));
    }
  */


}
