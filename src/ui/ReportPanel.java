package ui;

import model.Asset;
import service.AssetService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportPanel extends JPanel {

    public ReportPanel(AssetService service) {
        setLayout(new BorderLayout());
        setOpaque(false);

        // Panel gradien merah gelap
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(204, 0, 0),
                        0, getHeight(), new Color(102, 0, 0)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BoxLayout(gradientPanel, BoxLayout.Y_AXIS));
        gradientPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        gradientPanel.setOpaque(false);

        // Judul
        JLabel title = new JLabel("📊 Statistik & Laporan Aset");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);
        gradientPanel.add(title);
        gradientPanel.add(Box.createVerticalStrut(30));

        // Card info
        JLabel totalLabel = new JLabel();
        JLabel kategoriLabel = new JLabel();
        JLabel terbaruLabel = new JLabel();

        gradientPanel.add(createCard("📦 Total Aset", totalLabel));
        gradientPanel.add(Box.createVerticalStrut(15));
        gradientPanel.add(createCard("📂 Total Kategori", kategoriLabel));
        gradientPanel.add(Box.createVerticalStrut(15));
        gradientPanel.add(createCard("🕒 Aset Terbaru", terbaruLabel));

        add(gradientPanel, BorderLayout.CENTER);

        // Timer update setiap 1 detik
        new Timer(1000, e -> updateStats(service, totalLabel, kategoriLabel, terbaruLabel)).start();
        updateStats(service, totalLabel, kategoriLabel, terbaruLabel);
    }

    private JPanel createCard(String label, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        panel.setBackground(new Color(255, 102, 102, 180));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(Color.WHITE);

        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        panel.add(lbl, BorderLayout.WEST);
        panel.add(valueLabel, BorderLayout.EAST);
        return panel;
    }

    private void updateStats(AssetService service, JLabel total, JLabel kategori, JLabel terbaru) {
        List<Asset> list = service.getAll();
        total.setText(String.valueOf(list.size()));
        kategori.setText(String.valueOf(list.stream().map(Asset::getCategory).distinct().count()));
        terbaru.setText(list.isEmpty() ? "-" : list.get(0).getDate());
    }
}
