package ui;

import service.AssetService;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        AssetService service = new AssetService();

        AssetTablePanel tablePanel = new AssetTablePanel(service);
        AssetFormPanel formPanel = new AssetFormPanel(service, tablePanel);
        ReportPanel reportPanel = new ReportPanel(service);

        setTitle("Sistem Manajemen Aset Pribadi");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Data Aset", tablePanel);
        tabs.add("Form Aset", formPanel);
        tabs.add("Laporan", reportPanel);

        add(tabs);
    }
}
