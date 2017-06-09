package Controller;

import Model.PointList;
import View.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by Sinelnikov on 02.05.2017.
 */
public class ChartDraw {
    private PointList pointList;
    private MainFrame mainFrame;

    public ChartDraw() {
        pointList = new PointList(this);
        mainFrame = new MainFrame(this);
    }

    public void addPointToList(Point point) {
        pointList.addPoint(point);
        mainFrame.getChartOptionsViewPanel().getChartDrawPanel().repaint();
        JTable table = mainFrame.getSortOptionsPanel().getTable();
        Vector<String> newRow = new Vector<>();
        newRow.add(String.valueOf(pointList.getPointList().size()));
        newRow.add(String.valueOf(point.getX()));
        newRow.add(String.valueOf(point.getY()));
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addRow(newRow);
    }


    public void newList(int maxNumber, int step, int iterations) {
        pointList.makeNewPointListFromSort(maxNumber, step, iterations);
        JTable table = mainFrame.getSortOptionsPanel().getTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int rowCount = tableModel.getRowCount();
        for (int rowIndex = rowCount - 1; rowIndex >= 0; rowIndex--) {
            tableModel.removeRow(rowIndex);
        }
    }

    public List<Point> getPointList() {
        return pointList.getPointList();
    }

    public void updateProgress(int progress) {
        mainFrame.progressUpdate(progress);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
