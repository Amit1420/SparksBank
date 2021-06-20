package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9973568704,'Amit Kumar',9878000.00,'amitkumar80999@gmail.com','XXXXXXXXXXXX5377','BKID0004808')");
        db.execSQL("insert into user_table values(7488472516,'Anmol Kumar',98582.67,'anmol80999@gmail.com','XXXXXXXXXXXX7845','CNRB0001642')");
        db.execSQL("insert into user_table values(8540858396,'Rohit Kumar',943359.56,'rohit@gmail.com','XXXXXXXXXXXX8917','BKID0004808')");
        db.execSQL("insert into user_table values(8340456174,'Jatin Singh',996854.01,'jatinsingh.champ@gmail.com','XXXXXXXXXXXX8096','BKID0004804')");
        db.execSQL("insert into user_table values(6204839226,'Aayat Arsha',75803.48,'arshaayat139@gmail.com','XXXXXXXXXXXX9076','SBIN0000167')");
        db.execSQL("insert into user_table values(7050878288,'Divyesh Kaushal',96835.16,'divyesh.kaushal@gmail.com','XXXXXXXXXXXX5400','HDFC0000719')");
        db.execSQL("insert into user_table values(7488039767,'Vikash Kumar Mahto',67982.00,'emails2vikash@gmail.com','XXXXXXXXXXXX5806','SBIN0000045')");
        db.execSQL("insert into user_table values(6207886694,'Vikash Prajapati',56892.22,'vikkurohit@gmail.com','XXXXXXXXXXXX5289','SBIN0006076')");
        db.execSQL("insert into user_table values(96930176689,'Pranjal Kumar',89123.46,'pranjalkumar969@gmail.com','XXXXXXXXXXXX3758','BKID0004504')");
        db.execSQL("insert into user_table values(6206311482,'Sudhanshu Kumar Giri',87906.00,'sudhanshu.kumar2448@gmail.com','XXXXXXXXXXXX8645','BKID0004504')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
