package ui;

import service.AssetService;

import javax.swing.*;
import java.awt.*;

public class AssetFormPanel extends JPanel {

    public AssetFormPanel(AssetService service, AssetTablePanel tablePanel) {

        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("SansSerif", Font.PLAIN, 14);
        Dimension fieldSize = new Dimension(250, 28);

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

        l1.setFont(font); l2.setFont(font); l3.setFont(font); l4.setFont(font);

        // Tombol simpan update warna foreground hitam
        JButton save = new JButton("💾 Simpan");
        save.setToolTipText("Simpan data aset baru atau edit");
        save.setFont(font);
        save.setBackground(new Color(13, 205, 147));
        save.setForeground(Color.BLACK); // <- warna hitam agar emoji terlihat

        c.gridx = 0; c.gridy = 0;
        add(l1, c); c.gridx = 1; add(id, c);
        c.gridy++; c.gridx = 0; add(l2, c); c.gridx = 1; add(name, c);
        c.gridy++; c.gridx = 0; add(l3, c); c.gridx = 1; add(category, c);
        c.gridy++; c.gridx = 0; add(l4, c); c.gridx = 1; add(note, c);
        c.gridy++; c.gridx = 1; add(save, c);

        save.addActionListener(e -> {
            if (id.getText().isBlank() || name.getText().isBlank() || category.getText().isBlank() || note.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Semua field wajib diisi", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            service.add(id.getText(), name.getText(), category.getText(), note.getText());
            id.setText(""); name.setText(""); category.setText(""); note.setText("");
            tablePanel.refresh();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0,0,new Color(255,204,204),0,getHeight(),new Color(255,102,102));
        g2d.setPaint(gp);
        g2d.fillRect(0,0,getWidth(),getHeight());
    }
}
