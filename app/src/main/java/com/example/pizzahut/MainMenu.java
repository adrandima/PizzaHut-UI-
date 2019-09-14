package com.example.pizzahut;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenu extends AppCompatActivity {

    private CardView cardView;
    private CardView favorites;
    private ImageButton NavButton;
    private RadioButton delivery;
    private RadioButton pickup;
    private boolean deliveryCheck = false;
    private boolean pickupCheck = false;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    Dialog myDialog;

    private int[] myImageList = new int[]{R.drawable.one, R.drawable.two,
            R.drawable.three,R.drawable.four
            ,R.drawable.five,R.drawable.six};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        myDialog = new Dialog(this);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();

        final Intent intent = new Intent(this, PizzaMenu.class);
        final Intent intent2 = new Intent(this,my_favorites.class);

        cardView = (CardView)findViewById(R.id.pzzaCard);
        favorites = (CardView)findViewById(R.id.favorites);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });


        NavButton = (ImageButton)findViewById(R.id.NavButoon);
        final Intent intentMenu = new Intent(this,MainMenu.class);
        NavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMenu);
            }
        });




        delivery = (RadioButton)findViewById(R.id.deliery);
        pickup = (RadioButton)findViewById(R.id.pickup);

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deliveryCheck == false){
                    openDeliveryActivity(view);
                    deliveryCheck = true;
                    pickupCheck = false;
                }
                else {
                    deliveryCheck =false;
                }
            }
        });

        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pickupCheck == false){
                    openTakeaway(view);
                    pickupCheck = true;
                    deliveryCheck =false;
                }else {
                    pickupCheck = false;
                }
            }
        });


    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 6; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainMenu.this,imageModelArrayList));

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);


    }


    public void dismiss(View view){
        myDialog.dismiss();
    }

    public void openDeliveryActivity(View view){

        myDialog.setContentView(R.layout.activity_delivery);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
       /* Intent intent = new Intent(this,Delivery.class);
        startActivity(intent);*/
    }

    public void openTakeaway(View view){
        myDialog.setContentView(R.layout.activity_takeaway);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    public void continueButtonAction(View view){
        Bundle extras = new Bundle();
        Intent intent = new Intent(this, DirectionPage.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void locateMe(View view){
        Bundle extras = new Bundle();
        Intent intent = new Intent(this, DirectionPage.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void pickDirection(View view){
        Bundle extras = new Bundle();
        Intent intent = new Intent(this, Takeaway_location.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
