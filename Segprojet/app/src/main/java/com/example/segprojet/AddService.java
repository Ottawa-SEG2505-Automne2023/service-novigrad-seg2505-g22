package com.example.segprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddService extends AppCompatActivity implements ExampleDialog5.ExampleDialogListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("services");
    DatabaseReference myRef2 = database.getReference("succursales");
    ArrayList<String> listOfServices;
    ArrayList<Services> list;
    ArrayAdapter<String> adapter;
    Services service;
    private ListView listView;
    boolean confirm = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        service = new Services();
        listView = (ListView) findViewById(R.id.listView2);

        listOfServices = new ArrayList<>();
        adapter = new ArrayAdapter<String>(AddService.this, R.layout.service_info, R.id.printName, listOfServices);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    service = ds.getValue(Services.class);
                    listOfServices.add(service.getName()
                            + " \n First Name: " + service.getFirstName()
                            + " \n Last Name " + service.getLastName()
                            + " \n Date Of Birth:" + service.getDateOfBirth()
                            + " \n Adress:" + service.getAdress()
                            + " \n License Type" + service.getLicenseType()
                            + " \n Adress Proof:" + service.getAdressProof()
                            + " \n Photo:" + service.getPhoto()
                            + " \n Proof of Status:" + service.getProofOfStatus()
                    );
                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent =getIntent();
                String name2=intent.getStringExtra("SuccursaleName");



                    String user = listOfServices.get(position);
                    int firstSpacePosition = user.indexOf(" ");
                    String name = user.substring(0, firstSpacePosition);
                String name4=name;
                    Query checkUser = myRef.orderByChild("name").equalTo(name);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Services service =snapshot.child(name4).getValue(Services.class);
                            list.add(service);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                 String name3=name2;
                    Query checkUser2 = myRef2.orderByChild("name").equalTo(name2);
                Toast.makeText(AddService.this, "Service Ajouté", Toast.LENGTH_SHORT).show();

                    checkUser2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            snapshot.child(name3).child("ListOfServices").getRef().setValue(list);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });






                return false;
            }
        });

    }
        private void openDialog5 () {
            ExampleDialog5 exampleDialog = new ExampleDialog5();
            exampleDialog.show(getSupportFragmentManager(), "example Dialog");
        }

        @Override
        public void AddItem () {
            confirm = true;
        }
    }
