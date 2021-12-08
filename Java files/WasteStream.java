// WasteAssessmentApplication
// Date 5/19/2019
// Author: Nethaniel Sanchez
package com.example.wasteassessment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// A class below Bin and above WasteType in class hierarchy. An example would be:
//
//      WasteType cans = new WasteType("cans", 15.00);
//      WasteType bottles = new WasteType("bottles", 15.15);
//      WasteStream Recyclables = new WasteStream("Recyclables");
//      Recyclables.addWasteType(cans);
//      Recyclables.addWasteType(bottles);
//      float ans = Recyclables.gettotalWeight();
//
//       (ans == 30.15)
//


public class WasteStream {
    private String name;
    private HashMap<String, Entry> entries;
//    private double totalWeight;

    //--------- constructor ----------
    public WasteStream(String name){
 //       this.totalWeight = 0;
        this.name = name;
        entries = new HashMap<String, Entry>();
    }



    // -------------------------------------   methods   ----------------------------------------
    // A function that takes a WasteType object as input, and adds it
    // to the wasteTypes List field. Also updates totalWeight field.
    public void addWasteType(String name, Entry entry){
        this.entries.put(name, entry);
      //  this.setTotalWeight( this.getTotalWeight() + entry.getWeight());
    }

    // A function that returns the total weight of all the wasteType objects
    // in the wasteTypes List field.
//    public double getTotalWeight(){
//        return this.totalWeight;
//    }

    // A function that takes a double as input, and sets the totalWeight
    // field to that value.
//    public void setTotalWeight(double d){
//        this.totalWeight = d;
//    }

    // A function that returns the name of the WasteStream
    public String getName(){
        return this.name;
    }

    public HashMap<String, Entry> getEntries(){
        return this.entries;
    }

    public void deleteEntry(Entry e){
        this.entries.remove(e);
    }

}
