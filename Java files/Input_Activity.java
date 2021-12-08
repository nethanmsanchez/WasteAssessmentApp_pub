package com.example.wasteassessment2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Input_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String TAG = "Input_Activity";
    private EditText datetext;

    WasteAudit wasteAudit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button enter = (Button) findViewById(R.id.Enter_button);
        datetext = findViewById(R.id.date);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //= database.getReference("First Message");

        wasteAudit = new WasteAudit("Audit4");
        Timestamp ts = new Timestamp((new Date()).getTime());
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String timestamp = format.format(ts);
        wasteAudit.setDate(timestamp);

        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String location = ((Spinner)findViewById(R.id.location)).getSelectedItem().toString();
                Entry paper = getPaperEntry();
                Entry cans = getCansEntry();
                Entry glass = getGlassEntry();
                Entry plastic = getPlasticEntry();
                Entry other = getOtherEntry();

                WasteStream recyclables = new WasteStream("Recyclables");
                recyclables.addWasteType("Paper", paper);
                recyclables.addWasteType("Cans", cans);
                recyclables.addWasteType("Glass", glass);
                recyclables.addWasteType("Plastic", plastic);
                recyclables.addWasteType("Other", other);

                Bin bin = new Bin();
                bin.addStream("Recyclables", recyclables);


                wasteAudit.setLocation(location);
                wasteAudit.setType("Bin Based");
                wasteAudit.addBin(bin);

                Timestamp ts = new Timestamp((new Date()).getTime());
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String timestamp = format.format(ts);
                format.applyPattern("yyyyMMddhhmmssSSS");
                String title = format.format(ts);

                DatabaseReference myRef = database.getReference();
                myRef.child(title).setValue(wasteAudit);
            }
        });
    }




    public Entry getPaperEntry(){
        int count = Integer.parseInt(((TextView)findViewById(R.id.paper_count))
                .getText().toString());
        int weight = Integer.parseInt(((TextView)findViewById(R.id.paper_lbs))
                .getText().toString());
        int volume = Integer.parseInt(((TextView)findViewById(R.id.paper_gallons))
                .getText().toString());

        Entry ret = new Entry(count, weight, volume);
        return ret;
    }

    public Entry getCansEntry() {

        int count = Integer.parseInt(((TextView)findViewById(R.id.cans_count))
                .getText().toString());
        int weight = Integer.parseInt(((TextView)findViewById(R.id.cans_lbs))
                .getText().toString());
        int volume = Integer.parseInt(((TextView)findViewById(R.id.cans_gallons))
                .getText().toString());

        Entry ret = new Entry(count, weight, volume);
        return ret;
    }

    public Entry getGlassEntry(){
        int count = Integer.parseInt(((TextView)findViewById(R.id.glass_count))
                .getText().toString());
        int weight = Integer.parseInt(((TextView)findViewById(R.id.glass_lbs))
                .getText().toString());
        int volume = Integer.parseInt(((TextView)findViewById(R.id.glass_gallons))
                .getText().toString());

        Entry ret = new Entry(count, weight, volume);
        return ret;
    }

    public Entry getPlasticEntry(){
        int count = Integer.parseInt(((TextView)findViewById(R.id.plastic_count))
                .getText().toString());
        int weight = Integer.parseInt(((TextView)findViewById(R.id.plastic_lbs))
                .getText().toString());
        int volume = Integer.parseInt(((TextView)findViewById(R.id.plastic_gallons))
                .getText().toString());

        Entry ret = new Entry(count, weight, volume);
        return ret;
    }

    public Entry getOtherEntry(){
        int count = Integer.parseInt(((TextView)findViewById(R.id.other_count))
                .getText().toString());
        int weight = Integer.parseInt(((TextView)findViewById(R.id.other_lbs))
                .getText().toString());
        int volume = Integer.parseInt(((TextView)findViewById(R.id.other_gallons))
                .getText().toString());

        Entry ret = new Entry(count, weight, volume);
        return ret;
    }

    public void date(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        ++month;
        String date;
        if(month<10){
            date = "0" + month + "/" + dayOfMonth + "/" + year;
        }else {
            date = month + "/" + dayOfMonth + "/" + year;
        }
        datetext.setText(date);
        wasteAudit.setDate(date);
    }

}
