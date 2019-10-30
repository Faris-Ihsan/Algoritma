package com.example.qeuangans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.ConsoleHandler;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView textsaldo;
    Button list;

    //Notification
    public static final String CHANNEL_ID = "01";
    public static final String Name = "Keuangans";
    public static final String Desc = "Desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        //CASTING
        textsaldo = findViewById(R.id.textsaldo);
        list = findViewById(R.id.List);


        //PEMANGGILAN METHOD
        jmlSaldo();
    }


    //METHOD SALDO
    private void jmlSaldo() {
        Cursor cursor = db.saldo();
        while (cursor.moveToNext()) {
            textsaldo.append("Rp " + cursor.getString(0) + ",00");
        }

        String check = textsaldo.getText().toString();
        if (check.contentEquals("Rp 100000,00"))
            Toast.makeText(MainActivity.this, "Saldo Tipis", Toast.LENGTH_LONG).show();
    }

    //PINDAH TOMBOL INPUT DATA
    public void Pindah(View view) {
        Intent intent = new Intent(MainActivity.this, InputData.class);
        startActivity(intent);
    }

    public void list(View view) {
        Intent intent = new Intent(MainActivity.this, List.class);
        startActivity(intent);
    }

    public void dispnotif(){
        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("Hohohohoh")
                .setContentText("jdssadfyhijlkojjlljk")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat Mnotification = NotificationManagerCompat.from(this);
        Mnotification.notify(1, mbuilder.build());
    }
}
