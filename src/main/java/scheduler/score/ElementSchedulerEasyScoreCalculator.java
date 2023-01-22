package scheduler.score;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;
import scheduler.domain.*;

public class ElementSchedulerEasyScoreCalculator implements EasyScoreCalculator<ElementSchedule, HardSoftScore> {
    @Override
    public HardSoftScore calculateScore(ElementSchedule elementSchedule) {
        int hardScore = 0, softScore = 0;

        // Check if sufficient manpower each day
        // Adding softScore if elements pushed to layer days
        for (Place place : elementSchedule.getPlaceList()){
            Integer day = 0;
            for (Shift shift : elementSchedule.getShiftList()){
                Integer availableManpower = place.getMaxManHours();
                Float spentManpower = (float) 0;
                for (Assembly assembly : elementSchedule.getAssemblyList()){
                    if (assembly.getShift() != null && assembly.getPlace() != null){
                        if (assembly.getPlace() == place && assembly.getShift() == shift){
                            spentManpower += assembly.getElement().getManHours();
                            softScore-= day;
                        }
                    }
                }
                if (spentManpower > availableManpower){
                    hardScore--;
                }
                day++;
            }
        }
        return HardSoftScore.of(hardScore, softScore);
    }
}
