package com.example.sininupsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button btnsingup, btnsingin;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        btnsingin = (Button) findViewById(R.id.btnsingin);
        btnsingup = (Button) findViewById(R.id.btnsingup);
        DB = new DBhelper(this);


        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this,"Vui Long Dien Day Du Thong Tin",Toast.LENGTH_SHORT).show();

                else {
                if (pass.equals(repass)){
                    Boolean checkuser = DB.checkusername(user);
                    if (checkuser == false) {

                        Boolean insert = DB.insertData(user,pass);
                        if (insert == true ){
                            Toast.makeText(MainActivity.this,"DANG KY THANH CONG ",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "DANG KY LOI", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "TEN NGUOI DUNG DA CO, VUI LONG CHON TEN KHAC", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Khong Tim Thay Mat Khau", Toast.LENGTH_SHORT).show();
                }

                }


            }
        });
        btnsingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}