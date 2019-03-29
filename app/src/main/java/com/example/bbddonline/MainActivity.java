package com.example.bbddonline;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    private Statement st;
    private ResultSet rs;
    private Connection con;
    private String baseDatos = "catastro_ex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
        String driver="com.mysql.jdbc.Driver";
        String urlMysql = "jdbc:mysql://213.60.153.125:3306/";
        Class.forName(driver).newInstance();
        con= DriverManager.getConnection(urlMysql+baseDatos,"david","alba");
       // st=con.createStatement();
            Toast.makeText(MainActivity.this,"Se conecta correctamente",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("hola",e.getMessage());
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


}
