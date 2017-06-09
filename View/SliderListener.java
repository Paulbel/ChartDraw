package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SliderListener implements ChangeListener {

    ChartOptionsViewPanel panel;

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            double zoom = source.getValue();
            changeZoom(zoom);
        }
    }

    public SliderListener(ChartOptionsViewPanel panel) {
        this.panel = panel;
    }

    public void changeZoom(double newZoom) {
        panel.setChartZoom(newZoom / 100);
        panel.revalidatePanel();
    }
}