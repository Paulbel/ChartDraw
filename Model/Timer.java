package Model;

/**
 * Created by Sinelnikov on 27.04.2017.
 */
public class Timer {
    private long startTime;
    private long finishTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public long getTimePast() {
        finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
