package com.example.jp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    EditText pass;
    Button admi;
    Button uploadcontribute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        pass=(EditText)findViewById(R.id.password);
        admi=(Button)findViewById(R.id.admin);
        uploadcontribute = (Button) findViewById(R.id.Contribute);
        uploadcontribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,Test.class);
                startActivity(intent);
            }
        });
        admi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),pass.getText().toString(),Toast.LENGTH_SHORT).show();
                if(pass.getText().toString().trim().equals("admin123")) {
                    Intent next = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(next);
                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid Credential",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
