package ui;

import service.AssetService;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        AssetService service = new AssetService();

        DashboardPanel dashboardPanel = new DashboardPanel(service, this);
        AssetTablePanel tablePanel = new AssetTablePanel(service);
        AssetFormPanel formPanel = new AssetFormPanel(service, tablePanel);
        ReportPanel reportPanel = new ReportPanel(service);

        setTitle("Sistem Manajemen Aset Pribadi");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Dashboard", dashboardPanel);
        tabs.add("Data Aset", tablePanel);
        tabs.add("Form Aset", formPanel);
        tabs.add("Laporan", reportPanel);

        add(tabs);
    }
}
