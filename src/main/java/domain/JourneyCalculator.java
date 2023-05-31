package domain;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JourneyCalculator {
    private final Timetable timetable;
    private Clock clock;

    private String result;


    public JourneyCalculator(Clock clock, Timetable timetable) {
        this.clock = clock;
        this.timetable = timetable;
    }

    public String getNextTrainTime(Journey journey) {

         List<JourneyDepartAndArrivalTime> journeyTimes = timetable.getJourneys(journey.startLocation(), journey.destination());

        for (JourneyDepartAndArrivalTime journeyTime:journeyTimes) {

            LocalTime currentTime = parseTime(clock.timeAsString());
            String departureTime = journeyTime.departureTime();
            if (currentTime.isBefore(parseTime(departureTime))) {
                result = journeyTime.departureTime() + " - " + journeyTime.arrivalTime();
                break;
            }

        }

        return result;

    }

    private LocalTime parseTime(String departureTime) {
        return LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
