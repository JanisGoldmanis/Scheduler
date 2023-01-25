package scheduler.domain;


import java.util.ArrayList;


public class Element {
    private String deliveryNumber;
    private ArrayList<Float> manHours;
    private Float totalManHours;

    public Element(){}


    public Element(String deliveryNumber, ArrayList<Float> manHours){
        setDeliveryNumber(deliveryNumber);
        setManHours(manHours);
        setTotalManHours(generateTotalManHours(manHours));

    }



    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public ArrayList<Float> getManHours() {
        return manHours;
    }

    public void setManHours(ArrayList<Float> manHours) {
        this.manHours = manHours;
    }

    public Float generateTotalManHours(ArrayList<Float> manHours){
        Float totalManHours = (float) 0;
        for (Float manHour : manHours) {
            totalManHours += manHour;
        }
        return totalManHours;
    }

    public Float getTotalManHours() {
        return totalManHours;
    }

    public void setTotalManHours(Float totalManHours) {
        this.totalManHours = totalManHours;
    }
}
