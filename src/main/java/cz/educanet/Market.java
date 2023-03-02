package cz.educanet;

public class Market {

    private final int id;
    private final String symbol;
    private final String name;

    public Market(int id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
