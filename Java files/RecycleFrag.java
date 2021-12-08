package com.example.wasteassessment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RecycleFrag extends Fragment {
    private Button cleanPAC;
    private Button cans;
    private Button pbj;
    private Button glass_bottles;
    private Button other2;
    private Button back;
    private OnRecycleFragClickListener listener;
    private String TAG = "StreamsFrag.java: ";
    static private boolean cleanPACdone;
    static private boolean cansdone;
    static private boolean pbjdone;
    static private boolean glass_bottlesdone;
    static private boolean other2done;
    // ------------------- end of variables --------------------
    // ---------------------------------------------------------
    // --------------------- start methods ---------------------

    public interface OnRecycleFragClickListener{
        public void onCleanPACFragClick();
        public void onCansFragClick();
        public void onPbjFragClick();
        public void onGlassbottlesFragClick();
        public void onOther2FragClick();
        public void onRecycleBackFragClick();
    }

    public void setOnRecycleFragClickListener(OnRecycleFragClickListener listener){
        this.listener = listener;
    }

    public static RecycleFrag newInstance(boolean[] b){
        RecycleFrag I = new RecycleFrag();
        Bundle args = new Bundle();
        args.putBooleanArray("done", b);
        I.setArguments(args);
         return I;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.recycle_frag, container, false);

        boolean[] b = getArguments().getBooleanArray("done");
        cleanPACdone = b[0];
        cansdone = b[1];
        pbjdone = b[2];
        glass_bottlesdone = b[3];
        other2done = b[4];

        cleanPAC = view.findViewById(R.id.cleanPAC);
        if(cleanPACdone==true){
            cleanPAC.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        cleanPAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cleanPACdone = true;
                listener.onCleanPACFragClick();
            }
        });
        this.cans = view.findViewById(R.id.cans);
        if(cansdone==true){
            cans.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        cans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cansdone = true;
                listener.onCansFragClick();
            }
        });
        this.pbj = view.findViewById(R.id.pbj);
        if(pbjdone==true){
            pbj.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        pbj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pbjdone = true;
                listener.onPbjFragClick();
            }
        });
        this.glass_bottles = view.findViewById(R.id.glass_bottles);
        if(glass_bottlesdone==true){
            glass_bottles.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        glass_bottles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //glass_bottlesdone = true;
                listener.onGlassbottlesFragClick();
            }
        });
        this.other2 = view.findViewById(R.id.other2);
        if(other2done==true){
            other2.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        other2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //other2done = true;
                listener.onOther2FragClick();
            }
        });
        this.back = view.findViewById(R.id.recyclebackbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecycleBackFragClick();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }
}
