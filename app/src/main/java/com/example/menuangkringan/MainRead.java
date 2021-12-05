package com.example.menuangkringan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Menu> ListMenu = new ArrayList<Menu>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);

        adapter_off = new CustomListAdapter(this, ListMenu );
        mListView = (ListView) findViewById(R.id.list_menu_angkringan);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListMenu.clear();

        List<Menu> menu = db.ReadMenu();
        for (Menu mn : menu) {
            Menu daftar = new Menu();
            daftar.set_id(mn.get_id());
            daftar.set_nama(mn.get_nama());
            daftar.set_harga(mn.get_harga());
            ListMenu.add(daftar);

            if ((ListMenu.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Menu detailMhs = (Menu) o;

        String Sid = detailMhs.get_id();
        String Snama = detailMhs.get_nama();
        String Sharga = detailMhs.get_harga();

        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        startActivity(goUpdel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListMenu.clear();
        mListView.setAdapter(adapter_off);

        List<Menu> menu = db.ReadMenu();
        for (Menu mn : menu) {
            Menu daftar = new Menu();
            daftar.set_id(mn.get_id());
            daftar.set_nama(mn.get_nama());
            daftar.set_harga(mn.get_harga());
            ListMenu.add(daftar);

            if ((ListMenu.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}

