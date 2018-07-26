package com.kartikeya.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editPassword;
    Spinner spinnerType;
    Button buttonUpload;
    ListView userlistview;

    List<users> userslist;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init() {

    editTextName = (EditText) findViewById(R.id.editText);
    editPassword = (EditText) findViewById(R.id.editText2);
    spinnerType = (Spinner) findViewById(R.id.spinner);
    buttonUpload =(Button) findViewById(R.id.button);

    userlistview = (ListView) findViewById(R.id.userlist);

    userslist = new ArrayList<>();

    buttonUpload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addUser();
        }
    });

    databaseUser = FirebaseDatabase.getInstance().getReference("users");

    }

    private void addUser(){

        String name = editTextName.getText().toString().trim();
        String permissions = spinnerType.getSelectedItem().toString();
        String password = editPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){

            String ID = databaseUser.push().getKey();

            users user = new users(ID, name, password, permissions);

            databaseUser.child(ID).setValue(user);
            Toast.makeText(this, "User Added", Toast.LENGTH_LONG).show();

        } else{
            Toast.makeText(this, "Enter name and password", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userslist.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    users user = postSnapshot.getValue(users.class);

                    userslist.add(user);
                }

                UserList userAdapter = new UserList(MainActivity.this, userslist);
                userlistview.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

