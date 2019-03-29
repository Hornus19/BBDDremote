package com.example.bbddonline;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private String baseDatos = "empresab";
    private ArrayList<String> listado = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        texto = (TextView) findViewById(R.id.texto);
        try {
            String driver = "com.mysql.jdbc.Driver";
            String urlMysql = "jdbc:mysql://213.60.153.125:3306/";
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(urlMysql + baseDatos, "david", "Alba123.");
            st = con.createStatement();
            rs = st.executeQuery("Select * from departamentos");

            while (rs.next()) {
                listado.add(rs.getString("NOMBRE"));
            }
            texto.setText(listado.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("hola", e.getMessage());
        }
    }


}
