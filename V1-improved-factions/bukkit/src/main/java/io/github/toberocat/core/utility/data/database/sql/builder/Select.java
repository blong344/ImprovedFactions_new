package io.github.toberocat.core.utility.data.database.sql.builder;

import java.util.Objects;

public class Select {
    private String table = "";
    private String[] columns = new String[0];
    private String filter = "";

    public Select() {
    }

    public Select(String table, String[] columns, String filter) {
        this.table = table;
        this.columns = columns;
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }

    public String getTable() {
        return table;
    }

    public Select setTable(String table) {
        this.table = table;
        return this;
    }

    public Select setColumns(String... columns) {
        this.columns = columns;
        return this;
    }

    public Select setFilter(String filter, String... placeholders) {
        this.filter = String.format(filter, placeholders);
        return this;
    }

    public String getColumns() {
        return Objects.requireNonNullElse(String.join(", ", columns), "*");
    }
}
