package com.example.wasteassessment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ImageButton input_button = findViewById(R.id.input);
        input_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
            }
        });

        ImageButton edit_button = findViewById(R.id.edit);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

        ImageButton graph_button = findViewById(R.id.chart);
        graph_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart(v);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
            Intent login_intent = new Intent(this, Login_Activity.class);
            startActivity(login_intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflator = getMenuInflater();
        menuInflator.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Sign_Out:
                mAuth.signOut();
                Intent Login_intent = new Intent(this, Login_Activity.class);
                startActivity(Login_intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void input() {
        Intent intent = new Intent (this, Input_Activity.class);
        startActivity(intent);
    }

    public void edit() {
        Intent intent = new Intent (this, EditSelect_Activity.class);
        startActivity(intent);
    }

    public void chart(View view) {
        Intent intent = new Intent (this, Graph_Activity.class);
        startActivity(intent);
    }
    

}
