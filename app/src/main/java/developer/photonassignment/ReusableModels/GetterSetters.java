package developer.photonassignment.ReusableModels;

/**
 * Created by sravan on 09-01-2018.
 */

public class GetterSetters {
    private final int coordinateX;
    private final int coordinateY;

    public GetterSetters(int x, int y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }


    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().isAssignableFrom(obj.getClass())) return false;

        GetterSetters toCompare = (GetterSetters) obj;
        return this.coordinateX == toCompare.getCoordinateX() && this.coordinateY == toCompare.getCoordinateY();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", coordinateX, coordinateY);
    }
}
