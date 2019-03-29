package com.example.bbddonline;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private View view;
    private TextView user1, pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        texto = (TextView) findViewById(R.id.texto);
        final Dialog fdDialogue = new Dialog(MainActivity.this, R.style.Theme_AppCompat_DayNight_DarkActionBar);
        fdDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        fdDialogue.setContentView(R.layout.login);
        fdDialogue.setCancelable(false);
        fdDialogue.show();
        ((Button) fdDialogue.findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user1 = (EditText) fdDialogue.findViewById(R.id.user);
                pass1 = (EditText) fdDialogue.findViewById(R.id.pass);
                String userfinal, passfinal;
                userfinal=user1.getText().toString();
                passfinal=pass1.getText().toString();
                if (!userfinal.equalsIgnoreCase("") && !passfinal.equalsIgnoreCase("")) {

                    try {
                        String driver = "com.mysql.jdbc.Driver";
                        String urlMysql = "jdbc:mysql://213.60.153.125:3306/";
                        Class.forName(driver).newInstance();
                        con = DriverManager.getConnection(urlMysql + baseDatos, userfinal, passfinal);
                        if(!con.isClosed()){
                            fdDialogue.cancel();
                        st = con.createStatement();
                        rs = st.executeQuery("Select * from departamentos");

                        while (rs.next()) {
                            listado.add(rs.getString(2));
                            Log.i("hola", "entra");
                        }
                        for (int i = 0; listado.size() > i; i++) {
                            Log.i("ddddd", listado.get(i));
                            texto.setText(texto.getText().toString() + "\n" + listado.get(i));


                        }
                        Log.i("hola", texto.getText().toString());}else
                            Toast.makeText(MainActivity.this,"login erroneo",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("hola", e.getMessage());
                    }
                }else
                    Toast.makeText(MainActivity.this,"login erroneo",Toast.LENGTH_LONG).show();

            }
        });
    }


}
