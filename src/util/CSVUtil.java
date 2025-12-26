package util;

import model.Asset;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class CSVUtil {

    private static final String DIR = "data";
    private static final String PATH = DIR + "/assets.csv";

    static {
        File dir = new File(DIR);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(PATH);
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal membuat file data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<Asset> read() {
        List<Asset> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) list.add(Asset.fromCSV(line));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal membaca data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    public static void write(List<Asset> assets) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATH))) {
            for (Asset a : assets) pw.println(a.toCSV());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menyimpan data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
