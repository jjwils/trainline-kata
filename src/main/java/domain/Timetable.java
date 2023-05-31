package domain;

import java.util.HashMap;
import java.util.List;

public class Timetable {

    private HashMap<Route, List<JourneyDepartAndArrivalTime>> timetable = new HashMap<>();

    public void createTimetable(Route route, List<JourneyDepartAndArrivalTime> travelTimes) {
        timetable.put(route, travelTimes);
    }

    public HashMap<Route, List<JourneyDepartAndArrivalTime>> getTimetable() {
        return timetable;
    }

    List<JourneyDepartAndArrivalTime> getJourneys(String startLocation, String destination) {
        return getTimetable().get(startLocation + "-" + destination);
    }
}