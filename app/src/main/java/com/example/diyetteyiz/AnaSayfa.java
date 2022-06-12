package com.example.diyetteyiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AnaSayfa extends AppCompatActivity {
    private Button btnListele;
    private TextView txtTarih,txtHedef,txtYuzde;
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private ProgressBar pb;
    private ArrayList<String> listitem;
    ListView foodList;
    DBHelper DB;
    ArrayAdapter adapterr;
    double toplamKalori=0;

    String kadi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        txtTarih = (TextView)findViewById(R.id.txtTarih);
        txtHedef = findViewById(R.id.txtHedef);
        txtYuzde = findViewById(R.id.txtYuzde);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        txtTarih.setText(date.toString());
        pb = findViewById(R.id.progressBar);
        pb.setProgress(0);
        DB = new DBHelper(this);
       // btnListele = findViewById(R.id.btnListele);

        Intent i = getIntent();
        String kadi = i.getStringExtra("username");



        //viewdata();


    }

    private void viewdata() {
        DB = new DBHelper(this);
        Cursor cursor = DB.viewYediklerim();
        if (cursor.getCount()==0){
            Toast.makeText(AnaSayfa.this, "Data yok.", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                listitem.add(cursor.getString(1));
            }
            adapterr = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listitem);
            foodList.setAdapter(adapterr);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        switch (id){
            case R.id.bilgilerim:
                Intent intent = new Intent(getApplicationContext(), Bilgilerim.class);
                intent.putExtra("ilk",1);
                startActivity(intent);
                break;
            case R.id.kaloriEkle:
                Intent intent3 = new Intent(getApplicationContext(), Yemekler.class);
                startActivity(intent3);
                break;
            case R.id.yediklerim:
                toplamKalori=0;
                Cursor res2 =DB.bazalMetabolizma();
                double bzl=0;
                while (res2.moveToNext())
                {
                     bzl = Double.parseDouble(res2.getString(0));
                }
                Cursor res =DB.viewYediklerim();
                if (res.getCount()==0)
                {

                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append(res.getString(0)+"\t  ");
                    buffer.append(res.getString(1)+"\n");
                    toplamKalori += Double.parseDouble((res.getString(1)+"\n"));
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AnaSayfa.this);
                builder.setCancelable(true);
                builder.setTitle("Yediklerim");
                builder.setMessage(buffer.toString());
                builder.show();
                txtHedef.setText(String.valueOf("Aldığınız Kalori: "+toplamKalori+"/"+bzl));
                int value = (int)((toplamKalori/bzl)*100);
                txtYuzde.setText("%"+String.valueOf(value));
                pb.setProgress(value);
                break;
            case R.id.sifreGuncelle:
                Intent intent4 = new Intent(getApplicationContext(), SifreGuncelle.class);
                intent4.putExtra("username",kadi);
                startActivity(intent4);
                break;
            case R.id.yediklerimiSil:
                Boolean res3 =DB.deleteYediklerim();
                if (res3==true){
                    Toast.makeText(AnaSayfa.this, "Başarıyla silindi.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}