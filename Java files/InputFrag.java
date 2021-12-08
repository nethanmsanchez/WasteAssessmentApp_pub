package com.example.wasteassessment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class InputFrag extends Fragment{
    private TextView tv;
    private EditText weight;
    private EditText count;
    private EditText volume;
    private List<String> plist;
    private Button fb;
    private Button bb;
    private OnFragClickListener listener;
    private static String TAG = "DEbug";
    private static String entry;
    private Double cou;
    private Double vol;
    private Double wei;

    public  interface OnFragClickListener{
        public void onBackFragClick(String s);
        public void onForwardFragClick(double w, double c, double v, String s);
    }

    public void setOnFragClickListener(OnFragClickListener listener){
        this.listener = listener;
    }

    public static InputFrag newInstance(String str, double c, double v, double w){
        Log.i(TAG, "Step 2, here in newInstance");
        InputFrag I = new InputFrag();
        Bundle args = new Bundle();
        entry = str;
        args.putString("entry", str);
        if(c!=-1) {
            args.putDouble("count", c);
            args.putDouble("volume", v);
            args.putDouble("weight", w);
        }
        I.setArguments(args);
        return I;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // Inflate layout for fragment
        View view = inflater.inflate(R.layout.input_frag, container, false);
        //ConstraintLayout layout = view.findViewById(R.id.frag_container);
        cou = getArguments().getDouble("count");
        vol = getArguments().getDouble("volume");
        wei = getArguments().getDouble("weight");
        //add textview, EditText, foreward, and backward button
        this.tv = view.findViewById(R.id.tv);
        this.weight = view.findViewById(R.id.weight);
        if(wei != 0) {
            if(wei != -1) {
                weight.setText(wei.toString());
            }
        }
        this.count = view.findViewById(R.id.count);
        if(cou != 0) {
            if(cou != -1) {
                count.setText(cou.toString());
            }
        }
        this.volume = view.findViewById(R.id.volume);
        if(vol != 0) {
            if(vol != -1) {
                volume.setText(vol.toString());
            }
        }
        this.fb = view.findViewById(R.id.fb);
        this.bb = view.findViewById(R.id.bb);
        this.count.requestFocus();

        //view.setBackgroundColor(getResources().getColor(R.color.gold));

        Log.i(TAG, "Step 3, In InputFrag.java");
        tv.setText(getArguments().getString("prompt"));
        fb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i(TAG, "leaving frag: " + getArguments().getInt("index")+ ", message: " + count.getText().toString());
                listener.onForwardFragClick(getText(count), getText(weight), getText(volume), entry);
            }
        });
        bb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.onBackFragClick(entry);
                Log.i(TAG, "leaving frag: " + getArguments().getInt("index")+ ", message: " + count.getText().toString());
            }
        });
        Log.i(TAG, "Step 4, tv.get:  " + tv.getText());
        //layout.addView(tv);
        //Log.i(TAG, "Step 5, got passed layout.addView(tv)");
        //layout.addView(et);
        //layout.addView(fb);
        //layout.addView(bb);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
    }

    public void setFrag(String userPrompt){
        this.tv.setText(userPrompt);
    }

    public void setPromps(List<String> list){
        this.plist = list;
    }

    public double getText(EditText et){
        String temp = et.getText().toString();
        if(temp.equals("")){
            return -1;
        }
        double d = Double.parseDouble(temp);
        return d;
    }



}
