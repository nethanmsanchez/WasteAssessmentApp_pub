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


public class Login_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String TAG = "Login_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        final TextView userEmail = findViewById(R.id.emailField);
        final TextView userPassword = findViewById(R.id.passwordField);
        TextView goToRegister = findViewById(R.id.goToRegisterButton);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userSignin(userEmail.getText().toString(), userPassword.getText().toString());
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegister();
            }
        });

    }

    public void userSignin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void openRegister(){
        Intent intent = new Intent(this, Register_Activity.class);
        startActivity(intent);
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
}
