package io.github.toberocat.improvedFactions.core.database.mysql.builder;

public class Update {
    private String table = "";
    private UpdateValue value;
    private String filter = "";

    public Update() {
    }

    public Update(String table, UpdateValue value, String filter) {
        this.table = table;
        this.value = value;
        this.filter = filter;
    }

    public String getTable() {
        return table;
    }

    public Update setTable(String table) {
        this.table = table;
        return this;
    }

    public UpdateValue getValue() {
        return value;
    }

    public Update setValue(UpdateValue value) {
        this.value = value;
        return this;
    }

    public String getFilter() {
        return filter;
    }

    public Update setFilter(String filter) {
        this.filter = filter;
        return this;
    }
}
