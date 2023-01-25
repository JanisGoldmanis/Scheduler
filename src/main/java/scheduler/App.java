package scheduler;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scheduler.CSV.ReadCSV;
import scheduler.domain.*;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("Scheduler start!");

        SolverFactory<ElementSchedule> solverFactory = SolverFactory.create(SolverConfig.createFromXmlResource("scheduler/solverConfig.xml"));

        String elementsInputPath = "/Users/Janis/IdeaProjects/Scheduler/src/main/resources/inputs/Places1.csv";
//        String elementsInputPath = "/Users/janis.goldmanis/IdeaProjects/Scheduler/src/main/resources/inputs/Places1.csv";
        String placesInputPath = "/Users/Janis/IdeaProjects/Scheduler/src/main/resources/inputs/ElementData.csv";
//        String placesInputPath = "/Users/janis.goldmanis/IdeaProjects/Scheduler/src/main/resources/inputs/Elements1.csv";
        ElementSchedule problem = generateData(elementsInputPath, placesInputPath);

        Solver<ElementSchedule> solver = solverFactory.buildSolver();
        ElementSchedule solution = solver.solve(problem);

        printSchedule(solution);
    }
    public static ElementSchedule generateData(String elementsInputPath, String placesInputPath) {
        List<Shift> shiftList = Shift.generate_shifts();


        List<Place> placeList = new LinkedList<>();
        ReadCSV readCSV = new ReadCSV();
        String[][] placeData = readCSV.read(elementsInputPath);
        for(int i=1;i<placeData.length;i++){
            String placeName = placeData[i][0];
            Integer maxNumberOfWorkers = Integer.parseInt(placeData[i][1]);
            Place place = new Place(placeName, maxNumberOfWorkers);
            placeList.add(place);
        }

        List<PlaceInQueue> queueList = new LinkedList<>();
        int queue = 0;
        List<Element> elementList = new LinkedList<>();
        ReadCSV readCSV2 = new ReadCSV();
        String[][] elementData = readCSV2.read(placesInputPath);
        for(int i=1;i<elementData.length;i++){
            String deliveryNumber = elementData[i][0];
            ArrayList<Float> manHours = new ArrayList<>();
            for (int column=1;column<elementData[i].length;column++){
                manHours.add(Float.parseFloat(elementData[i][column]));
            }
            Element element = new Element(deliveryNumber, manHours);
            elementList.add(element);
            queueList.add(new PlaceInQueue(queue));
            queue++;
        }

        List<Assembly> assemblyList = new LinkedList<>();
        for (Element element : elementList){
            assemblyList.add(new Assembly(element,null , null , null));
        }
        ElementSchedule schedule = new ElementSchedule();
        schedule.setShiftList(shiftList);
        schedule.setPlaceList(placeList);
        schedule.setElementList(elementList);
        schedule.setAssemblyList(assemblyList);
        schedule.setQueueList(queueList);
        return schedule;
    }


    private static String center_string(String string, Integer spaces){
        Integer stringLength = string.length();
        Integer back = (spaces - stringLength)/2;
        int forward = spaces - back - stringLength;
        String spacesForward = new String(new char[forward]).replace("\0", " ");
        String spacesBack = new String(new char[back]).replace("\0", " ");
        return spacesForward + string + spacesBack;
    }


    private static void printSchedule(ElementSchedule schedule) {

        int tabSize = 20;

        StringBuilder manHours;
        for (Shift shift : schedule.getShiftList()) {
            String shiftString = shift.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
            LOGGER.info("");
            LOGGER.info(shiftString);

            List<List<Assembly>> shiftAssemblyList = new LinkedList<>();
            int longestList = 0;

            StringBuilder header = new StringBuilder("|");
            for (Place place : schedule.getPlaceList()) {
                header.append(center_string(place.getName(), tabSize)).append("|");
                List<Assembly> placeAssemblyList = new LinkedList<>();
                for (Assembly assembly : schedule.getAssemblyList()) {
                    if (assembly.getShift() == shift && assembly.getPlace() == place) {
                        placeAssemblyList.add(assembly);
                    }
                }
                if (placeAssemblyList.size() > longestList) {
                    longestList = placeAssemblyList.size();
                }
                shiftAssemblyList.add(placeAssemblyList);
            }
            LOGGER.info(header.toString());

            int placeCount = schedule.getPlaceList().size();
            for (int i = 0; i < longestList; i++) {
                StringBuilder row = new StringBuilder("|");
                for (int j = 0; j < placeCount; j++) {
                    try {
                        row.append(center_string(shiftAssemblyList.get(j).get(i).getElement().getDeliveryNumber()+" "+ shiftAssemblyList.get(j).get(i).getQueue().getPlaceInQueue(), tabSize)).append("|");
                    } catch (IndexOutOfBoundsException e) {
                        row.append(center_string("", tabSize)).append("|");
                    }
                }
                LOGGER.info(row.toString());
            }
            List<Float> manPowerList = new LinkedList<>();
            for (List<Assembly> assemblyList : shiftAssemblyList) {
                float manPower = 0;
                for (Assembly assembly : assemblyList) {
                    manPower += assembly.getElement().getTotalManHours();
                }
                manPowerList.add(manPower);
            }
            List<Integer> availableManPowerList = new LinkedList<>();
            for (Place place : schedule.getPlaceList()) {
                availableManPowerList.add(place.getMaxManHours());
            }
            manHours = new StringBuilder("|");
            for (int i = 0; i < placeCount; i++) {
                String manHoursString = manPowerList.get(i) +"/"+ availableManPowerList.get(i);
                manHours.append(center_string(manHoursString, tabSize)).append("|");
            }
            LOGGER.info(manHours.toString());
        }
    }
}