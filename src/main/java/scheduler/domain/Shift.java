package scheduler.domain;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;


public class Shift {
    private DayOfWeek dayOfWeek;


    public Shift(){}


    public Shift(DayOfWeek dayOfWeek){
        setDayOfWeek(dayOfWeek);
    }


    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    public static List<Shift> generate_shifts() {
        List<Shift> shiftList = new LinkedList<>();
        shiftList.add(new Shift(DayOfWeek.MONDAY));
        shiftList.add(new Shift(DayOfWeek.TUESDAY));
        shiftList.add(new Shift(DayOfWeek.WEDNESDAY));
        shiftList.add(new Shift(DayOfWeek.THURSDAY));
        shiftList.add(new Shift(DayOfWeek.FRIDAY));
        return shiftList;
    }

}



