package com.example.diyetteyiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Bilgilerim extends AppCompatActivity {

    private Button btnKaydet,btnBazal;
    private EditText txtYas, txtBoy,txtKilo;
    private TextView txtBazal;
    private Spinner spinner,spinCinsiyet;
    DBHelper DB;
    double bazal=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgilerim);

        btnKaydet = findViewById(R.id.btnKaydet);
        btnBazal = findViewById(R.id.btnBazal);
        txtYas = findViewById(R.id.txtYas);
        txtBoy = findViewById(R.id.txtBoy);
        txtKilo = findViewById(R.id.txtKilo);
        txtBazal = findViewById(R.id.txtBazal);
        DB = new DBHelper(this);

        spinCinsiyet = findViewById(R.id.spinCinsiyet);
        String[] arrayCinsiyet = new String[] {
                "Kadın",
                "Erkek",
        };
        Spinner s1 = (Spinner) findViewById(R.id.spinCinsiyet);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayCinsiyet);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        spinner = findViewById(R.id.spinner);
        String[] arraySpinner = new String[] {
                "Fazla hareket etmiyorum",
                "Az hareket ediyorum, Hafif egzersiz yapıyorum",
                "Orta derece hareket gerektiren bir iş yapıyorum",
                "Oldukça aktifim, Haftada 4 gün spor yapıyorum",
                "Aşırı düzeyde spor yapıyorum, Çok ağır işlerde çalışıyorum"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter2);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cinsiyet = spinCinsiyet.getSelectedItem().toString();
                Intent i = getIntent();
                String kullaniciAdi = i.getStringExtra("username");
                String ilk = i.getStringExtra("ilk");
                int yas = Integer.parseInt(txtYas.getText().toString());
                int boy = Integer.parseInt(txtBoy.getText().toString());
                int kilo = Integer.parseInt(txtKilo.getText().toString());
                String spin = spinner.getSelectedItem().toString();

                if (cinsiyet=="Erkek")
                {
                    bazal=(13.7*kilo)+(5*boy)+(6.8*yas)+66;
                }
                else{
                    bazal=(9.6*kilo)+(1.8*boy)+(5*yas)+655;
                }
                String secilen = spinner.getSelectedItem().toString();
                if (secilen=="Az hareket ediyorum, Hafif egzersiz yapıyorum"){bazal+=290;}
                else if(secilen=="Orta derece hareket gerektiren bir iş yapıyorum"){bazal+=580;}
                else if(secilen=="Oldukça aktifim, Haftada 4 gün spor yapıyorum"){bazal+=870;}
                else if(secilen=="Aşırı düzeyde spor yapıyorum, Çok ağır işlerde çalışıyorum"){bazal+=1160;}

                Boolean insert = DB.InsertUserInfo(kullaniciAdi, cinsiyet,yas,boy,kilo,spin,bazal);
                if (insert == true) {
                    Toast.makeText(Bilgilerim.this, "Bilgileriniz kaydedildi.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AnaSayfa.class);
                    startActivity(intent);
                }
                /*
                if (ilk=="0"){

                }
                    else{
                    Boolean update = DB.UpdateUserInfo(kullaniciAdi, cinsiyet,yas,boy,kilo,spin,bazal);
                    if (update == true) {
                        Toast.makeText(Bilgilerim.this, "Bilgileriniz güncellendi.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AnaSayfa.class);
                        startActivity(intent);
                    }
                }*/



            }
        });

        btnBazal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cinsiyet = spinCinsiyet.getSelectedItem().toString();
                int yas = Integer.parseInt(txtYas.getText().toString());
                int boy = Integer.parseInt(txtBoy.getText().toString());
                int kilo = Integer.parseInt(txtKilo.getText().toString());
                String secilen = spinner.getSelectedItem().toString();
                bazal = 0;
                    if (cinsiyet=="Erkek")
                    {
                        bazal=(13.7*kilo)+(5*boy)+(6.8*yas)+66;
                    }
                    else{
                        bazal=(9.6*kilo)+(1.8*boy)+(5*yas)+655;
                    }


                    if (secilen=="Az hareket ediyorum, Hafif egzersiz yapıyorum"){bazal+=290;}
                    else if(secilen=="Orta derece hareket gerektiren bir iş yapıyorum"){bazal+=580;}
                    else if(secilen=="Oldukça aktifim, Haftada 4 gün spor yapıyorum"){bazal+=870;}
                    else if(secilen=="Aşırı düzeyde spor yapıyorum, Çok ağır işlerde çalışıyorum"){bazal+=1160;}

                 txtBazal.setText(String.valueOf(bazal));
            }
        });


    }
}