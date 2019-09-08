package dao;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Results;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Alex on 4/23/2017.
 */
public class AccountsDAO {

    public static void updateNE() throws SQLException, ClassNotFoundException {
        String updateStmt = "SELECT revaluation()";
        try {
            DBUtil.dbExecute(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while Revaluation Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //Get Investment per instrument as Quantity and avarage Price
    //*************************************
    public static ObservableList<Results> getInvestQuantityPrice() throws SQLException, ClassNotFoundException {
         String selectStmt = "SELECT * FROM average_price_currency;";
        try {
            ResultSet rsInv = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Results> invRez = getInvestQuantityPriceList(rsInv);
            return invRez;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed:" + e);
            throw e;
        }
    }

    private static ObservableList<Results> getInvestQuantityPriceList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Results> invRes = FXCollections.observableArrayList();
        while (rs.next()) {
            Results inv = new Results();
            inv.setIsin(rs.getString("isin"));
            inv.setQuantity(rs.getInt("quantity"));
            inv.setPrice(rs.getDouble("price"));
            inv.setCurrency(rs.getString("currency"));
            invRes.add(inv);
        }
        return invRes;
    }

    //*************************************
    //Get the Current Investment Amnt
    //*************************************
    public static ObservableList<Results> getCurrentTotal() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT average_price_currency.isin AS isin, total_per_currency.total AS total, total_per_currency.currency AS currency" +
                " FROM average_price_currency INNER JOIN total_per_currency ON average_price_currency.currency=total_per_currency.currency; ";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsCur = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getCurrentTotalList method and get trade object
            ObservableList<Results> curRez = getCurrentTotalList(rsCur);
            //Return currentTotal object
            return curRez;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed:" + e);
            //Return exception
            throw e;
        }
    }

    //Select * from CurrentTotal operation
    private static ObservableList<Results> getCurrentTotalList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of CurrentTotal objects
        ObservableList<Results> curRez = FXCollections.observableArrayList();
        while (rs.next()) {
            Results cur = new Results();
            cur.setIsin(rs.getString("isin"));
            cur.setTotal_per_isin(rs.getDouble("total"));
            cur.setCurrency(rs.getString("currency"));
            //  cur.setRate(rs.getDouble("price"));
            //  cur.setDate(rs.getDate("date"));


            //Add CurrentTotal to the ObservableList
            curRez.add(cur);
        }
        //return curList (ObservableList of Trades)
        return curRez;

    }

    // Method get ResultSet after executing SELECT total_investment
    public static Results currentValueTotalResult() throws SQLException, ClassNotFoundException {

        //Declare SELECT statement
        String selectStmt = "SELECT * FROM total_investment;";
        //Execute SELECT operation
        try {
            ResultSet rsTotal = DBUtil.dbExecuteQuery(selectStmt);
            Results curTotal = getTotalFromResultSet(rsTotal);
            return curTotal;

        } catch (SQLException e) {
            System.out.print("Error occurred while SELECT Operation: " + e);
            throw e;
        }
    }

    // Method help get result fr SELECT abt last Re-evaluation
    private static Results getTotalFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        Results trd = null;
        if (rs.next()) {
            trd = new Results();
            trd.setTotal_per_isin(rs.getDouble("total"));
        }
        return trd;
    }


    //Method that go to DataBase and return data with necessary date wich using in pre-revaluation
    public static Results getDate() throws SQLException, ClassNotFoundException {
        //Declare SELECT statement
        String selectStmt = "SELECT date FROM account where acc_ne IN (SELECT acc_ne FROM account ORDER BY block_id DESC LIMIT 1);";
        //Execute SELECT operation
        try {
            ResultSet rsDate = DBUtil.dbExecuteQuery(selectStmt);
            Results revDate = getDateFromResultSet(rsDate);
            return revDate;

        } catch (SQLException e) {
            System.out.print("Error occurred while SELECT Operation: " + e);
            throw e;
        }
    }


    // Method help get result fr SELECT abt last Re-evaluation
    private static Results getDateFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        Results trd = null;
        if (rs.next()) {
            trd = new Results();
            trd.setDate(rs.getDate("date"));
        }
        return trd;
    }

    //Method help get re-evaluation figures (acc_NE) for each isin per currencies on choosen date
    public static ObservableList<Results> getRevaluationPerInstrument(String date) throws SQLException, ClassNotFoundException {
        //Declare SELECT statement
        String selectStmt = "SELECT account.isin, account.acc_ne, account.currency,account.date FROM account WHERE account.date='" + date + "';";
        //Execute SELECT
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRev = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getRevaluationPerInstrumentList method and get trade object
            ObservableList<Results> invRev = getRevaluationPerInstrumentList(rsRev);
            // return RevaluationPerInstrument Object
            return invRev;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed:" + e);
            //Return exception
            throw e;
        }

    }

    private static ObservableList<Results> getRevaluationPerInstrumentList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Results> revIns = FXCollections.observableArrayList();
        while (rs.next()) {
            Results rev = new Results();
            rev.setIsin(rs.getString("isin"));
            rev.setTotal_per_isin(rs.getDouble("acc_ne"));
            rev.setCurrency(rs.getString("currency"));
            rev.setDate(rs.getDate("date"));
            revIns.add(rev);
        }
        return revIns;
    }

    //Method help get re-evaluation figures (acc_NE) for each Portfolio per currencies per Instrument on choosen date
    public static ObservableList<Results> getRevaluationPerPortfolio(String portfolio_name, String date) throws SQLException, ClassNotFoundException {
        //Declare SELECT statement
        String selectStmt = "SELECT account.isin,account.acc_ne,account.currency,account.date,trade.portfolio_name" +
                " FROM account JOIN trade ON (account.isin=trade.isin) WHERE (account.date='"+date+"') AND (trade.portfolio_name='"+portfolio_name+"');";
        //Execute SELECT
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRev = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getRevaluationPerInstrumentList method and get trade object
            ObservableList<Results> invRev = getRevaluationPerPortfolioList(rsRev);
            // return RevaluationPerPortfolio Object
            return invRev;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed:" + e);
            //Return exception
            throw e;
        }

    }

    private static ObservableList<Results>getRevaluationPerPortfolioList(ResultSet rs) throws SQLException,ClassNotFoundException{
        ObservableList<Results> revPort = FXCollections.observableArrayList();
        while (rs.next()) {
            Results rev = new Results();
            rev.setIsin(rs.getString("isin"));
            rev.setTotal_per_isin(rs.getDouble("acc_ne"));
            rev.setCurrency(rs.getString("currency"));
            rev.setDate(rs.getDate("date"));
            rev.setPortfolio_name(rs.getString("portfolio_name"));
            revPort.add(rev);
        }
        return revPort;
    }


}
