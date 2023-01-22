package scheduler.domain;

public class Worker {
    private String name;
    private Boolean working;


    public Worker() {}



    public Worker(String name, Boolean working){
        setName(name);
        setWorking(working);
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getWorking() {
        return working;
    }

    public void setWorking(Boolean working) {
        this.working = working;
    }
}
