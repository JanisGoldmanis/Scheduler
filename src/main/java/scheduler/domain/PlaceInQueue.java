package scheduler.domain;

public class PlaceInQueue {
    private int placeInQueue;


    public PlaceInQueue(){}


    public PlaceInQueue(int placeInQueue){
        setPlaceInQueue(placeInQueue);
    }



    public int getPlaceInQueue() {
        return placeInQueue;
    }

    public void setPlaceInQueue(int placeInQueue) {
        this.placeInQueue = placeInQueue;
    }
}
