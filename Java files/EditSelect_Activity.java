package com.example.wasteassessment2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditSelect_Activity extends AppCompatActivity {

    Spinner nameSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editselect);

        nameSpinner = findViewById(R.id.name_Spinner);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final Query orderByNames = myRef.orderByChild("name");
        orderByNames.addListenerForSingleValueEvent(populateNameSpinner());

        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Query getVals = myRef.orderByChild("name").equalTo(name);
                getVals.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getChildrenCount()==1){
                            for(DataSnapshot i: dataSnapshot.getChildren()){
                                String date = i.child("date").getValue().toString();
                                String location = i.child("location").getValue().toString();
                                String type = i.child("type").getValue().toString();
                                ((TextView)findViewById(R.id.editSelect_Date)).setText(date);
                                ((TextView)findViewById(R.id.editSelect_Location)).setText(location);
                                ((TextView)findViewById(R.id.editSelect_Type)).setText(type);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public ValueEventListener populateNameSpinner(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               final List<String> names = new ArrayList<>();
               for(DataSnapshot i: dataSnapshot.getChildren()){
                   String name = i.child("name").getValue().toString();
                   names.add(name);
               }

               ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(EditSelect_Activity.this,
                        android.R.layout.simple_spinner_item, names);
               arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               nameSpinner.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

}
