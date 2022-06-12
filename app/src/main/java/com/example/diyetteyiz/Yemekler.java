package com.example.diyetteyiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Yemekler extends AppCompatActivity {

    private Spinner spin1,spin2,spin3;
    private Button btnEkle,btnHesapla;
    private TextView txtKalori;
    EditText txtMiktar;
    public double kalori;
    public String neYedim="bos";
    DBHelper DB;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemekler);
        btnEkle = findViewById(R.id.btnEkle);
        btnHesapla = findViewById(R.id.btnHesapla);
        txtMiktar = findViewById(R.id.txtMiktar);
        txtKalori = findViewById(R.id.txtKalori);
        spin1 = findViewById(R.id.spin1);
        spin2 = findViewById(R.id.spin2);
        spin3 = findViewById(R.id.spin3);
        DB = new DBHelper(this);

        String[] array = new String[] {
                "Yiyecek",
                "İçecek"
        };
        Spinner s = (Spinner) findViewById(R.id.spin1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        String[] array2 = new String[] {
                "Yemekler",
                "Deniz Mahsülleri",
                "Meyveler",
                "Et Ürünleri",
                "Süt Ürünleri",
        };
        Spinner s2 = (Spinner) findViewById(R.id.spin2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array2);



        String[] array3 = new String[] {
                "Kola",
                "Süt",
                "Ayran"
        };
        Spinner s3 = (Spinner) findViewById(R.id.spin2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array3);

        String[] array4 = new String[] {
                "Kuru Fasülye",
                "Bezelye",
                "Pişmiş Enginar",
                "Adana Kebap",
                "Lazanya",
        };
        Spinner s4 = (Spinner) findViewById(R.id.spin3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array4);

        String[] array5 = new String[] {
                "Hamsi Tava",
                "Somon",
                "Levrek"
        };
        Spinner s5 = (Spinner) findViewById(R.id.spin3);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array5);


        String[] array6 = new String[] {
                "Muz",
                "Elma",
                "Çilek"
        };
        Spinner s6 = (Spinner) findViewById(R.id.spin3);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array6);

        String[] array7 = new String[] {
                "-"
        };
        Spinner s7 = (Spinner) findViewById(R.id.spin3);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array7);


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spin1.getSelectedItem().toString()=="Yiyecek") {
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(adapter2);
                }
                else{
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s3.setAdapter(adapter3);
                    adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s7.setAdapter(adapter7);
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spin2.getSelectedItem().toString()=="Yemekler") {
                    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s4.setAdapter(adapter4);
                }
                else if (spin2.getSelectedItem().toString()=="Deniz Mahsülleri"){
                    adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s5.setAdapter(adapter5);
                }
                else if (spin2.getSelectedItem().toString()=="Meyveler"){
                    adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s6.setAdapter(adapter6);
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spin2.getSelectedItem().toString()=="Kola") {
                    kalori=37.5;
                    neYedim="Kola";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin2.getSelectedItem().toString()=="Süt"){
                    kalori=42.3;
                    neYedim="Süt";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin2.getSelectedItem().toString()=="Ayran"){
                    kalori=40.4;
                    neYedim="Ayran";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Kuru Fasülye"){
                    kalori=146;
                    neYedim="Kuru Fasülye";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Bezelye"){
                    kalori=81;
                    neYedim="Bezelye";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Pişmiş Enginar"){
                    kalori=53;
                    neYedim="Pişmiş Enginar";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Adana Kebap"){
                    kalori=239;
                    neYedim="Adana Kebap";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Lazanya"){
                    kalori=181;
                    neYedim="Lazanya";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Hamsi Tava"){
                    kalori=179;
                    neYedim="Hamsi Tava";txtKalori.setText(String.valueOf(kalori));
                }

                else if (spin3.getSelectedItem().toString()=="Somon"){
                    kalori=251;
                    neYedim="Somon";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Levrek"){
                    kalori=97;
                    neYedim="Levrek";txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Muz"){
                    kalori=88;
                    neYedim="Muz";
                    txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Elma"){
                    kalori=52;
                    neYedim="Elma";
                    txtKalori.setText(String.valueOf(kalori));
                }
                else if (spin3.getSelectedItem().toString()=="Çilek"){
                    kalori=32;
                    neYedim="Çilek";
                    txtKalori.setText(String.valueOf(kalori));
                }


            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                date = dateFormat.format(calendar.getTime());
                String trh = date.toString();

               Boolean insert = DB.InsertYediklerim(neYedim, String.valueOf(kalori));
                if (insert == true) {
                    Toast.makeText(Yemekler.this, "Eklendi.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AnaSayfa.class);
                    startActivity(intent);
                }

                    else{
                        Toast.makeText(Yemekler.this, "Hata.", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(txtKalori.getText().toString());
                double yuzde = Double.parseDouble(txtMiktar.getText().toString());
                double cal = (value)*(yuzde/100);
                kalori=cal;
                txtKalori.setText(String.valueOf(cal));
            }
        });



    }





}