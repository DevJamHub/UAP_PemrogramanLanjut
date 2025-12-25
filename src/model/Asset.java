package model;

public class Asset {

    private final String id;
    private final String name;
    private final String category;
    private final String date;
    private final String note;

    public Asset(String id, String name, String category, String date, String note) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getNote() { return note; }

    public String toCSV() {
        return String.join(",", id, name, category, date, note);
    }

    public static Asset fromCSV(String line) {
        String[] d = line.split(",");
        return new Asset(
                d.length > 0 ? d[0] : "",
                d.length > 1 ? d[1] : "",
                d.length > 2 ? d[2] : "",
                d.length > 3 ? d[3] : "",
                d.length > 4 ? d[4] : ""
        );
    }
}
