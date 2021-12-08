package com.example.wasteassessment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CompostFrag extends Fragment {
    private Button soiledPAC;
    private Button compostable_cups;
    private Button compostable_clamshells;
    private Button organics;
    private Button other4;
    private Button back;
    static private boolean soiledPACdone;
    static private boolean compostable_cupsdone;
    static private boolean compostable_clamshellsdone;
    static private boolean organicsdone;
    static private boolean other4done;
    private OnCompostFragClickListener listener;
    private String TAG = "CompostFrag.java: ";
    // ------------------- end of variables --------------------
    // ---------------------------------------------------------
    // --------------------- start methods ---------------------

    public interface OnCompostFragClickListener{
        public void onSoiledPACFragClick();
        public void onCompostablecupsFragClick();
        public void onCompostabeclamshellsFragClick();
        public void onOrganicsFragClick();
        public void onOther4FragClick();
        public void onBackCompostFragClick();
    }

    public void setOnCompostFragClickListener(OnCompostFragClickListener listener){
        this.listener = listener;
    }

    public static CompostFrag newInstance(boolean[] b){
        CompostFrag I = new CompostFrag();
        Bundle args = new Bundle();
        args.putBooleanArray("done", b);
        I.setArguments(args);
        return I;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.compost_frag, container, false);

        boolean[] b = getArguments().getBooleanArray("done");
        soiledPACdone = b[0];
        compostable_cupsdone = b[1];
        compostable_clamshellsdone = b[2];
        organicsdone = b[3];
        other4done = b[4];

        this.soiledPAC = view.findViewById(R.id.soiledPAC);
        if(soiledPACdone==true){
            soiledPAC.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        soiledPAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSoiledPACFragClick();
                //soiledPACdone = true;
            }
        });
        this.compostable_cups = view.findViewById(R.id.compostable_cups);
        if(compostable_cupsdone==true){
            compostable_cups.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        compostable_cups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCompostablecupsFragClick();
                //compostable_cupsdone = true;
            }
        });
        this.compostable_clamshells = view.findViewById(R.id.compostable_clamshells);
        if(compostable_clamshellsdone==true){
            compostable_clamshells.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        compostable_clamshells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCompostabeclamshellsFragClick();
                //compostable_clamshellsdone = true;
            }
        });
        this.organics = view.findViewById(R.id.organics);
        if(organicsdone==true){
            organics.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        organics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOrganicsFragClick();
                //organicsdone = true;
            }
        });
        this.other4 = view.findViewById(R.id.other4);
        if(other4done==true){
            other4.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        other4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOther4FragClick();
                //other4done = true;
            }
        });
        this.back = view.findViewById(R.id.compostbackbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBackCompostFragClick();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }
}

