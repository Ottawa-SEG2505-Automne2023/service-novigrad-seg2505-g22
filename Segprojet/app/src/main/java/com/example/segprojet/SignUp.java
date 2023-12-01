package com.example.segprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText newName;
    EditText newUsername;
    EditText newPassword;
    EditText newEmail;
    RadioButton client;
    RadioButton employee;
    Button signUpBtn;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);

        newName = findViewById(R.id.newName);
        newUsername = findViewById(R.id.newUsername);
        newPassword = findViewById(R.id.newPassword);
        newEmail = findViewById(R.id.newEmail);
        client = findViewById(R.id.radioButton);
        employee = findViewById(R.id.radioButton2);
        signUpBtn = findViewById(R.id.signUp);

        myRef = FirebaseDatabase.getInstance().getReference("users");

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = newEmail.getText().toString().trim();
                String password = newPassword.getText().toString().trim();
                final String name = newName.getText().toString().trim();
                final String username = newUsername.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username)) {
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Sauvegarde des informations de l'utilisateur dans la base de données Firebase Realtime
                String typeOfAccount = client.isChecked() ? "client" : "Employee";
                User userData = new User(username, email, password, typeOfAccount, name, "");
                myRef.child(username).setValue(userData);

                Toast.makeText(SignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
        });
    }
}
