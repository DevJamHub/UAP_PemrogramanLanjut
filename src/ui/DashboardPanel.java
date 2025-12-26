package ui;

import service.AssetService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel(AssetService service, JFrame parent) {

        setLayout(new BorderLayout());

        JPanel bg = new GradientPanel();
        bg.setLayout(new BorderLayout(0, 30));
        bg.setBorder(new EmptyBorder(30, 40, 30, 40));

        // ===== TITLE =====
        JLabel title = new JLabel("Dashboard Manajemen Aset", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        bg.add(title, BorderLayout.NORTH);

        // ===== STAT CARD =====
        JPanel statPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        statPanel.setOpaque(false);

        JLabel totalAset = new JLabel("0");
        JLabel totalKategori = new JLabel("0");

        statPanel.add(createStatCard("Total Aset", totalAset));
        statPanel.add(createStatCard("Total Kategori", totalKategori));

        bg.add(statPanel, BorderLayout.CENTER);

        // ===== NAVIGATION =====
        JPanel navPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        navPanel.setOpaque(false);
        navPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // === SUDAH DITUKAR ===
        navPanel.add(createNavButton(
                "Tambah Aset",
                "Input data aset baru",
                () -> switchTab(parent, 2) // <-- ke Data Aset
        ));

        navPanel.add(createNavButton(
                "Data Aset",
                "Lihat dan kelola data aset",
                () -> switchTab(parent, 1) // <-- ke Tambah Aset
        ));

        navPanel.add(createNavButton(
                "Laporan",
                "Statistik dan laporan aset",
                () -> switchTab(parent, 3)
        ));

        bg.add(navPanel, BorderLayout.SOUTH);
        add(bg);

        // ===== AUTO UPDATE =====
        new Timer(1000, e -> {
            totalAset.setText(String.valueOf(service.getAll().size()));
            totalKategori.setText(
                    String.valueOf(
                            service.getAll().stream()
                                    .map(a -> a.getCategory())
                                    .distinct()
                                    .count()
                    )
            );
        }).start();
    }

    // ===== STAT CARD =====
    private JPanel createStatCard(String title, JLabel value) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel t = new JLabel(title);
        t.setFont(new Font("SansSerif", Font.BOLD, 15));
        t.setForeground(Color.BLACK);

        value.setFont(new Font("SansSerif", Font.BOLD, 42));
        value.setForeground(Color.BLACK);

        card.add(t);
        card.add(Box.createVerticalStrut(10));
        card.add(value);

        return card;
    }

    // ===== NAV BUTTON CARD =====
    private JPanel createNavButton(String title, String desc, Runnable action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel t = new JLabel(title);
        t.setFont(new Font("SansSerif", Font.BOLD, 16));
        t.setForeground(Color.BLACK);

        JLabel d = new JLabel(desc);
        d.setFont(new Font("SansSerif", Font.PLAIN, 13));
        d.setForeground(Color.BLACK);

        JButton btn = new JButton("Buka");
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFocusPainted(false);

        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        btn.addActionListener(e -> action.run());

        card.add(t);
        card.add(Box.createVerticalStrut(5));
        card.add(d);
        card.add(Box.createVerticalStrut(15));
        card.add(btn);

        return card;
    }

    // ===== TAB SWITCH =====
    private void switchTab(JFrame parent, int index) {
        JTabbedPane tabs = (JTabbedPane) parent.getContentPane().getComponent(0);
        tabs.setSelectedIndex(index);
    }

    // ===== GRADIENT BACKGROUND =====
    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new GradientPaint(
                    0, 0, new Color(255, 120, 120),
                    0, getHeight(), new Color(150, 0, 0)
            ));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
