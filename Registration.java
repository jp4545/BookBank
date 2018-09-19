package com.example.jp.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private EditText fullname;
    private EditText email;
    private EditText password;
    private Button signup;
    private TextView loginlink;
    DatabaseReference firebaserealtime;
    FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fullname = (EditText) findViewById(R.id.reg_fullname);
        email = (EditText) findViewById((R.id.reg_email));
        password = (EditText) findViewById(R.id.reg_password);
        signup = (Button) findViewById(R.id.btnRegister);
        loginlink = (TextView) findViewById(R.id.link_to_login);
        firebaserealtime = FirebaseDatabase.getInstance().getReference("signup");
        firebaseauth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adduser();
            }
        });
        loginlink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(next);
            }
        });
    }

    private void adduser() {
        String Fullname = fullname.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Fullname)) {
            Toast.makeText(this, "Please enter fullname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Email) || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //registeruserauth(Email, Password);
            String id = firebaserealtime.push().getKey();
            Adduser user = new Adduser(Fullname, Email, Password);
            firebaserealtime.child(id).setValue(user);
            firebaseauth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = firebaseauth.getCurrentUser();
                                Toast.makeText(Registration.this, "User added successfully", Toast.LENGTH_SHORT).show();
                                Intent nextact = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(nextact);
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Registration.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

    /*private void registeruserauth(String Email,String Password) {
        firebaseauth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                            Intent nextact = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(nextact);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

    });
}*/
}
