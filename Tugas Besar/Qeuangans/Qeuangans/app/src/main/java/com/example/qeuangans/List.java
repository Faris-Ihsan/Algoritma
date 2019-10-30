package com.example.qeuangans;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    private static final String TAG = "List";

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DatabaseHelper(this);

        tampilkanData();
    }

    public void tampilkanData() {
        Cursor cursor = db.tampilkanData();
        final SwipeMenuListView listView = findViewById(R.id.listView);
        final ArrayList<String> listitem = new ArrayList<>();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "TIDAK ADA DATA", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listitem.add("\n"+ cursor.getString(0)+"\n" + "Tanggal : " + cursor.getString(5) + "\n" + "\n" + "Pemasukan : " + cursor.getString(1)
                        + "\n" + "Jumlah : Rp " + cursor.getString(2) + ",00" + "\n" + "\n" + "Pengeluaran : " + cursor.getString(3)
                        + "\n" + "Jumlah : Rp" + cursor.getString(4) + ",00" + "\n");
            }
            final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listitem);
            listView.setAdapter(adapter);

            SwipeMenuCreator creator = new SwipeMenuCreator() {

                @Override
                public void create(SwipeMenu menu) {
                    // create "open" item
                    SwipeMenuItem openItem = new SwipeMenuItem(
                            getApplicationContext());
                    // set item background
                    openItem.setBackground(new ColorDrawable(Color.rgb(255, 33,
                            33)));
                    // set item width
                    openItem.setWidth(170);
                    // set item title
                    openItem.setTitle("Hapus");
                    // set item title fontsize
                    openItem.setTitleSize(18);
                    // set item title font color
                    openItem.setTitleColor(Color.WHITE);
                    // add to menu
                    menu.addMenuItem(openItem);
                }
            };
            listView.setMenuCreator(creator);



            listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                    switch (index) {
                        case 0:
                            Toast.makeText(List.this, "INPUT BERHASIL", Toast.LENGTH_LONG).show();
                        }
                    // false : close the menu; true : not close the menu
                    return false;
                }
            });
        }
    }
}
