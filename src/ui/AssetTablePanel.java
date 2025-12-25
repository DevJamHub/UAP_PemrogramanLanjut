package ui;

import model.Asset;
import service.AssetService;
import util.SimpleDocumentListener;

import javax.swing.*;
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
        setBackground(Color.WHITE);

        Font font = new Font("SansSerif", Font.PLAIN, 13);

        // SEARCH
        JTextField searchField = new JTextField();
        searchField.setFont(font);

        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.setBackground(Color.WHITE);
        top.add(new JLabel("Pencarian"), BorderLayout.WEST);
        top.add(searchField, BorderLayout.CENTER);

        // TABLE
        model = new DefaultTableModel(
                new String[]{"ID", "Nama", "Kategori", "Tanggal", "Catatan"}, 0
        );
        table = new JTable(model);
        table.setFont(font);
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(table);

        // BUTTONS
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Hapus");

        updateBtn.setFont(font);
        deleteBtn.setFont(font);

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.add(updateBtn);
        bottom.add(deleteBtn);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // SEARCH LISTENER
        searchField.getDocument().addDocumentListener(
                new SimpleDocumentListener(() -> {
                    String key = searchField.getText();
                    if (key.isBlank()) refresh();
                    else refresh(service.search(key));
                })
        );

        // BUTTON ACTION
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

    // ================= UPDATE =================
    private void updateSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang mau diupdate");
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
                this,
                fields,
                "Update Data Aset",
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

    // ================= DELETE =================
    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang mau dihapus");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            service.deleteById(
                    model.getValueAt(row, 0).toString()
            );
            refresh();
        }
    }
}
