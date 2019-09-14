package com.example.pizzahut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PaymentDetails extends AppCompatActivity {

    EditText card;
    EditText month;
    EditText year;
    EditText code;
    Button pay;

    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdetails);


        card = findViewById(R.id.editText3);
        month = findViewById(R.id.editText7);
        year = findViewById(R.id.editText4);
        code = findViewById(R.id.editText2);
        pay = findViewById(R.id.button2);

        pay.setOnClickListener(new View.OnClickListener() {
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

    public void onButtonClick(View view) {
    }

    void checkDataEntered() {

        if (isEmpty(card)) {
            card.setError("card number is required!");
            Toast t = Toast.makeText(this, "Enter card details!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(month)) {
            month.setError("month is required!");
        }

        if (isEmpty(year)) {
            year.setError("year is required!");
        }

        if (isEmpty(code)) {
            code.setError("Security code is required!");
        }

        else
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context, R.style.MyDialogTheme);

            alertDialogBuilder.setTitle("Payment Confirmation!");

            alertDialogBuilder
                    .setMessage("Are you sure to do this!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            PaymentDetails.this.finish();
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

    }



}
