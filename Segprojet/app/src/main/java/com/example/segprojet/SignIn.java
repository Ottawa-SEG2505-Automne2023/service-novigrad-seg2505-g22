package com.example.segprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button signInBtn;
    private Button SignUpBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    private final String TAG = "nINO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

 // Initialise les composants de l'interface utilisateur
        username = (EditText) findViewById(R.id.enteredUsername);
        password = (EditText) findViewById(R.id.enteredPassword);
        signInBtn = (Button) findViewById(R.id.SignInBtn);
        SignUpBtn = (Button) findViewById(R.id.SignUpBtn);


// Définir d'un clic listener pour le bouton "Sign Up"

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

// Définir d'un clic listener pour le bouton "Sign In"
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
 // Obtenir le nom d'utilisateur et le mot de passe saisis
                String Username =username.getText().toString().trim();
                String Password = password.getText().toString().trim();
                
// Vérifie si les informations d'identification correspondent à celles de l'administrateur
                if (Username.equals("admin")&& (Password.equals("123admin456")))
                {
                    startActivity(new Intent(getApplicationContext(),Admin.class));
                }
// Vérifie si le nom d'utilisateur ou le mot de passe est vide
                if (TextUtils.isEmpty(Username)){
                    username.setText("username required");
                    return;
                } else if (TextUtils.isEmpty(Password)){
                    password.setText("password required");
                    return;
                } else {
                    // Read from the database
                    // whenever data at this location is updated.                        }

 // Interroger la base de données Firebase pour vérifier si l'utilisateur existe
                    Query checkUser = myRef.orderByChild("username").equalTo(Username);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
    // Le nom d'utilisateur existe dans la base de données
                                username.setError(null);
// Récupère le mot de passe stocké dans la base de données
                                String passwordFromDB = snapshot.child(Username).child("password").getValue(String.class);
// Vérifie si le mot de passe saisi correspond au mot de passe stocké
                                if (passwordFromDB.equals(Password)) {
                                    password.setError(null);
// Récupère les autres informations de l'utilisateur depuis la base de données
                                    String emailFromDB = snapshot.child(Username).child("email").getValue(String.class);
                                    String nameFromDB = snapshot.child(Username).child("name").getValue(String.class);
                                    String typeOfAccountFromDB = snapshot.child(Username).child("typeOfAccount").getValue(String.class);
                                    String SuccursaleNameFromDB =snapshot.child(Username).child("SuccursaleName").getValue(String.class);
             //  transmets les détails de l'utilisateur à d'autres activités
                                    Intent intent1 = new Intent(getApplicationContext(), Client.class);
                                    Intent intent = new Intent(getApplicationContext(), Employee.class);

                                    intent.putExtra("name", nameFromDB);
                                    intent.putExtra("username", Username);
                                    intent.putExtra("password", Password);
                                    intent.putExtra("email", emailFromDB);
                                    intent.putExtra("typeOfAccount", typeOfAccountFromDB);
                                    intent.putExtra("SuccurssaleName",SuccursaleNameFromDB);

                                    intent1.putExtra("name", nameFromDB);
                                    intent1.putExtra("username", Username);
                                    intent1.putExtra("password", Password);
                                    intent1.putExtra("email", emailFromDB);
                                    intent1.putExtra("typeOfAccount", typeOfAccountFromDB);
            
             // Démarre l'activité appropriée en fonction du type de compte de l'utilisateur
                                    if (typeOfAccountFromDB.equals("employee")||typeOfAccountFromDB.equals("Employee"))
                                        startActivity(intent);
                                    else
                                        startActivity(intent1);
                                } else {
                                    password.setError("wrong password");
                                    password.requestFocus();
                                }

                            } else{
                                username.setError("no such user exist");
                                username.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
    }
}
