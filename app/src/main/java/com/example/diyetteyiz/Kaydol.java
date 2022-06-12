package com.example.diyetteyiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Kaydol extends AppCompatActivity {

    private Button button,button2;
    private EditText txtUser, txtPass,txtPassAgain;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        txtPassAgain = findViewById(R.id.txtPassAgain);
        DB = new DBHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String kadi = txtUser.getText().toString();
                String sifre = txtPass.getText().toString();
                String sifreTekrar = txtPassAgain.getText().toString();

                if (sifre.equals(sifreTekrar)){
                    Boolean checkuser = DB.checkUsername(kadi);
                    if (checkuser==false) {
                        Boolean insert = DB.InsertUser(kadi, sifre);
                        if (insert == true) {
                            Toast.makeText(Kaydol.this, "Başarıyla kaydoldunuz.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Bilgilerim.class);
                            intent.putExtra("ilk",0);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
            }
        });
    }
}