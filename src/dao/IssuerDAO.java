package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Issuer;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 4/21/2017.
 */
public class IssuerDAO {
    //*******************************
    //SELECT an Issuer
    //*******************************

    //Search by EQ_NUMBER
    public static Issuer searchIssuer(String issEqnum) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statament
        String selectStmt = "SELECT * FROM issuer WHERE eq_number=" + "('" + issEqnum + "')";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsIss = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getInstrumentFromResultSet method and get instrument object
            Issuer issuer = getIssuerFromResultSet(rsIss);
            //Return instrument object
            return issuer;
        } catch (SQLException e) {
            System.out.println("While searching an issuer with" + issEqnum + "eq_number, an error occured: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Issuer Object's attributes and return issuer object.
    private static Issuer getIssuerFromResultSet(ResultSet rs) throws SQLException {
        Issuer iss = null;
        if (rs.next()) {
            iss = new Issuer();
            iss.setEq_number(rs.getInt("eq_number"));
            iss.setCompany_name(rs.getString("company_name"));
            iss.setCountry(rs.getString("country"));
            iss.setSector(rs.getString("sector"));
            iss.setReiting(rs.getString("reiting"));

        }
        return iss;

    }

    //*******************************
    //SELECT Issuers
    //*******************************
    public static ObservableList<Issuer> searchIssuers() throws SQLException, ClassNotFoundException {
        //Declare SELECT statement
        String selectStmt = "SELECT *FROM issuer";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsIss = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getIssuerList method and get issuer object
            ObservableList<Issuer> issList = getIssuerList(rsIss);
            // Return issuer object
            return issList;
        } catch (SQLException e) {
            System.out.println("SQL operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }

    // Select * from issuer operation
    private  static  ObservableList<Issuer> getIssuerList(ResultSet rs) throws SQLException, ClassNotFoundException{
        //Declare a observable List which comprises of Isssuer objects
        ObservableList<Issuer> issList= FXCollections.observableArrayList();
        while (rs.next()) {
           Issuer iss = new Issuer();
            iss.setEq_number(rs.getInt("eq_number"));
            iss.setCompany_name(rs.getString("company_name"));
            iss.setCountry(rs.getString("country"));
            iss.setSector(rs.getString("sector"));
            iss.setReiting(rs.getString("reiting"));
            //Add issuer to the ObservableList
            issList.add(iss);
        }
        //return issList (ObservableList of Issuer)
        return  issList;
    }

    //*************************************
    //DELETE an issuer
    //*************************************

    public static void deleteIssWithId (String IssId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt ="DELETE FROM issuer WHERE eq_number ="+ IssId;

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT an issuer
    //*************************************

    public  static  void insertIss (String eq_number,String company_name,String country, String sector,
                                    String reiting) throws SQLException,ClassNotFoundException{
        //Declare a INSERT statement
        String updateStmt= "INSERT INTO issuer"+
                " (eq_number,company_name, country, sector, reiting)" +
                "  VALUES" + "('"+eq_number+"', '"+company_name+"','"+country+"', '"+sector+"','"+reiting+"')";

        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {System.out.println("Error occurred while INSERT Operation: " + e);
            throw  e;
        }
    }


}
