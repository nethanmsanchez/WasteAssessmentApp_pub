package com.example.wasteassessment2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Activity extends AppCompatActivity {

    String TAG = "Register_Activity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        TextView goToLoginTV = findViewById(R.id.goToLoginButton);
        final TextView userEmail = findViewById(R.id.emailField);
        final TextView userPassword = findViewById(R.id.passwordField);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registerUser(userEmail.getText().toString(), userPassword.getText().toString());
            }
        });

        goToLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToLoginActivity();
            }
        });

    }

    public void registerUser(String email, String password){
        if(!email.contains("@ucsc.edu")){
            ((TextView) findViewById(R.id.textSignInStatus)).setText(
                    "Error: Please use a UCSC email");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(Register_Activity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser user){
        if(user != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            ((TextView) findViewById(R.id.textSignInStatus)).setText(
                    "Error: Sign-in Failed");
        }
    }

    public void  goToLoginActivity() {
        Intent intent = new Intent(this, Login_Activity.class);
        startActivity(intent);
    }
}
