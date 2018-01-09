package developer.photonassignment.ReusableModels;

import java.util.List;

/**
 * Created by sravan on 09-01-2018.
 */

public class MatrixLeast {
    private final List<List<Integer>> lowestCostMatrix;

    public MatrixLeast(List<List<Integer>> lowestCostMatrix) {
        this.lowestCostMatrix = lowestCostMatrix;
    }

    public GetterSetters getRight(GetterSetters matrixCells) {
        if (matrixCells.getCoordinateX() + 1 > lowestCostMatrix.get(matrixCells.getCoordinateY() - 1).size())
            return null;

        return new GetterSetters(matrixCells.getCoordinateX() + 1, matrixCells.getCoordinateY());
    }

    public GetterSetters getDiagonalUp(GetterSetters matrixCells) {
        GetterSetters right = getRight(matrixCells);
        if (right == null)
            return null;
        int y = matrixCells.getCoordinateY() - 1;
        return new GetterSetters(right.getCoordinateX(), y == 0 ? lowestCostMatrix.size() : y);
    }


    public GetterSetters getDiagonalDown(GetterSetters matrixCells) {
        GetterSetters right = getRight(matrixCells);
        if (right == null)
            return null;
        int y = matrixCells.getCoordinateY() + 1;
        return new GetterSetters(right.getCoordinateX(), y > lowestCostMatrix.size() ? 1 : y);
    }


    public int getCost(GetterSetters matrixCells) {
        return lowestCostMatrix.get(matrixCells.getCoordinateY() - 1).get(matrixCells.getCoordinateX() - 1);
    }

    public int getWidth() {
        return lowestCostMatrix.get(0).size();
    }

    public int getHeight() {
        return lowestCostMatrix.size();
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().isAssignableFrom(this.getClass())) {
            return false;
        }
        MatrixLeast o1 = (MatrixLeast) o;

        for (int i = 0; i < o1.lowestCostMatrix.size(); i++) {
            List<Integer> thisRow = this.lowestCostMatrix.get(i);
            List<Integer> compareRow = o1.lowestCostMatrix.get(i);

            for (int x = 0; x < compareRow.size(); x++) {
                if (thisRow.get(x) != compareRow.get(x)) {
                    return false;
                }
            }
        }
        return true;
    }


}
