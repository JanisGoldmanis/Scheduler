package scheduler.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.LinkedList;
import java.util.List;

@PlanningSolution
public class ElementSchedule {
    @PlanningEntityCollectionProperty
    private List<Assembly> assemblyList;
    @ValueRangeProvider(id = "places")
    @ProblemFactCollectionProperty
    private List<Place> placeList;
    @ValueRangeProvider(id = "shifts")
    @ProblemFactCollectionProperty
    private List<Shift> shiftList;
    @ValueRangeProvider(id = "queue")
    @ProblemFactCollectionProperty
    private List<PlaceInQueue> queueList;
    @ProblemFactCollectionProperty
    private List<Worker> workerList;
    @ProblemFactCollectionProperty
    private List<Element> elementList;
    @PlanningScore
    private HardSoftScore score;


    public ElementSchedule(){
        setAssemblyList(new LinkedList<>());
        setPlaceList(new LinkedList<>());
        setShiftList(new LinkedList<>());
        setWorkerList(new LinkedList<>());
        setElementList(new LinkedList<>());
        setQueueList(new LinkedList<>());
    }



    public List<Assembly> getAssemblyList() {
        return assemblyList;
    }

    public void setAssemblyList(List<Assembly> assemblyList) {
        this.assemblyList = assemblyList;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public List<PlaceInQueue> getQueueList() {
        return queueList;
    }

    public void setQueueList(List<PlaceInQueue> queueList) {
        this.queueList = queueList;
    }
}
