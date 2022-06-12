package com.example.diyetteyiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SifreGuncelle extends AppCompatActivity {

    private Button btnGuncelle;
    private EditText txtEski, txtYeni,txtYeniden,txtKadi;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_guncelle);
        btnGuncelle = findViewById(R.id.btnGuncelle);
        txtEski = findViewById(R.id.txt1);
        txtYeni = findViewById(R.id.txt2);
        txtYeniden = findViewById(R.id.txt3);
        DB = new DBHelper(this);
        txtKadi = findViewById(R.id.txt4);

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eskisifre = txtEski.getText().toString();
                String yenisifre = txtYeni.getText().toString();
                String yeniden = txtYeniden.getText().toString();
                String kullanici = txtKadi.getText().toString();
                if (yenisifre.equals(yeniden)){
                    DB.UpdatePassword(kullanici,yenisifre);
                    Toast.makeText(SifreGuncelle.this, "Şifre güncellendi.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SifreGuncelle.this, "Hata.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}