package View;

import Controller.ChartDraw;
import Model.TreeArraySort;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Sinelnikov on 20.04.2017.
 */
public class MainFrame {
    private ChartOptionsViewPanel chartOptionsViewPanel;
    private TreeArraySort treeArraySorter;
    private SortOptionsPanel sortOptionsPanel;

    public MainFrame(ChartDraw controller) {
        treeArraySorter = new TreeArraySort();
        JFrame mainFrame = new JFrame("DrawGraph");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chartOptionsViewPanel = new ChartOptionsViewPanel(controller.getPointList());
        sortOptionsPanel = new SortOptionsPanel(controller);
        mainFrame.add(sortOptionsPanel, BorderLayout.WEST);
        mainFrame.add(chartOptionsViewPanel, BorderLayout.EAST);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void progressUpdate(int progress) {
        chartOptionsViewPanel.updateProgress(progress);
    }

    public ChartOptionsViewPanel getChartOptionsViewPanel() {
        return chartOptionsViewPanel;
    }

    public SortOptionsPanel getSortOptionsPanel() {
        return sortOptionsPanel;
    }
}

