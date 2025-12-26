package ui;

import model.Asset;
import service.AssetService;
import util.SimpleDocumentListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AssetTablePanel extends JPanel {


    private final AssetService service;
    private final DefaultTableModel model;
    private final JTable table;

    public AssetTablePanel(AssetService service) {
        this.service = service;
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 179, 179));

        Font font = new Font("SansSerif", Font.PLAIN, 13);

        // ===== TOP SEARCH =====
        JTextField searchField = new JTextField();
        searchField.setFont(font);

        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.setBackground(new Color(255, 204, 204));

        JLabel searchLabel = new JLabel("Pencarian");
        searchLabel.setFont(font);
        searchLabel.setForeground(Color.BLACK);

        top.add(searchLabel, BorderLayout.WEST);
        top.add(searchField, BorderLayout.CENTER);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"ID", "Nama", "Kategori", "Tanggal", "Catatan"}, 0
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(font);
        table.setRowHeight(28);
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(new Color(255, 153, 153));
        table.setSelectionForeground(Color.BLACK);

        // Header
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        t, value, isSelected, hasFocus, row, col);

                lbl.setOpaque(true);
                lbl.setBackground(new Color(255, 102, 102));
                lbl.setForeground(Color.BLACK);
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setFont(font.deriveFont(Font.BOLD));
                return lbl;
            }
        });

        table.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(table);

        // ===== BUTTONS =====
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Hapus");

        // Ikon standar Swing (hitam & konsisten)
        updateBtn.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        deleteBtn.setIcon(UIManager.getIcon("FileView.fileIcon"));


        updateBtn.setToolTipText("Update data aset yang dipilih");
        deleteBtn.setToolTipText("Hapus data aset yang dipilih");

        updateBtn.setFont(font);
        deleteBtn.setFont(font);

        // Background tetap merah, teks & ikon hitam
        updateBtn.setBackground(new Color(255, 102, 102));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setFocusPainted(false);

        deleteBtn.setBackground(new Color(204, 0, 0));
        deleteBtn.setForeground(Color.BLACK);
        deleteBtn.setFocusPainted(false);

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(255, 153, 153));
        bottom.add(updateBtn);
        bottom.add(deleteBtn);

        // ===== LAYOUT =====
        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // ===== LOGIC =====
        searchField.getDocument().addDocumentListener(
                new SimpleDocumentListener(() -> {
                    String key = searchField.getText();
                    if (key.isBlank()) refresh();
                    else refresh(service.search(key));
                })
        );

        updateBtn.addActionListener(e -> updateSelected());
        deleteBtn.addActionListener(e -> deleteSelected());

        refresh();
    }

    public void refresh() {
        refresh(service.getAll());
    }

    public void refresh(List<Asset> data) {
        model.setRowCount(0);
        for (Asset a : data) {
            model.addRow(new Object[]{
                    a.getId(),
                    a.getName(),
                    a.getCategory(),
                    a.getDate(),
                    a.getNote()
            });
        }
    }

    private void updateSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data yang mau diupdate",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JTextField name = new JTextField(model.getValueAt(row, 1).toString());
        JTextField category = new JTextField(model.getValueAt(row, 2).toString());
        JTextField note = new JTextField(model.getValueAt(row, 4).toString());

        Object[] fields = {
                "Nama", name,
                "Kategori", category,
                "Catatan", note
        };

        int result = JOptionPane.showConfirmDialog(
                this, fields, "Update Data Aset",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            service.updateById(
                    model.getValueAt(row, 0).toString(),
                    name.getText(),
                    category.getText(),
                    note.getText()
            );
            refresh();
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data yang mau dihapus",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            service.deleteById(model.getValueAt(row, 0).toString());
            refresh();
        }
    }
}
