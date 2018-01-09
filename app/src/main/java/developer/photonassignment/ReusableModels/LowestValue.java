package developer.photonassignment.ReusableModels;

/**
 * Created by sravan on 09-01-2018.
 */

public class LowestValue {

    private final GetterSetters coordinates;
    private final Integer value;

    public GetterSetters getCoordinates() {
        return coordinates;
    }

    public Integer getValue() {
        return value;
    }

    public LowestValue(GetterSetters coordinates, Integer value) {
        this.coordinates = coordinates;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().isAssignableFrom(this.getClass())) {
            return false;
        }
        LowestValue o1 = (LowestValue) o;
        return o1.getCoordinates().equals(this.getCoordinates()) &&
                o1.getValue().equals(this.getValue());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", coordinates, value);
    }
}
