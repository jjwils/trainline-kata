package domain;

import java.util.Objects;

public record Route (String startStation, String destinationStation) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(startStation, route.startStation) && Objects.equals(destinationStation, route.destinationStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startStation, destinationStation);
    }
}







