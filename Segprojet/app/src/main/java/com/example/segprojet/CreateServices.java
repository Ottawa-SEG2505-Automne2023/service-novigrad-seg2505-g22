package com.example.segprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateServices extends AppCompatActivity implements ExampleDialog.ExampleDialogListener, ExampleDialog2.ExampleDialogListener {


    private Button elements;
    private Button documents;
    private Button submit;
    private EditText name;
    private EditText name2;
    private EditText name3;
    private String firstName="false";
    private String LastName = "false";
    private String DateOfBirth = "false";
    private String Adress = "false";
    private String LicenseType ="false";
    private String AdressProof = "false";
    private String ProofOfStatus = "false";
    private String Photo ="false";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("services");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_services);

        elements =(Button)findViewById(R.id.formElements);
        documents =(Button)findViewById(R.id.requiredDocuments);
        submit = (Button)findViewById(R.id.submitService);
        name =(EditText)findViewById(R.id.serviceName) ;
        name2 =(EditText)findViewById(R.id.test1) ;
        name3=(EditText)findViewById(R.id.test2) ;


        elements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog1();
            }
        });


        documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog2();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString()))
                {
                    name.setError("name required");
                    return;
                } else if(TextUtils.isEmpty(name2.getText().toString())){
                    name2.setText("Select Forms'elements");
                    return;
                } else if(TextUtils.isEmpty(name3.getText().toString())){
                    name3.setText("Select Required Documents");
                    return;
                } else {
                    Services service = new Services(name.getText().toString(),firstName,LastName,DateOfBirth,Adress,LicenseType,AdressProof,ProofOfStatus,Photo);


                    myRef.child(name.getText().toString()).setValue(service);

                    startActivity(new Intent(getApplicationContext(),Admin.class));
                }
            }
        });
    }

    private void OpenDialog2() {
        ExampleDialog2 exampleDialog = new ExampleDialog2();
        exampleDialog.show(getSupportFragmentManager(),"example Dialog");
    }

    public void OpenDialog1() {

        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example Dialog");
    }

    @Override
    public void whoIsChecked(boolean IsFirstNameChecked, boolean IsLastNameChecked, boolean IsDateOfBirthChecked, boolean IsAdressChecked, boolean IsLicenseTypeChecked) {
        String text ="Name "+IsLastNameChecked +" Surname "+IsFirstNameChecked+ " Date of birth "+ IsDateOfBirthChecked +" Adress "+ IsAdressChecked + " License Type "+IsLicenseTypeChecked;

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



        name2.setText(text);
    }

    @Override
    public void whoIsChecked(boolean IsFirstNameChecked, boolean IsLastNameChecked, boolean IsDateOfBirthChecked) {
        String text ="Adress Proof "+IsLastNameChecked +" Proof Of Status "+IsFirstNameChecked+ " Photo  "+ IsDateOfBirthChecked ;
        if(IsFirstNameChecked)
            AdressProof ="true";
        if (IsDateOfBirthChecked)
           Photo ="true";
        if (IsLastNameChecked)
            ProofOfStatus ="true";

        name3.setText(text);
    }
}