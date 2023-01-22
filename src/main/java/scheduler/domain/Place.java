package scheduler.domain;

import java.util.List;

public class Place {
    private String name;
    private Integer maxNumberOfWorkers;
    private Integer maxManHours;




    public Place(){}


    public Place(String name, Integer maxNumberOfWorkers){
        setName(name);
        setMaxNumberOfWorkers(maxNumberOfWorkers);
        setMaxManHours(maxNumberOfWorkers*8);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getMaxNumberOfWorkers() {
        return maxNumberOfWorkers;
    }

    public void setMaxNumberOfWorkers(Integer maxNumberOfWorkers) {
        this.maxNumberOfWorkers = maxNumberOfWorkers;
    }

    public Integer getMaxManHours() {
        return maxManHours;
    }

    public void setMaxManHours(Integer maxManHours) {
        this.maxManHours = maxManHours;
    }
}
