// WasteAssessmentApplication
// Date 5/19/2019
// Author: Nethaniel Sanchez

package com.example.wasteassessment2;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// The highest class in the hierarchy, directly above Bin. An example would be:
//
//      WasteType cans = new WasteType("cans", 15.00);
//      WasteType bottles = new WasteType("bottles", 15.15);
//      WasteStream Recyclables = new WasteStream("Recyclables");
//      Recyclables.addWasteType(cans);
//      Recyclables.addWasteType(bottles);
//      Bin bin = new Bin();
//      bin.addStream(Recyclables);
//      float ans = bin.getStreamWeight("Recyclables");
//      WasteAudit wasteAudit = new WasteAudit("myAudit");
//      wasteAudit.addBin(bin);
//
//

public class WasteAudit implements Serializable {
    private String date;
    private List<Bin> bins;
    private String type;
    private String location;
    private int _id;
    private String name;

    // -------- constructor ---------
    public WasteAudit(String name){
        this.name = name;
        this.bins = new ArrayList<>();
        this.date = "";
        this.type = "";
        this.location = "";
        this._id = -1;
    }

    // -------------------------        Methods       -----------------------------
    public void addBin(Bin bin){
        this.bins.add(bin);
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Bin> getBins(){
        return this.bins;
    }

    public String getName(){
        return this.name;
    }

    public String getDate(){
        return this.date;
    }

    public String getLocation(){
        return this.location;
    }

    public String getType(){
        return this.type;
    }
}
