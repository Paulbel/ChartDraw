package Model;

import Controller.ChartDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sinelnikov on 01.05.2017.
 */
public class PointList {
    private List<Point> pointList;
    private ChartDraw controller;
    private RandomNElementsSorter randomNElementsSorter;

    public PointList(ChartDraw controller) {
        this.controller = controller;
        pointList = new ArrayList<>();
    }

    public void addPoint(Point point) {
        pointList.add(point);
        Collections.sort(pointList, new PointsComparatorXAxis());
    }

    public List<Point> getPointList() {
        return pointList;
    }


    public void makeNewPointListFromSort(int maxNumber, int step, int iterations) {
        pointList.clear();
        if (randomNElementsSorter != null) {
            randomNElementsSorter.interrupt();
        }
        randomNElementsSorter = new RandomNElementsSorter(maxNumber, pointList, step, controller, iterations);
        randomNElementsSorter.start();
    }


}
