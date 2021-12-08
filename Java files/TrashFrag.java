
package com.example.wasteassessment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TrashFrag extends Fragment {
    private Button plastic_bags;
    private Button wrappers;
    private Button non_compostable_cups;
    private Button plastic_containers;
    private Button other3;
    private Button back;
    static private boolean plastic_bagsdone;
    static private boolean wrappersdone;
    static private boolean non_compostable_cupsdone;
    static private boolean plastic_containersdone;
    static private boolean other3done;
    private OnTrashFragClickListener listener;
    private String TAG = "TrashFrag.java: ";
    // ------------------- end of variables --------------------
    // ---------------------------------------------------------
    // --------------------- start methods ---------------------

    public interface OnTrashFragClickListener{
        public void onPlasticbagsFragClick();
        public void onWrappersFragClick();
        public void onNoncompostablecupsFragClick();
        public void onPlasticcontainersFragClick();
        public void onOther3FragClick();
        public void onTrashBackFragClick();
    }

    public void setOnTrashFragClickListener(OnTrashFragClickListener listener){
        this.listener = listener;
    }

    public static TrashFrag newInstance(boolean[] b){
        TrashFrag I = new TrashFrag();
        Bundle args = new Bundle();
        args.putBooleanArray("done", b);
        I.setArguments(args);
        return I;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.trash_frag, container, false);

        boolean[] b = getArguments().getBooleanArray("done");
        plastic_bagsdone = b[0];
        wrappersdone = b[1];
        non_compostable_cupsdone = b[2];
        plastic_containersdone = b[3];
        other3done = b[4];

        this.plastic_bags = view.findViewById(R.id.plastic_bags);
        if(plastic_bagsdone==true){
            plastic_bags.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        plastic_bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlasticbagsFragClick();
                //plastic_bagsdone = true;
            }
        });
        this.wrappers = view.findViewById(R.id.wrappers);
        if(wrappersdone==true){
            wrappers.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        wrappers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onWrappersFragClick();
                //wrappersdone = true;
            }
        });
        this.non_compostable_cups = view.findViewById(R.id.non_compostable_cups);
        if(non_compostable_cupsdone==true){
            non_compostable_cups.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        non_compostable_cups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNoncompostablecupsFragClick();
                //non_compostable_cupsdone = true;
            }
        });
        this.plastic_containers = view.findViewById(R.id.plastic_containers);
        if(plastic_containersdone==true){
            plastic_containers.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        plastic_containers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlasticcontainersFragClick();
                //plastic_containersdone = true;
            }
        });
        this.other3 = view.findViewById(R.id.other3);
        if(other3done==true){
            other3.setBackground(getResources().getDrawable(R.drawable.dark_smaller_rounded_border));
        }
        other3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOther3FragClick();
                //other3done = true;
            }
        });
        this.back = view.findViewById(R.id.trashbackbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTrashBackFragClick();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }
}
