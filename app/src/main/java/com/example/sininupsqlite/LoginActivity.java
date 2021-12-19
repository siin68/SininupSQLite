package com.example.sininupsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username1, password1;
    Button  btnsingin1;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);
        btnsingin1 = (Button) findViewById(R.id.btnsingin1);
        DB = new DBhelper(this);

        btnsingin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username1.getText().toString();
                String pass = password1.getText().toString();

                if (user.equals("") || pass.equals("") )
                    Toast.makeText(LoginActivity.this, "VUI LONG DIEN DAY DU THONG TIN", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if (checkuserpass == true) {

                        Toast.makeText(LoginActivity.this, "DANG NHAP THANH CONG", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "THONG TIN SAI", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}