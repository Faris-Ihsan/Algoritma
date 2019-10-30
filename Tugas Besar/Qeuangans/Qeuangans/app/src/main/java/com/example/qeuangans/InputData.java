package com.example.qeuangans;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class InputData extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText jenispemasukan, jmlpemasukan, jenispengeluaran, jmlpengeluaran;
    TextView tglpemasukan;
    Button inputmasuk;
    private DatePickerDialog.OnDateSetListener  setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        myDB = new DatabaseHelper(this);

        //CASTING
        jenispemasukan = findViewById(R.id.jenispemasukan);
        jmlpemasukan = findViewById(R.id.jmlpemasukan);
        jenispengeluaran = findViewById(R.id.jenispengeluaran);
        jmlpengeluaran = findViewById(R.id.jmlpengeluaran);
        tglpemasukan = findViewById(R.id.tglpemasukan);
        inputmasuk = findViewById(R.id.inputmasuk);

        //KALENDER
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tglpemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InputData.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                tglpemasukan.setText(date);
            }
        };
        //AKHIR KALENDER


        //PEMANGGILAN METHOD
        AddData();
    }

    //SCRIPT TAMBAH DATA DATABASE
    private void AddData() {
        inputmasuk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            boolean isInseted = myDB.insertData(jenispemasukan.getText().toString(),
                                    jmlpemasukan.getText().toString(), jenispengeluaran.getText().toString(),
                                    jmlpengeluaran.getText().toString(), tglpemasukan.getText().toString());
                            if (isInseted = true)
                                Toast.makeText(InputData.this, "INPUT BERHASIL", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(InputData.this, "INPUT GAGAL", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
