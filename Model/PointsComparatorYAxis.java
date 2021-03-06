package Model;

import java.awt.*;
import java.util.Comparator;

/**
 * Created by Sinelnikov on 23.04.2017.
 */
public class PointsComparatorYAxis implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        return p1.getY() >= p2.getY() ? 1 : 0;
    }
}
