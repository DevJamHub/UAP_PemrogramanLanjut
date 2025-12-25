package util;

import model.Asset;

import java.io.*;
import java.util.*;

public class CSVUtil {

    private static final String DIR = "data";
    private static final String PATH = DIR + "/assets.csv";

    static {
        new File(DIR).mkdirs();
        try {
            new File(PATH).createNewFile();
        } catch (IOException ignored) {}
    }

    public static List<Asset> read() {
        List<Asset> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) list.add(Asset.fromCSV(line));
            }
        } catch (IOException ignored) {}
        return list;
    }

    public static void write(List<Asset> assets) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATH))) {
            for (Asset a : assets) pw.println(a.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
