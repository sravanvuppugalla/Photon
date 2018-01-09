package developer.photonassignment;

import java.util.ArrayList;
import java.util.List;

import developer.photonassignment.ReusableModels.LowestValue;
import developer.photonassignment.ReusableModels.MatrixLeast;
import developer.photonassignment.ReusableModels.Responce;

/**
 * Created by sravan on 09-01-2018.
 */

public class MatrixPathSum {

    private final MatrixPath mPath;

    public MatrixPathSum(MatrixPath mPath) {
        this.mPath = mPath;
    }


    public Responce calculate(MatrixLeast leastMatrix) throws Exception {
        List<LowestValue> path = mPath.findPath(leastMatrix);

        return new Responce(path.size() == leastMatrix.getWidth(), sumPath(path), pathToRowNumbers(path));
    }


    private List<Integer> pathToRowNumbers(List<LowestValue> path) {
        List<Integer> pathAsRowNumbers = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            pathAsRowNumbers.add(path.get(i).getCoordinates().getCoordinateY());
        }
        return pathAsRowNumbers;
    }


    private int sumPath(List<LowestValue> path) {
        int sum = 0;
        for (int i = 0; i < path.size(); i++) {
            sum += path.get(i).getValue();
        }
        return sum;
    }
}
