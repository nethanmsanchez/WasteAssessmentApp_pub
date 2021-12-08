// WasteAssessmentApplication
// Date 5/19/2019
// Author: Nethaniel Sanchez

package com.example.wasteassessment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// A class below WasteAudit and a class above WasteStream. An example would be:
//
//      WasteType cans = new WasteType("cans", 15.00);
//      WasteType bottles = new WasteType("bottles", 15.15);
//      WasteStream Recyclables = new WasteStream("Recyclables");
//      Recyclables.addWasteType(cans);
//      Recyclables.addWasteType(bottles);
//      Bin bin = new Bin();
//      bin.addStream(Recyclables);
//      float ans = bin.getStreamWeight("Recyclables");
//
//        (ans == 30.15)


public class Bin {
    String location;
    String type;
    HashMap<String, WasteStream> streams;
    int _id;

    //------ constructor -----------
    public Bin(){
        this.location = "";
        String type = "";
        streams = new HashMap<String, WasteStream>();
        _id = -1;
    }

    // -----------------------      Methods     ------------------------

    // A function that takes a String as input, and returns the totalWeight
    // of the stream with the same name as the input. If no stream with
    // given name is not found returns -1.
//    public double getStreamWeight(String name){
//        WasteStream stream;
//        for(int i = 0; i < streams.size(); i++){
//            stream = streams.get(i);
//            if(stream.getName().equals(name)){
//                return stream.getTotalWeight();
//            }
//        }
//        return -1;
//    }

    // A function that takes a WasteStream object as input and adds it to
    // the streams field of the current Bin object.
    public void addStream(String name, WasteStream stream){
        this.streams.put(name, stream);
    }

    public void deleteStream(WasteStream w){
        this.streams.remove(w);
    }

    // A function that takes a String and sets the Location field to that
    // value.
    public void setLocation(String name){
        this.location = name;
    }

    // A function that takes a String and sets the Type field to that value.
    public void settype(String type){
        this.type = type;
    }

}
