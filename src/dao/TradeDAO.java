package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Trade;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.postgresql.core.SqlCommandType.INSERT;

/**
 * Created by Alex on 4/2/2017.
 */
public class TradeDAO {

    //*******************************
    //SELECT an Trade
    //*******************************
    public static Trade searchTrade (String trdId) throws SQLException,ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt="SELECT * FROM trade WHERE trade_id="+trdId;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTrd= DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getTradeFromResultSet method and get trade object

            Trade trade= getTradeFromResultSet(rsTrd);

            //Return trade object
            return trade;
        } catch (SQLException e){
            System.out.println("While searching an trade with " + trdId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }



    //Use ResultSet from DB as parameter and set Trade Object's attributes and return trade object.
    private static Trade getTradeFromResultSet(ResultSet rs) throws SQLException

    {  Trade trd=null;
       if (rs.next()) {
           trd = new Trade();
           trd.setTrade_id(rs.getInt("trade_id"));
           trd.setPortfolio_name(rs.getString("portfolio_name"));
           trd.setIsin(rs.getString("isin"));
           trd.setName(rs.getString("name"));
           trd.setTrade_date(rs.getDate("trade_date"));
           trd.setSettlement_date(rs.getDate("settlement_date"));
           trd.setQuantity(rs.getInt("quantity"));
           trd.setPrice(rs.getDouble("price"));
           trd.setAccrued_int(rs.getDouble("accrued_int"));
           trd.setCounterparty(rs.getString("counterparty"));
           trd.setSide(rs.getString("side"));
           trd.setTrader(rs.getString("trader"));
           trd.setCustody(rs.getString("custody"));
       }
       return trd;

       }

    //*******************************
    //SELECT Trades
    //*******************************

    public  static ObservableList<Trade> searchTrades()throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt="SELECT * FROM trade";
        //Execute SELECT statement
        try{
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTrds=DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getTradeList method and get trade object
            ObservableList<Trade> trdList=getTradesList(rsTrds);
            //Return trade object
            return  trdList;
        } catch (SQLException e){
            System.out.println("SQL select operation has been failed:" + e);
            //Return exception
            throw e;
        }
    }

    //Select * from trades operation
    private  static  ObservableList<Trade>getTradesList(ResultSet rs) throws SQLException,ClassNotFoundException{

        //Declare a observable List which comprises of Trade objects
        ObservableList<Trade>trdList= FXCollections.observableArrayList();
        while (rs.next())
        {
            Trade trd=new Trade();
            trd.setTrade_id(rs.getInt("trade_id"));
            trd.setPortfolio_name(rs.getString("portfolio_name"));
            trd.setIsin(rs.getString("isin"));
            trd.setName(rs.getString("name"));
            trd.setTrade_date(rs.getDate("trade_date"));
            trd.setSettlement_date(rs.getDate("settlement_date"));
            trd.setQuantity(rs.getInt("quantity"));
            trd.setPrice(rs.getDouble("price"));
            trd.setAccrued_int(rs.getDouble("accrued_int"));
            trd.setCounterparty(rs.getString("counterparty"));
            trd.setSide(rs.getString("side"));
            trd.setTrader(rs.getString("trader"));
            trd.setCustody(rs.getString("custody"));
            //Add trade to the ObservableList
            trdList.add(trd);
        }
        //return trdList (ObservableList of Trades)
        return trdList;

    }

    //*************************************
    //UPDATE an trades's price
    //*************************************

    public static void updateTrdPrice (String trdId, String trdPrice) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt = "UPDATE trade SET price = " + trdPrice  + "WHERE trade_id = "+ trdId;

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //DELETE an trade
    //*************************************
    public static void deleteTrdWithId (String TrdId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt ="DELETE FROM trade WHERE trade_id ="+ TrdId;

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    //*************************************
    //INSERT an trade using function add_trade
    //*************************************

    public  static void insertTrd(String portfolio_name, String isin, String name, String trade_date,
                                  String settlement_date, String quantity, String price, String accrued_int,
                                  String counterparty, String side, String trader, String custody) throws
                                   SQLException, ClassNotFoundException{
        //Declare a INSERT statement
      /*  String updateStmt="INSERT INTO trade"+
                " (portfolio_name,isin,name,trade_date,settlement_date,quantity,price,accrued_int,counterparty,side,trader,custody)" +
                "  VALUES" + "('"+portfolio_name+"', '"+isin+"','"+name+"', '"+trade_date+"','"+settlement_date+"','"+quantity+"','"+price+"','"+accrued_int+"','"+counterparty+"','"+side+"','"+trader+"','"+custody+"')";
      */  //Execute INSERT operation
        String updateStmt= "BEGIN;" +
                   "SELECT add_trade('"+portfolio_name+"', '"+isin+"','"+name+"', '"+trade_date+"','"+settlement_date+"','"+quantity+"','"+price+"','"+accrued_int+"','"+counterparty+"','"+side+"','"+trader+"','"+custody+"');" +
                "SELECT selection(); END;";
        try {
            DBUtil.dbExecute(updateStmt); // using Execute instead of ExecuteUpdate, because that can ignore SELECT statement
        } catch (SQLException e){
            System.out.println("Error occurred while INSERT Operation: " + e);
            throw  e;
        }
    }
 }




