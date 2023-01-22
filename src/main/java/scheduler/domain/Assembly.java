package scheduler.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Assembly {
    private Element element;
    @PlanningVariable(valueRangeProviderRefs = "shifts")
    private Shift shift;
    @PlanningVariable(valueRangeProviderRefs = "places")
    private Place place;




    public Assembly(){}


    public Assembly(Element element, Shift shift, Place place){
//        setWorkers(workers);
        setElement(element);
        setTimeSlot(shift);
        setPlace(place);
//        setID(id);
    }


//    public List<Worker> getWorkers() {
//        return workers;
//    }
//
//    public void setWorkers(List<Worker> workers) {
//        this.workers = workers;
//    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Shift getShift() {
        return shift;
    }

    public void setTimeSlot(Shift shift) {
        this.shift = shift;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

//    public String getID() {
//        return id;
//    }
//
//    public void setID(String ID) {
//        this.id = ID;
//    }
}
