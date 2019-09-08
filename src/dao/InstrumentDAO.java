package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Instrument;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 4/15/2017.
 */
public class InstrumentDAO {

    //*******************************
    //SELECT an Instrument
    //*******************************

    //Search by ISIN
    public static Instrument searchInstrumentIsin(String insIsin) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statament
        String selectStmt = "SELECT * FROM instrument WHERE isin=" + "('" + insIsin + "')";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsIns = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getInstrumentFromResultSet method and get instrument object
            Instrument instrument = getInstrumentFromResultSet(rsIns);
            //Return instrument object
            return instrument;
        } catch (SQLException e) {
            System.out.println("While searching an instrument with" + insIsin + "isin, an error occured: " + e);
            //Return exception
            throw e;
        }
    }

    //Search by CUSIP
    public static Instrument searchInstrumentCusip(String insCusip) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statament
        String selectStmt = "SELECT * FROM instrument WHERE cusip=" + "('" + insCusip + "')";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsIns = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getInstrumentFromResultSet method and get instrument object
            Instrument instrument = getInstrumentFromResultSet(rsIns);
            //Return instrument object
            return instrument;
        } catch (SQLException e) {
            System.out.println("While searching an instrument with" + insCusip + "isin, an error occured: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Instrument Object's attributes and return instrument object.
    private static Instrument getInstrumentFromResultSet(ResultSet rs) throws SQLException {
        Instrument ins = null;
        if (rs.next()) {
            ins = new Instrument();
            ins.setIsin(rs.getString("isin"));
            ins.setCoupon(rs.getDouble("coupon"));
            ins.setCurrency(rs.getString("currency"));
            ins.setType_instr(rs.getString("type_instr"));
            ins.setMaturity(rs.getDate("maturity"));
            ins.setRate_type(rs.getString("rate_type"));
            ins.setCusip(rs.getString("cusip"));
            ins.setNumber_pmnts(rs.getInt("number_pmnts"));
            ins.setName_instr(rs.getString("name_instr"));
            ins.setBase(rs.getString("base"));
            ins.setEq_number(rs.getInt("eq_number"));
        }
        return ins;

    }

    //*******************************
    //SELECT Instruments
    //*******************************
    public static ObservableList<Instrument> searchInstruments() throws SQLException, ClassNotFoundException {
        //Declare SELECT statement
        String selectStmt = "SELECT *FROM instrument";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsInsts = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getInstrumentList method and get instrument object
            ObservableList<Instrument> insList = getInstrumentList(rsInsts);
            // Return instrument object
            return insList;
        } catch (SQLException e) {
            System.out.println("SQL operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }
    // Select * from instrument operation
    private  static  ObservableList<Instrument> getInstrumentList(ResultSet rs) throws SQLException, ClassNotFoundException{
        //Declare a observable List which comprises of Instrument objects
        ObservableList<Instrument> insList= FXCollections.observableArrayList();
        while (rs.next()) {
            Instrument ins = new Instrument();
            ins.setIsin(rs.getString("isin"));
            ins.setCoupon(rs.getDouble("coupon"));
            ins.setCurrency(rs.getString("currency"));
            ins.setType_instr(rs.getString("type_instr"));
            ins.setMaturity(rs.getDate("maturity"));
            ins.setRate_type(rs.getString("rate_type"));
            ins.setCusip(rs.getString("cusip"));
            ins.setNumber_pmnts(rs.getInt("number_pmnts"));
            ins.setName_instr(rs.getString("name_instr"));
            ins.setBase(rs.getString("base"));
            ins.setEq_number(rs.getInt("eq_number"));
            //Add instrument to the ObservableList
            insList.add(ins);
        }
        //return insList (ObservableList of Instruments)
        return  insList;
     }


    //*************************************
    //INSERT an instrument
    //*************************************
    public  static  void insertIns (String isin,String coupon,String currency, String type_instr,String maturity, String  rate_type, String cusip,
                                    String number_pmnts, String name_instr, String base, String eq_number) throws SQLException,ClassNotFoundException{
        //Declare a INSERT statement
        String updateStmt= "INSERT INTO instrument"+
                " (isin,coupon,currency,type_instr,maturity,rate_type,cusip,number_pmnts,name_instr,base,eq_number )" +
                "  VALUES" + "('"+isin+"', '"+coupon+"','"+currency+"', '"+type_instr+"','"+maturity+"','"+rate_type+"','"+cusip+"','"+number_pmnts+"','"+name_instr+"','"+base+"','"+eq_number+"')";

        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {System.out.println("Error occurred while INSERT Operation: " + e);
        throw  e;
        }
    }

    //*************************************
    //DELETE an instrument
    //*************************************
    public static void deleteInsWithIsin (String InsIsin) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt ="DELETE FROM instrument WHERE isin ="+ "('"+InsIsin+"')";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    }


