package com.example.jp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewFiles extends AppCompatActivity {

    RecyclerView recyclerview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploaded_files);

        String value= getIntent().getStringExtra("getData");
        String value1= getIntent().getStringExtra("getData1");

        //Toast.makeText(ViewFiles.this,value,Toast.LENGTH_SHORT).show();
        //Toast.makeText(ViewFiles.this,value1,Toast.LENGTH_SHORT).show();

        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Links/"+value+"/"+value1);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String filename=dataSnapshot.getKey();
                String url=dataSnapshot.getValue(String.class);
                //String url="https://firebasestorage.googleapis.com/v0/b/bookbank-3c3f9.appspot.com/o/Books%2F1535547522133?alt=media&token=102f48a4-abb4-4589-bdde-6fc1de71a836";
                ((MyAdapter)recyclerview.getAdapter()).update(filename,url);
               // Intent intent = new Intent(ViewFiles.this,Test.class);
                //Toast.makeText(ViewFiles.this,url,Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(ViewFiles.this,Test.class);
                //intent.putExtra("Sendurl", url);
                //startActivity(intent);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerview = findViewById(R.id.UploadedFiles);

        recyclerview.setLayoutManager(new LinearLayoutManager(ViewFiles.this));
        MyAdapter myAdapter=new MyAdapter(recyclerview,ViewFiles.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerview.setAdapter(myAdapter);
    }
}
