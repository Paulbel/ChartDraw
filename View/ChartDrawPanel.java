package View;


import Model.PointsComparatorXAxis;
import Model.PointsComparatorYAxis;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by Sinelnikov on 23.04.2017.
 */
public class ChartDrawPanel extends JPanel {
    private int PREF_W = 1100;
    private int PREF_H = 900;
    private int BORDER_GAP = 55;
    private Color GRAPH_COLOR = Color.blue;
    private Color GRAPH_POINT_COLOR = Color.black;
    private Stroke GRAPH_STROKE = new BasicStroke(3f);
    private int GRAPH_POINT_WIDTH = 10;
    private int Y_HATCH_CNT = 10;
    private int FONT_SIZE = 10;
    private double zoom;
    private List<Point> points;


    public ChartDrawPanel(List<Point> points) {
        this.points = points;
        this.points.sort(new PointsComparatorXAxis());
        this.zoom = 0.5;
        this.setVisible(true);
    }


    public void setZoom(double zoom) {
        if (zoom < 0.5) {
            this.zoom = 0.5;
        } else if (zoom > 1.5) {
            this.zoom = 1.5;
        } else {
            this.zoom = zoom;
        }
    }

    public double getZoom() {
        return zoom;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (points.size() > 0) {
            double maxNumberOfElements = findStep((int) Collections.max(points, new PointsComparatorXAxis()).getX());
            double maxTime = findStep((int) Collections.max(points, new PointsComparatorYAxis()).getY());
            int border_gap = (int) (BORDER_GAP * zoom);
            int hatchLength = (int) (GRAPH_POINT_WIDTH * zoom);
            double xScale = (((double) getWidth() - 2 * border_gap) / maxNumberOfElements);
            double yScale = (((double) getHeight() - 2 * border_gap) / maxTime);
            int fontSize;
            if (zoom < 3) {
                fontSize = (int) (FONT_SIZE * zoom);
            } else {
                fontSize = FONT_SIZE * 3;
            }
            List<Point> zoomedPointsList = new ArrayList<>();

            for (Point point : points) {
                Point newPoint = new Point();
                newPoint.x = (int) (border_gap + point.x * xScale);
                newPoint.y = (int) (getHeight() - point.y * yScale - border_gap);
                zoomedPointsList.add(newPoint);
            }

            g2.drawLine(border_gap, getHeight() - border_gap, border_gap, border_gap);
            g2.drawLine(border_gap, getHeight() - border_gap, getWidth() - border_gap, getHeight() - border_gap);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

            double hatchNumber = maxTime / 10;


            for (int index = 0; index < Y_HATCH_CNT; index++) {
                int x0 = border_gap - hatchLength / 2;
                int x1 = x0 + hatchLength;
                int y0 = getHeight() - (((index + 1) * (getHeight() - border_gap * 2)) / Y_HATCH_CNT + border_gap);
                g2.drawLine(x0, y0, x1, y0);
                g2.drawString(String.valueOf(hatchNumber * (index + 1)), 0, y0 + fontSize / 2);
            }

            hatchNumber = maxNumberOfElements / 10;
            for (int index = 0; index < Y_HATCH_CNT; index++) {
                int x0 = (index + 1) * (getWidth() - border_gap * 2) / Y_HATCH_CNT + border_gap;
                int y0 = getHeight() - border_gap + hatchLength / 2;
                int y1 = y0 - hatchLength;
                g2.drawLine(x0, y0, x0, y1);
                g2.drawString(String.valueOf((int) hatchNumber * (index + 1)), x0 - fontSize / 2, y0 + border_gap / 2);
            }
            g2.drawString("0", border_gap, getHeight()-border_gap/2);
            Stroke oldStroke = g2.getStroke();
            g2.setColor(GRAPH_COLOR);
            g2.setStroke(GRAPH_STROKE);
            for (int i = 0; i < zoomedPointsList.size() - 1; i++) {
                Point point = zoomedPointsList.get(i);
                int x1 = point.x;
                int y1 = point.y;
                point = zoomedPointsList.get(i + 1);
                int x2 = point.x;
                int y2 = point.y;
                g2.drawLine(x1, y1, x2, y2);
            }

            g2.setStroke(oldStroke);
            g2.setColor(GRAPH_POINT_COLOR);
            for (Point point : zoomedPointsList) {
                int x = point.x - GRAPH_POINT_WIDTH / 2;
                int y = point.y - GRAPH_POINT_WIDTH / 2;
                int ovalW = GRAPH_POINT_WIDTH;
                int ovalH = GRAPH_POINT_WIDTH;
                g2.fillOval(x, y, ovalW, ovalH);
            }
        } else {
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2.drawString("Введите значения и нажмите на кнопку \"построить\"", 200, 300);
        }
    }

    public int findStep(int number) {
        int step = 10;
        while (step < number) {
            step = step + 10;
        }
        return step;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int) (PREF_W * zoom), (int) (PREF_H * zoom));
    }
}