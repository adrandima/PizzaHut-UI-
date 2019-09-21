package com.example.pizzahut;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzahut.Model.Location;
import com.example.pizzahut.Service.MyDBHandler;

import java.util.ArrayList;

public class Customise_Item extends AppCompatActivity {
    boolean check = true;
    boolean check1 = true;
    boolean SizeCheck1 = true;
    boolean SizeCheck2 = false;
    boolean SizeCheck3 = false;
    boolean CrustCheck1 = false;
    boolean CrustCheck2 = false;
    boolean CrustCheck3 = false;
    boolean addCheesCheck = false;

    private TextView hedding;
    private ImageButton NavButton;
    private ImageButton fav;
    private TextView price;
    private TextView totPrice;
    private Button Size1;
    private Button Size2;
    private Button Size3;
    private Button Crust1;
    private Button Crust2;
    private Button Crust3;
    private double totalPrice = 1100.00;
    private double finalPrice;
    private CheckBox addChees;
    private Spinner spinner;
    private Button addToCart;
    MyDBHandler db;
    public ArrayList<String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise__item);
        db = new MyDBHandler(this);
        hedding = findViewById(R.id.hedding);
        hedding.setText("CUSTOMISE");
        NavButton = (ImageButton)findViewById(R.id.NavButoon);
        fav = (ImageButton)findViewById(R.id.fav);
        final Intent intent = new Intent(this,MainMenu.class);

        check1 = db.findItem("one");
        ArrayList<String> loc = db.getAllFavorite();
        for (int i=0;i<loc.size();i++){

            System.out.println("Data base input find :++++++++++++++"+loc.get(i));

        }

        System.out.println("====================="+check1);
        if (check1){
            fav.setImageResource(R.drawable.ic_favorite_black_24dp);
            check=false;
        }
        else {
            fav.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            check = true;

        }
        NavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check == false){

                    fav.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                        Toast.makeText(Customise_Item.this, "Item is removed from your favorites", Toast.LENGTH_LONG).show();

                        db.deleteItem("one");
                        ArrayList<String> loc = db.getAllFavorite();
                        for (int i=0;i<loc.size();i++){
                            System.out.println("Data base remove:++++++++++++++"+loc.get(i));
                        }
                        check = true;
                }
                else {

                    fav.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(Customise_Item.this, "Item is added to your favorites", Toast.LENGTH_LONG).show();
                    db.insertItem("one");
                    ArrayList<String> loc = db.getAllFavorite();
                    for (int i=0;i<loc.size();i++){

                        System.out.println("Data base input:++++++++++++++"+loc.get(i));

                    }
                    check=false;

                }
            }
        });


        spinner = (Spinner) findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "1",
                "2",
                "3",
                "4",
                "5",
        };

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        price = (TextView)findViewById(R.id.price);
        Size1 = (Button)findViewById(R.id.Regular);
        Size2 = (Button)findViewById(R.id.Medium);
        Size3 = (Button)findViewById(R.id.Large);

        totPrice = (TextView)findViewById(R.id.totalPrice);
        addToCart = (Button)findViewById(R.id.addToCart);

        Crust1 = (Button)findViewById(R.id.Crust1);
        Crust2 = (Button)findViewById(R.id.Crust2);
        Crust3 = (Button)findViewById(R.id.Crust3);

        Size1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SizeCheck1 == false){
                    Size1.setBackgroundColor(Color.rgb(238,58,67));
                    Size1.setTextColor(Color.WHITE);
                    SizeCheck1 = true;
                    price.setText(" Rs. 1100.00");
                    totalPrice = 1100.00;

                    if (CrustCheck1 == true){
                        price.setText(" Rs. 1300.00");
                        totalPrice = 1100.00;
                    }
                    if (CrustCheck2 == true){
                        price.setText(" Rs. 1350.00");
                        totalPrice = 1350.00;
                    }
                    if (CrustCheck3 == true){
                        price.setText(" Rs. 1400.00");
                        totalPrice = 1400.00;
                    }

                    if (SizeCheck2 == true){
                        Size2.setBackgroundColor(Color.rgb(233,187,187));
                        Size2.setTextColor(Color.rgb(238,58,67));
                        SizeCheck2=false;
                    }
                    if (SizeCheck3 == true){
                        Size3.setBackgroundColor(Color.rgb(233,187,187));
                        Size3.setTextColor(Color.rgb(238,58,67));
                        SizeCheck3=false;
                    }
                }
                else {
                    Size1.setBackgroundColor(Color.rgb(233,187,187));
                    Size1.setTextColor(Color.rgb(238,58,67));
                    SizeCheck1=false;
                    price.setText(" Rs. 0.00");
                    totalPrice = 0.00;
                }
            }
        });

        Size2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SizeCheck2 == false){
                    Size2.setBackgroundColor(Color.rgb(238,58,67));
                    Size2.setTextColor(Color.WHITE);
                    SizeCheck2 = true;
                    price.setText(" Rs. 1400.00");
                    totalPrice = 1400;

                    if (CrustCheck1 == true){
                        price.setText(" Rs. 1500.00");
                        totalPrice = 1500;
                    }
                    if (CrustCheck2 == true){
                        price.setText(" Rs. 1550.00");
                        totalPrice = 1550;
                    }
                    if (CrustCheck3 == true){
                        price.setText(" Rs. 1600.00");
                        totalPrice = 1600;
                    }

                    if (SizeCheck1 == true){
                        Size1.setBackgroundColor(Color.rgb(233,187,187));
                        Size1.setTextColor(Color.rgb(238,58,67));
                        SizeCheck1=false;
                    }
                    if (SizeCheck3 == true){
                        Size3.setBackgroundColor(Color.rgb(233,187,187));
                        Size3.setTextColor(Color.rgb(238,58,67));
                        SizeCheck3=false;
                    }
                }
                else {
                    Size2.setBackgroundColor(Color.rgb(233,187,187));
                    Size2.setTextColor(Color.rgb(238,58,67));
                    SizeCheck2=false;
                    price.setText(" Rs. 0.00");
                    totalPrice = 0.00;
                }
            }
        });

        Size3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SizeCheck3 == false){
                    Size3.setBackgroundColor(Color.rgb(238,58,67));
                    Size3.setTextColor(Color.WHITE);
                    SizeCheck3 = true;
                    price.setText(" Rs. 1700.00");
                    totalPrice = 1700;
                    if (CrustCheck1 == true){
                        price.setText(" Rs. 1800.00");
                        totalPrice = 1800;
                    }
                    if (CrustCheck2 == true){
                        price.setText(" Rs. 1850.00");
                        totalPrice = 1850;
                    }
                    if (CrustCheck3 == true){
                        price.setText(" Rs. 1900.00");
                        totalPrice = 1900;
                    }
                    if (SizeCheck2 == true){
                        Size2.setBackgroundColor(Color.rgb(233,187,187));
                        Size2.setTextColor(Color.rgb(238,58,67));
                        SizeCheck2=false;
                    }
                    if (SizeCheck1 == true){
                        Size1.setBackgroundColor(Color.rgb(233,187,187));
                        Size1.setTextColor(Color.rgb(238,58,67));
                        SizeCheck1=false;
                    }
                }
                else {
                    Size3.setBackgroundColor(Color.rgb(233,187,187));
                    Size3.setTextColor(Color.rgb(238,58,67));
                    SizeCheck3=false;
                    price.setText(" Rs. 0.00");
                    totalPrice = 0.00;
                }
            }
        });

        Crust1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CrustCheck1 == false){
                    Crust1.setBackgroundColor(Color.rgb(238,58,67));
                    Crust1.setTextColor(Color.WHITE);
                    CrustCheck1 = true;
                    if (SizeCheck1 == true){
                        price.setText(" Rs. 1300.00");
                        totalPrice = 1300;
                    }
                    if (SizeCheck2 == true){
                        price.setText(" Rs. 1500.00");
                        totalPrice = 1500;
                    }
                    if (SizeCheck3 == true){
                        price.setText(" Rs. 1800.00");
                        totalPrice = 1800;
                    }

                    if (CrustCheck2 == true){
                        Crust2.setBackgroundColor(Color.rgb(233,187,187));
                        Crust2.setTextColor(Color.rgb(238,58,67));
                        CrustCheck2=false;
                    }
                    if (CrustCheck3 == true){
                        Crust3.setBackgroundColor(Color.rgb(233,187,187));
                        Crust3.setTextColor(Color.rgb(238,58,67));
                        CrustCheck3=false;
                    }
                }
                else {
                    Crust1.setBackgroundColor(Color.rgb(233,187,187));
                    Crust1.setTextColor(Color.rgb(238,58,67));
                    CrustCheck1=false;
                    if (SizeCheck1 == true){
                        price.setText(" Rs. 1100.00");
                        totalPrice = 1100;
                    }
                    if (SizeCheck2 == true){
                        price.setText(" Rs. 1400.00");
                        totalPrice = 1400;
                    }
                    if (SizeCheck3 == true){
                        price.setText(" Rs. 1700.00");
                        totalPrice = 1700;
                    }
                }
            }
        });

        Crust2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CrustCheck2 == false){
                    Crust2.setBackgroundColor(Color.rgb(238,58,67));
                    Crust2.setTextColor(Color.WHITE);
                    CrustCheck2 = true;

                    if (SizeCheck1 == true){
                        price.setText(" Rs. 1350.00");
                        totalPrice = 1350;
                    }
                    if (SizeCheck2 == true){
                        price.setText(" Rs. 1550.00");
                        totalPrice = 1550;
                    }
                    if (SizeCheck3 == true){
                        price.setText(" Rs. 1850.00");
                        totalPrice = 1850;
                    }

                    if (CrustCheck1 == true){
                        Crust1.setBackgroundColor(Color.rgb(233,187,187));
                        Crust1.setTextColor(Color.rgb(238,58,67));
                        CrustCheck1=false;
                    }
                    if (SizeCheck3 == true){
                        Crust3.setBackgroundColor(Color.rgb(233,187,187));
                        Crust3.setTextColor(Color.rgb(238,58,67));
                        CrustCheck3=false;
                    }
                }
                else {
                    Crust2.setBackgroundColor(Color.rgb(233,187,187));
                    Crust2.setTextColor(Color.rgb(238,58,67));
                    CrustCheck2=false;

                    if (SizeCheck1 == true){
                        price.setText(" Rs. 1100.00");
                        totalPrice = 1100;
                    }
                    if (SizeCheck2 == true){
                        price.setText(" Rs. 1400.00");
                        totalPrice = 1400;
                    }
                    if (SizeCheck3 == true){
                        price.setText(" Rs. 1700.00");
                        totalPrice = 1700;
                    }
                }
            }
        });

        Crust3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CrustCheck3 == false){
                    Crust3.setBackgroundColor(Color.rgb(238,58,67));
                    Crust3.setTextColor(Color.WHITE);
                    CrustCheck3 = true;

                    if (SizeCheck1 == true){
                        price.setText(" Rs. 1400.00");
                        totalPrice = 1400;
                    }
                    if (SizeCheck2 == true){
                        price.setText(" Rs. 1600.00");
                        totalPrice = 1600;
                    }
                    if (SizeCheck3 == true){
                        price.setText(" Rs. 1900.00");
                        totalPrice = 1900;
                    }

                    if (CrustCheck2 == true){
                        Crust2.setBackgroundColor(Color.rgb(233,187,187));
                        Crust2.setTextColor(Color.rgb(238,58,67));
                        CrustCheck2=false;
                    }
                    if (CrustCheck1 == true){
                        Crust1.setBackgroundColor(Color.rgb(233,187,187));
                        Crust1.setTextColor(Color.rgb(238,58,67));
                        CrustCheck1=false;
                    }
                }
                else {
                    Crust3.setBackgroundColor(Color.rgb(233,187,187));
                    Crust3.setTextColor(Color.rgb(238,58,67));
                    CrustCheck3=false;
                    if (SizeCheck1 == true){
                        price.setText(" Rs. 1100.00");
                        totalPrice = 1100.00;
                    }
                    if (SizeCheck2 == true){
                        price.setText(" Rs. 1400.00");
                        totalPrice = 1400.00;
                    }
                    if (SizeCheck3 == true){
                        price.setText(" Rs. 1700.00");
                        totalPrice = 1700.00;
                    }
                }
            }
        });


        addChees = (CheckBox)findViewById(R.id.addChees);
        addChees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addCheesCheck == false){
                    totalPrice = totalPrice + 160.00;
                    price.setText(" Rs. "+totalPrice+"0");
                    addCheesCheck = true;
                }
                else {
                    totalPrice = totalPrice - 160.00;
                    price.setText(" Rs. " + totalPrice + "0");
                    addCheesCheck = false;
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                finalPrice = 0.0;
                finalPrice = totalPrice * Double.valueOf(spinner.getSelectedItem().toString()).doubleValue();
                //Toast.makeText(Customise_Item.this, "Total Amount : Rs. "+totalPrice+"0", Toast.LENGTH_LONG).show();

                totPrice.setText("Total Amount : Rs. "+finalPrice+"0");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SizeCheck1 == false && SizeCheck2 == false && SizeCheck3 == false){
                    Toast.makeText(Customise_Item.this, "Please select a size!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Customise_Item.this, "Your order is successfully add to the cart \n            Total Amount : Rs. "+finalPrice+"0", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(),Cart.class);
                    startActivity(intent);
                }
            }
        });

    }
}
