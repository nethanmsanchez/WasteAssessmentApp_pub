package com.example.wasteassessment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OtherFrag extends Fragment {
    private Button hazardous;
    private Button batteries;
    private Button ewaste;
    private Button wood;
    private Button back;
    static private boolean hazardousdone;
    static private boolean batteriesdone;
    static private boolean ewastedone;
    static private boolean wooddone;
    private OnOtherFragClickListener listener;
    private String TAG = "CompostFrag.java: ";
    // ------------------- end of variables --------------------
    // ---------------------------------------------------------
    // --------------------- start methods ---------------------

    public interface OnOtherFragClickListener{
        public void onHazardousFragClick();
        public void onBatteriesFragClick();
        public void onEwasteFragClick();
        public void onWoodFragClick();
        public void onOtherBackFragClick();
    }

    public void setOnOtherFragClickListener(OnOtherFragClickListener listener){
        this.listener = listener;
    }

    public static OtherFrag newInstance(boolean[] b){
        OtherFrag I = new OtherFrag();
        Bundle args = new Bundle();
        args.putBooleanArray("done", b);
        I.setArguments(args);
        return I;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.other_frag, container, false);

        boolean[] b = getArguments().getBooleanArray("done");
        hazardousdone = b[0];
        batteriesdone = b[1];
        ewastedone = b[2];
        wooddone = b[3];

        this.hazardous = view.findViewById(R.id.hazardous);
        if(hazardousdone==true){
            hazardous.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        hazardous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHazardousFragClick();
                //hazardousdone = true;
            }
        });
        this.batteries = view.findViewById(R.id.batteries);
        if(batteriesdone==true){
            batteries.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        batteries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBatteriesFragClick();
                //batteriesdone = true;
            }
        });
        this.ewaste = view.findViewById(R.id.ewaste);
        if(ewastedone==true){
            ewaste.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        ewaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEwasteFragClick();
                //ewastedone = true;
            }
        });
        this.wood = view.findViewById(R.id.wood);
        if(wooddone==true){
            wood.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onWoodFragClick();
                //wooddone = true;
            }
        });
        this.back = view.findViewById(R.id.otherbackbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOtherBackFragClick();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }
}

