package ui;

import service.AssetService;

import javax.swing.*;
import java.awt.*;

public class AssetFormPanel extends JPanel {

    public AssetFormPanel(AssetService service, AssetTablePanel tablePanel) {

        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        Font font = new Font("SansSerif", Font.PLAIN, 14);
        Dimension fieldSize = new Dimension(200, 28);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField category = new JTextField();
        JTextField note = new JTextField();

        id.setPreferredSize(fieldSize);
        name.setPreferredSize(fieldSize);
        category.setPreferredSize(fieldSize);
        note.setPreferredSize(fieldSize);

        JLabel l1 = new JLabel("ID Aset");
        JLabel l2 = new JLabel("Nama Aset");
        JLabel l3 = new JLabel("Kategori");
        JLabel l4 = new JLabel("Catatan");

        l1.setFont(font);
        l2.setFont(font);
        l3.setFont(font);
        l4.setFont(font);

        JButton save = new JButton("Simpan");
        save.setFont(font);

        c.gridx = 0; c.gridy = 0;
        add(l1, c); c.gridx = 1; add(id, c);

        c.gridy++; c.gridx = 0;
        add(l2, c); c.gridx = 1; add(name, c);

        c.gridy++; c.gridx = 0;
        add(l3, c); c.gridx = 1; add(category, c);

        c.gridy++; c.gridx = 0;
        add(l4, c); c.gridx = 1; add(note, c);

        c.gridy++; c.gridx = 1;
        add(save, c);

        save.addActionListener(e -> {
            if (id.getText().isBlank() ||
                    name.getText().isBlank() ||
                    category.getText().isBlank() ||
                    note.getText().isBlank()) {

                JOptionPane.showMessageDialog(this, "Semua field wajib diisi");
                return;
            }

            service.add(
                    id.getText(),
                    name.getText(),
                    category.getText(),
                    note.getText()
            );

            id.setText("");
            name.setText("");
            category.setText("");
            note.setText("");

            tablePanel.refresh();
        });
    }
}
