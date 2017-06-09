package Model;

/**
 * Created by Sinelnikov on 27.04.2017.
 */
public class Sorting {
    private int[] array;
    private long timeSpent;

    public void sort() {
        Timer timer = new Timer();
        TreeArraySort treeArraySort = new TreeArraySort();
        timer.start();
        treeArraySort.sortArray(array);
        timeSpent = timer.getTimePast();
    }

    public Sorting(int[] array) {
        this.array = array;
    }

    public long getTimeSpent() {
        return timeSpent;
    }
}
