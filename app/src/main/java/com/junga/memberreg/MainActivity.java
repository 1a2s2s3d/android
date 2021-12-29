package com.junga.memberreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnRegister;
    EditText edtId, edtPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("signup");

        btnRegister = (Button) findViewById(R.id.register_btn);
        edtId = (EditText) findViewById(R.id.register_id);
        edtPw = (EditText) findViewById(R.id.register_pw);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String result;
                    String id = edtId.getText().toString();
                    String pw = edtPw.getText().toString();
                    Toast.makeText(MainActivity.this, "signup button push" + id + ":" + pw, Toast.LENGTH_SHORT).show();

                    // use class create
                    RegisterActivity task = new RegisterActivity();
                    result = task.execute(id, pw).get();

                    //result message
                    Toast.makeText(MainActivity.this, "signup result :" + result, Toast.LENGTH_SHORT).show();

                    Log.d("DB Connect", "Success---");
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"DB Error....", Toast.LENGTH_SHORT).show();
                    Log.d("DB Connect", "Error ---");
                }
            }
        });

    }
}