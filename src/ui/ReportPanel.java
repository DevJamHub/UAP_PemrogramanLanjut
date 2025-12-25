package ui;

import service.AssetService;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {

    public ReportPanel(AssetService service) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel label = new JLabel();
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(label);

        new Timer(1000, e ->
                label.setText("Total Aset Tercatat: " + service.getAll().size())
        ).start();
    }
}
