package View;

import Controller.ChartDraw;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Sinelnikov on 08.05.2017.
 */
public class SortOptionsPanel extends JPanel {
    private JTable table;
    public SortOptionsPanel(ChartDraw controller){
        this.setPreferredSize(new Dimension(400,300));
        this.setLayout(new FlowLayout());
        JLabel inputNumberElementsLabel = new JLabel("Введите количество элементов: от 2 до");
        JLabel inputStepLabel = new JLabel("Введите шаг");
        JLabel iterationsCountLabel = new JLabel("Количество сортировок массива одной длины:");
        JTextField maxElementNumberTextField = new JTextField(8);
        JTextField stepTextField = new JTextField(8);
        JTextField iterationsTextField = new JTextField(8);
        JButton buildButton = new JButton("Построить");

        Vector<String> headerVect = new Vector<>();
        headerVect.add("№");
        headerVect.add("Количество");
        headerVect.add("Время");

        DefaultTableModel mod = new DefaultTableModel(headerVect, 0);
        table = new JTable(mod);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(350, 480));
        this.add(inputNumberElementsLabel);
        this.add(maxElementNumberTextField);
        this.add(inputStepLabel);
        this.add(stepTextField);
        this.add(iterationsCountLabel);
        this.add(iterationsTextField);
        this.add(buildButton);
        this.add(scrollPane);
        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int maxNumber = Integer.valueOf(maxElementNumberTextField.getText());
                    int step = Integer.valueOf(stepTextField.getText());
                    int iterationNumber = Integer.valueOf(iterationsTextField.getText());
                    controller.newList(maxNumber, step, iterationNumber);
                } catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(controller.getMainFrame().getChartOptionsViewPanel(), "Введено некорректное значение");
                }
            }
        });
    }

    public JTable getTable() {
        return table;
    }
}
