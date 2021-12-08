// WasteAssessmentApplication
// Date 5/19/2019
// Author: Nethaniel Sanchez

package com.example.wasteassessment2;



// The lowest class in the hierarchy. An example would be:
//
//           WasteType cans = new WasteType("cans", 15.06);
//
// If a bin/stream has 15.06 pounds of cans.


public class WasteType {
    private String name;
    private double weight;

    // ------------- constructor ---------------
    public WasteType(String name, double weight){
        this.name = name;
        this.weight = weight;
    }

    // A function that takes a double as input and changes the weight
    // field of a WasteType object to that value.
    public void setWeight(double w){
        this.weight = w;
    }

    // A function that returns the weight field of a WasteType object.
    public double getWeight(){
        return this.weight;
    }
}
