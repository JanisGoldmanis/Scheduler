package scheduler.domain;


import org.optaplanner.core.api.domain.entity.PlanningEntity;


public class Element {
    private String deliveryNumber;
    private Float manHours;

    public Element(){}


    public Element(String deliveryNumber, Float manHours){
        setDeliveryNumber(deliveryNumber);
        setManHours(manHours);
    }



    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public Float getManHours() {
        return manHours;
    }

    public void setManHours(Float manHours) {
        this.manHours = manHours;
    }
}
