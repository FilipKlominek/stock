package cz.educanet;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class MarketBean {

    public int getRemoveId() {
        return removeId;
    }

    public void setRemoveId(int removeId) {
        this.removeId = removeId;
    }

    public String getAddTicker_symbol() {
        return addTicker_symbol;
    }

    public void setAddTicker_symbol(String addTicker_symbol) {
        this.addTicker_symbol = addTicker_symbol;
    }

    public String getAddDescriptive_name() {
        return addDescriptive_name;
    }

    public void setAddDescriptive_name(String addDescriptive_name) {
        this.addDescriptive_name = addDescriptive_name;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public String getEditTicker_symbol() {
        return editTicker_symbol;
    }

    public void setEditTicker_symbol(String editTicker_symbol) {
        this.editTicker_symbol = editTicker_symbol;
    }

    public String getEditDescriptive_name() {
        return editDescriptive_name;
    }

    public void setEditDescriptive_name(String editDescriptive_name) {
        this.editDescriptive_name = editDescriptive_name;
    }

    private int removeId;
    private String addTicker_symbol;
    private String addDescriptive_name;
    private int editId;
    private String editTicker_symbol;
    private String editDescriptive_name;


    public void addMarket() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT ticker_symbol, descriptive_name" +
                        " INTO stock_market.stock_market" +
                        " VALUES (?, ?)"
        );

        preparedStatement.setString(1, addTicker_symbol);
        preparedStatement.setString(2, addDescriptive_name);

        connection.close();
    }

    public void removeMarket() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM stock_market.stock_market" +
                        " WHERE stock_market.stock_market_id = ?"
        );
        preparedStatement.setInt(1, removeId);

        connection.close();
    }

    public void editMarket() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE stock_market" +
                        " SET stock_market.ticker_symbol = ?, stock_market.descriptive_name = ?" +
                        " WHERE stock_market.stock_market_id = ?"
        );
        preparedStatement.setString(1, editTicker_symbol);
        preparedStatement.setString(2, editDescriptive_name);
        preparedStatement.setInt(3, editId);

        connection.close();
    }

    public List<Market> getStockMarkets() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT s.ticker_symbol, s.descriptive_name, s.stock_market_id" +
                        " FROM stock_market.stock_market AS s"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Market> markets = new ArrayList<>();

        while (resultSet.next()) {
            markets.add(new Market(
                    resultSet.getInt("stock_market_id"),
                    resultSet.getString("ticker_symbol"),
                    resultSet.getString("descriptive_name")
            ));
        }

        return markets;
    }
}
