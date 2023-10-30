package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private TextView welcomeTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        welcomeTextView = findViewById(R.id.welcomeTextView);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(WelcomeActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success
                                FirebaseUser user = mAuth.getCurrentUser();
                                welcomeTextView.setText("Welcome, " + user.getEmail());
                                welcomeTextView.setVisibility(View.VISIBLE);
                            } else {
                                // Sign in fail
                                welcomeTextView.setText("Authentication failed.");
                                welcomeTextView.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });
    }
}
