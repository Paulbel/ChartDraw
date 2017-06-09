package Model;

import Controller.ChartDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Sinelnikov on 27.04.2017.
 */
public class RandomNElementsSorter extends Thread {
    private List timeSpent;
    private Random random;
    private int maxArrayLength;
    private int step;
    private int iterationNumber;
    private ChartDraw controller;
    private int MINSORTINGNUMBER = 2;

    public void run() {
        int iterations = ((/*(int) Math.ceil*/(maxArrayLength - MINSORTINGNUMBER) / step) + 1) * iterationNumber;
        int currentIteration = 0;
        timeSpent = new ArrayList<>();
        for (int currentLength = MINSORTINGNUMBER; currentLength < maxArrayLength; currentLength = currentLength + step) {
            for (int iteration = 0; iteration < iterationNumber; iteration++) {
                if (!this.isInterrupted()) {
                    currentIteration++;
                    double progress = ((double) currentIteration / iterations) * 100;
                    controller.updateProgress((int) Math.floor(progress));
                    int[] array = new int[currentLength];
                    for (int index = 0; index < currentLength; index++) {
                        array[index] = random.nextInt(Integer.MAX_VALUE);
                    }
                    Sorting sorting = new Sorting(array);
                    sorting.sort();
                    timeSpent.add(sorting.getTimeSpent());
                } else return;
            }
            long allTime = 0;
            for (Iterator iterator = timeSpent.iterator(); iterator.hasNext(); ) {
                allTime = allTime + (long) iterator.next();
            }
            int averageTime = (int) (allTime / timeSpent.size());
            timeSpent.clear();
            Point point = new Point(currentLength, averageTime);
            controller.addPointToList(point);
        }
    }


    public RandomNElementsSorter(int maxArrayLength, List<Point> pointList, int step, ChartDraw controller, int iterationNumber) {
        random = new Random();
        this.controller = controller;
        this.maxArrayLength = maxArrayLength;
        this.step = step;
        this.iterationNumber = iterationNumber;
        this.timeSpent = pointList;
    }

}
