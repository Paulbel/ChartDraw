package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


/**
 * Created by Sinelnikov on 24.04.2017.
 */
public class ChartOptionsViewPanel extends JPanel {
    private ChartDrawPanel chartDrawPanel;
    private JLabel scaleTextLabel;
    private JSlider slider;
    private JProgressBar progressBar;
    ChartOptionsViewPanel(List<Point> pointList){
        super();
        this.setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
        slider = new JSlider(JSlider.HORIZONTAL, 50, 150, 100);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);

        chartDrawPanel = new ChartDrawPanel(pointList);
        scaleTextLabel = new JLabel(chartDrawPanel.getZoom()*100+"%");

        toolBar.setFloatable(false);
        toolBar.setLayout(new FlowLayout());


        JScrollPane scrollPanel = new JScrollPane(chartDrawPanel);

        scrollPanel.setAutoscrolls(true);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point origin;

            @Override
            public void mousePressed(MouseEvent e) {
                origin = e.getPoint();
            }


            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (origin != null){
                    JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, chartDrawPanel);
                    if(viewPort != null){
                        int deltaX = origin.x  - e.getX();
                        int deltaY = origin.y - e.getY();

                        Rectangle view = viewPort.getViewRect();
                        view.x += deltaX*0.3;
                        view.y += deltaY*0.2;
                        chartDrawPanel.scrollRectToVisible(view);
                    }
                }
            }
        };
        chartDrawPanel.addMouseListener(mouseAdapter);
        chartDrawPanel.addMouseMotionListener(mouseAdapter);
        scrollPanel.setPreferredSize(new Dimension(800,600));
        scrollPanel.setVisible(true);
        progressBar.setString("Начните вычисления");
        progressBar.setStringPainted(true);

        toolBar.add(slider);
        toolBar.add(progressBar /*FlowLayout.RIGHT*/);
        this.add(scrollPanel,BorderLayout.WEST);
        this.add(toolBar, BorderLayout.SOUTH);
        this.setVisible(true);
        slider.addChangeListener(new SliderListener(this));
        scrollPanel.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.isControlDown()) {
                    if (e.getWheelRotation() < 0) {
                        chartDrawPanel.setZoom(chartDrawPanel.getZoom()+0.1);

                    } else {
                        chartDrawPanel.setZoom(chartDrawPanel.getZoom()-0.1);
                    }
                    slider.setValue((int)(chartDrawPanel.getZoom()*100));
                    chartDrawPanel.revalidate();
                }
            }
            });
    }
    void revalidatePanel(){
        scaleTextLabel.setText(Math.round(chartDrawPanel.getZoom()*100)+"%");
        chartDrawPanel.revalidate();
    }

    void updateProgress(int progress){
        progressBar.setValue(progress);
        progressBar.setString("Выполнено "+progress+"%");
        revalidate();
    }

    public ChartDrawPanel getChartDrawPanel() {
        return chartDrawPanel;
    }

    void setChartZoom(double zoom){
        chartDrawPanel.setZoom(zoom);
    }
}
