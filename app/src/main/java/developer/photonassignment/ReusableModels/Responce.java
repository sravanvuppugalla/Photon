package developer.photonassignment.ReusableModels;

import java.util.List;

/**
 * Created by sravan on 09-01-2018.
 */

public class Responce {
    private final boolean completed;
    private final int totalCost;
    private final List<Integer> pathList;

    public Responce(boolean completed, int totalResistance, List<Integer> path) {
        this.completed = completed;
        this.totalCost = totalResistance;
        this.pathList = path;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public List<Integer> getPathList() {
        return pathList;
    }
}
