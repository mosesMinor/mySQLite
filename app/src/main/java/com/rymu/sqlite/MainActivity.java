package com.rymu.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mFirstname,mSecondname,mId;
    Button mBtnsave;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstname=(EditText)findViewById(R.id.fname);
        mSecondname=(EditText)findViewById(R.id.sname);
        mId=(EditText)findViewById(R.id.idno);
        mBtnsave=(Button) findViewById(R.id.btnadd);

        db=openOrCreateDatabase("detailsDB",  MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS personreg(fname VARCHAR,sname VARCHAR,id VARCHAR)");
    }
    public void AddtodB(View view) {
       String getFname=mFirstname.getText().toString();
       String getSname=mSecondname.getText().toString();
       String getId=mId.toString();

       if(getFname.isEmpty()){
           messageDisplay("NAME ERROR ","PLEASE ENTER FIRST NAME");
       }else if(getSname.isEmpty()){
           messageDisplay("SNAME ERROR","PLEASE ENTER SECOND NAME");
       }else if(getId.isEmpty()){
           messageDisplay("ID ERROR","PLEASE ENTER ID");
       }else {
           db.execSQL("INSERT INTO personreg VALUES('"+getFname+"','"+getId+"')");
           messageDisplay("SUCCES","ADDED SUCCESFULLY");
           clear();
       }
    }
    private void clear() {
        mFirstname.setText("");
        mSecondname.setText("");
        mId.setText("");
    }
    private void messageDisplay(String tittle,String message) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(tittle);
        builder.setMessage(message);
        builder.create().show();
    }
}
