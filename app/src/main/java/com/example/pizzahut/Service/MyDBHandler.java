package com.example.pizzahut.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pizzahut.Model.Location;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Pizza.db";
    public static final String CONTACTS_TABLE_NAME_LOCATION = "location";
    public static final String CONTACTS_TABLE_NAME_fAVARITE = "favorite";
    public static final String COLUMN_PRODUCTNAME = "item";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_LOCATION_LATI = "latitude";
    public static final String CONTACTS_COLUMN_LOCATION_LONG = "longitude";
    public static final String CONTACTS_COLUMN_NAME = "name";
    ArrayList<com.example.pizzahut.Model.Location> array_list;
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS location");
        db.execSQL("DROP TABLE IF EXISTS favorite");
        db.execSQL(
                "create table location " +
                        "(id integer primary key, latitude text,longitude text,name text)"
        );

        db.execSQL(
                "create table favorite " +
                        "(id integer primary key,item text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS location");
        db.execSQL("DROP TABLE IF EXISTS favorite");
        onCreate(db);
    }



    public boolean insertLocation (Location l) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("latitude",l.getLocation().latitude);
        contentValues.put("longitude",l.getLocation().longitude);
        contentValues.put("name", l.getName());

        db.insert("location", null, contentValues);
        return true;
    }


    public boolean insertItem (String item) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("item",item);

        //db.insert("favorite", null, contentValues);
        db.insertWithOnConflict("favorite", null, contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        return true;
    }


    public boolean deleteItem(String itemName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where="item=?";
        db.delete(CONTACTS_TABLE_NAME_fAVARITE, where, new String[]{itemName}) ;

        return true;


    }


    public boolean findItem(String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME_fAVARITE + " where item = '" +itemName + "'" , null);

        if (c.getCount() > 0)
            return true;
        else
            return false;
    }

    public ArrayList<String> getAllFavorite() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from favorite", null );
        res.moveToFirst();
        int i = 0;

        while(res.isAfterLast() == false){

             String item = res.getString(res.getColumnIndex("item"));

            array_list.add(item);

            res.moveToNext();
        }

        return array_list;
    }



    public void deleteAllFavarite()
    {   SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from favorite");
        db.close();
    }


    public void deleteAll()
    {   SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ CONTACTS_TABLE_NAME_LOCATION);
        db.close();
    }


    public ArrayList<Location> getAllLocation() {
        ArrayList<com.example.pizzahut.Model.Location> array_list = new ArrayList<com.example.pizzahut.Model.Location>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from location", null );
        res.moveToFirst();
        int i = 0;
        while(res.isAfterLast() == false){

            double lat = Double.valueOf(res.getString(res.getColumnIndex(CONTACTS_COLUMN_LOCATION_LATI)));
            double lan = Double.valueOf(res.getString(res.getColumnIndex(CONTACTS_COLUMN_LOCATION_LONG)));
            String na = res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME));

            array_list.add(new Location(new LatLng(lat,lan),na));

            res.moveToNext();
        }
        return array_list;
    }


    public boolean insertDate(){
        deleteAll();
        insertLocation(new com.example.pizzahut.Model.Location(new LatLng(6.904766, 79.950325),"Malabe"));
        insertLocation(new com.example.pizzahut.Model.Location(new LatLng(6.938591, 79.986942),"Kaduwela"));
        insertLocation(new com.example.pizzahut.Model.Location(new LatLng(6.911503, 79.851236),"Kollupitiya"));

        return true;
    }
}
