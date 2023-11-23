package com.example.segprojet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageServices extends AppCompatActivity implements ExampleDialog4.ExampleDialogListener {
    private ListView listView;
    private Button btnExit;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("services");
    ArrayList<String> listOfServices ;
    ArrayAdapter<String> adapter;
    Services service;
    private boolean confirm =false;
    private String firstName="false";
    private String LastName = "false";
    private String DateOfBirth = "false";
    private String Adress = "false";
    private String LicenseType ="false";
    private String AdressProof = "false";
    private String ProofOfStatus = "false";
    private String Photo ="false";
    private boolean confirm2 =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        service = new Services();
        listView =(ListView)findViewById(R.id.listView);
        btnExit = findViewById(R.id.btnExit);

        listOfServices =new ArrayList<>();
        adapter = new ArrayAdapter<String>(ManageServices.this, R.layout.service_info, R.id.printName, listOfServices);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren())
                {
                    service =ds.getValue(Services.class);
                    listOfServices.add(service.getName()
                            +" \n First Name: "+service.getFirstName()
                            +" \n Last Name "+service.getLastName()
                            +" \n Date Of Birth:"+ service.getDateOfBirth()
                            +" \n Adress:"+ service.getAdress()
                            + " \n License Type"+ service.getLicenseType()
                            +" \n Adress Proof:"+ service.getAdressProof()
                            +" \n Photo:"+ service.getPhoto()
                            +" \n Proof of Status:"+ service.getProofOfStatus()
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
                openDialog4();
                String user = listOfServices.get(position);
                int firstSpacePosition = user.indexOf(" ");
                final String name = user.substring(0, firstSpacePosition);

                if (confirm) {
                    // If the deletion confirmation is true
                    listOfServices.remove(position);
                    adapter.notifyDataSetChanged();
                    myRef.child(name).removeValue();
                    confirm = false; // Reset confirmation flag
                } else if (confirm2) {
                    // If the update confirmation is true
                    Services updatedService = new Services(name, firstName, LastName, DateOfBirth, Adress, LicenseType, AdressProof, ProofOfStatus, Photo);
                    listOfServices.remove(position);
                    adapter.notifyDataSetChanged();

                    // Perform the update in Firebase
                    myRef.child(name).setValue(updatedService).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Firebase update successful, no further action needed
                            } else {
                                // Handle the failure to update in Firebase
                                // You might want to add a log statement or show a Toast here
                                Log.e("ManageServices", "Error updating service in Firebase: " + task.getException());
                            }
                        }
                    });

                    confirm2 = false; // Reset confirmation flag
                }

                return true;
            }
        });





        // Handle the exit button click
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });
    }

    private void openDialog4() {
        ExampleDialog4 exampleDialog = new ExampleDialog4();
        exampleDialog.show(getSupportFragmentManager(),"example Dialog");
    }

    @Override
    public void whoIsChecked(boolean IsFirstNameChecked, boolean IsLastNameChecked, boolean IsDateOfBirthChecked, boolean IsAdressChecked, boolean IsLicenseTypeChecked, boolean IsAdressProofChecked, boolean IsProofOfStatusChecked, boolean IsPhotoChecked) {
        if(IsAdressChecked)
            Adress ="true";
        if (IsDateOfBirthChecked)
            DateOfBirth ="true";
        if (IsFirstNameChecked)
            firstName ="true";
        if(IsLastNameChecked)
            LastName ="true";
        if(IsLicenseTypeChecked)
            LicenseType ="true";
        if(IsFirstNameChecked)
            AdressProof ="true";
        if (IsDateOfBirthChecked)
            Photo ="true";
        if (IsLastNameChecked)
            ProofOfStatus ="true";

        confirm2=true;
    }

    @Override
    public void DeleteItem(){
        confirm =true;
    }
}
