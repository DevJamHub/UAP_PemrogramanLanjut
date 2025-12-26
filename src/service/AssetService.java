package service;

import model.Asset;
import util.CSVUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AssetService {

    private final List<Asset> assets;

    public AssetService() {
        assets = CSVUtil.read();
    }

    public List<Asset> getAll() {
        return assets.stream()
                .sorted(Comparator.comparing(Asset::getDate).reversed())
                .collect(Collectors.toList());
    }

    public void add(String id, String name, String category, String note) {
        assets.add(new Asset(
                id, name, category,
                LocalDate.now().toString(),
                note
        ));
        CSVUtil.write(assets);
    }

    public boolean updateById(String id, String name, String category, String note) {
        for (int i = 0; i < assets.size(); i++) {
            Asset a = assets.get(i);
            if (a.getId().equals(id)) {
                assets.set(i, new Asset(
                        id, name, category,
                        a.getDate(), note
                ));
                CSVUtil.write(assets);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        boolean removed = assets.removeIf(a -> a.getId().equals(id));
        if (removed) CSVUtil.write(assets);
        return removed;
    }

    public List<Asset> search(String keyword) {
        String k = keyword.toLowerCase();
        return assets.stream()
                .filter(a ->
                        a.getId().toLowerCase().contains(k) ||
                                a.getName().toLowerCase().contains(k) ||
                                a.getCategory().toLowerCase().contains(k)
                )
                .sorted(Comparator.comparing(Asset::getDate).reversed())
                .collect(Collectors.toList());
    }
}
