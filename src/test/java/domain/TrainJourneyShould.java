package domain;

import domain.Journey;
import domain.JourneyCalculator;
import domain.Timetable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TrainJourneyShould {

    @Mock
    Clock clock;
    private Timetable timetable;

    @BeforeEach
    void setup(){
         timetable = new Timetable();
         timetable.createTimetable(new Route("London","Manchester"), Arrays.asList(
                 new JourneyDepartAndArrivalTime("10:00", "11:00"),
                 new JourneyDepartAndArrivalTime("11:00", "13:00"))
         );


    }

    @Test
    void calculateNextTrainTime() {
        //arrange
        given(clock.timeAsString()).willReturn("09:59");
        JourneyCalculator journeyCalculator = new JourneyCalculator(clock, timetable);

        //act
        String result = journeyCalculator.getNextTrainTime(new Journey("London", "Manchester"));

        //assert
        assertEquals("10:00 - 11:00", result);
    }

    @Test
    void calculateTheNextTrainTimeWhenFirstTrainPassed() {
        //arrange
        given(clock.timeAsString()).willReturn("10:00");
        JourneyCalculator journeyCalculator = new JourneyCalculator(clock, timetable );

        //act
        String result = journeyCalculator.getNextTrainTime(new Journey("London", "Manchester"));

        //assert
        assertEquals("11:00 - 13:00", result);
    }


}
