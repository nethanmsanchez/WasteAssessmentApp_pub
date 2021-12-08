package com.example.wasteassessment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StreamsFrag extends Fragment {
    private Button recyclables;
    private Button trash;
    private Button compost;
    private Button other;
    private Button back;
    private Button done;
    private OnStreamFragClickListener listener;
    private String TAG = "StreamsFrag.java: ";
    // ------------------- end of variables --------------------
    // ---------------------------------------------------------
    // --------------------- start methods ---------------------

    public interface OnStreamFragClickListener{
        public void onRecycleFragClick();
        public void onTrashFragClick();
        public void onCompostFragClick();
        public void onOtherFragClick();
        public void onBackStreamFragClick();
        public void onDoneStreamFragClick();
    }

    public void setOnStreamFragClickListener(OnStreamFragClickListener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.streams_frag, container, false);

        this.recyclables = view.findViewById(R.id.recyclebutton);
        recyclables.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.onRecycleFragClick();
            }
        });
        this.trash = view.findViewById(R.id.trashbutton);
        trash.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.onTrashFragClick();
            }
        });
        this.compost = view.findViewById(R.id.compostbutton);
        compost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.onCompostFragClick();
            }
        });
        this.other = view.findViewById(R.id.otherbutton);
        other.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.onOtherFragClick();
            }
        });
        this.back = view.findViewById(R.id.streamsbackbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBackStreamFragClick();
            }
        });
        this.done = view.findViewById(R.id.steamsdonebutton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDoneStreamFragClick();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
    }

}














