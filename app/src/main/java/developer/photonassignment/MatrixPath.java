package developer.photonassignment;

import java.util.ArrayList;
import java.util.List;

import developer.photonassignment.ReusableModels.GetterSetters;
import developer.photonassignment.ReusableModels.LowestValue;
import developer.photonassignment.ReusableModels.MatrixLeast;

/**
 * Created by sravan on 09-01-2018.
 */

public class MatrixPath {
    private final int maxCost;

    public MatrixPath() {
        this(Integer.MAX_VALUE);
    }

    public MatrixPath(int maxCost) {
        this.maxCost = maxCost;
    }


    public List<LowestValue> findPath(MatrixLeast leastMatrix) {
        List<LowestValue> bestPath = null;

        GetterSetters matrixTuple;
        for (int i = 0; i < leastMatrix.getHeight(); i++) {
            matrixTuple=new GetterSetters(1, i + 1);
            if(leastMatrix.getCost(matrixTuple)>maxCost) {
                continue;
            }
            List<LowestValue> currentPath = findPath(leastMatrix, matrixTuple, new ArrayList<LowestValue>());
            if (bestPath == null || sumPath(currentPath) < sumPath(bestPath)) {
                bestPath = currentPath;
            }
        }

        return bestPath;
    }

    // find the best path entry from UP/DOWN or RIGHT
    private List<LowestValue> findBestPath(List<LowestValue> up, List<LowestValue> right, List<LowestValue> down) {
        List<LowestValue> bestOfUpAndRight = bestOfTwo(up, right);
        return bestOfTwo(bestOfUpAndRight, down);
    }

    private List<LowestValue> findPath(MatrixLeast leastMatrix, GetterSetters matrixTuple, List<LowestValue> path) {
        if (matrixTuple == null) {
            return path;
        }

        List<LowestValue> currentPath = new ArrayList<>(path);
        int nextCost = leastMatrix.getCost(matrixTuple);

        if (sumPath(currentPath) + nextCost > maxCost || matrixTuple.getCoordinateX() > leastMatrix.getWidth()) {
            return currentPath;
        }
        currentPath.add(new LowestValue(matrixTuple, nextCost));

        List<LowestValue> upRight = findPath(leastMatrix, leastMatrix.getDiagonalUp(matrixTuple), currentPath);
        List<LowestValue> straightRight = findPath(leastMatrix, leastMatrix.getRight(matrixTuple), currentPath);
        List<LowestValue> downRight = findPath(leastMatrix, leastMatrix.getDiagonalDown(matrixTuple), currentPath);

        return findBestPath(upRight, straightRight, downRight);
    }
    private List<LowestValue> bestOfTwo(List<LowestValue> p1, List<LowestValue> p2) {
        if (p1.size() == p2.size()) {
            if (sumPath(p1) < sumPath(p2)) {
                return p1;
            }
            return p2;
        }

        if (p1.size() > p2.size()) {
            return p1;
        }
        return p2;
    }

    private int sumPath(List<LowestValue> path) {
        int sum = 0;
        for (int i = 0; i < path.size(); i++) {
            sum += path.get(i).getValue();
        }
        return sum;
    }

    public int getMaxCost() {
        return maxCost;
    }
}
