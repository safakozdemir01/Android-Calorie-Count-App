package com.example.diyetteyiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public String userNameGenel;
    private Button btnGiris,btnKaydol;
    private EditText txtKadi, txtSifre;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGiris = findViewById(R.id.btnGir);
        btnKaydol =  findViewById(R.id.btnKaydol);
        txtKadi = findViewById(R.id.txtKadi);
        txtSifre = findViewById(R.id.txtSifre);
        DB = new DBHelper(this);

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String kadi = txtKadi.getText().toString();
                String sifre = txtSifre.getText().toString();
                Boolean kontrol = DB.checkUsernamePassword(kadi,sifre);
                if(kontrol==true){
                    userNameGenel=kadi;
                    Intent intent = new Intent(getApplicationContext(), AnaSayfa.class);
                    intent.putExtra("username",userNameGenel);
                    startActivity(intent);
                }
            }
        });
        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent2 = new Intent(getApplicationContext(), Kaydol.class);
                    startActivity(intent2);
            }
        });





    }

}