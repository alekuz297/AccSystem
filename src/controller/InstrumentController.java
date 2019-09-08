package controller;

import dao.InstrumentDAO;
import dao.IssuerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Alex on 4/12/2017.
 */
public class InstrumentController  {

// Fields re Instruments
@FXML private TextField isinText;
@FXML private TextField cusipText;
@FXML private TextField name_instrText;
@FXML private TextField type_instrText;
@FXML private TextField currencyText;
@FXML private TextField couponText;
@FXML private TextField rate_typeText;
@FXML private  TextField number_pmntsText;
@FXML private TextField baseText;
//@FXML private  TextField maturityText;
@FXML private DatePicker maturityDate;
@FXML private TextField eq_numberText;
@FXML private TextArea resultArea;
@FXML private  TextField searchIsinText;
@FXML private  TextField searchCusipText;
@FXML private  TextField isinDeleteText;

// Fields re Issuer
@FXML private  TextField eq_number2Text;
@FXML private  TextField company_nameText;
@FXML private  TextField countryText;
@FXML private  TextField sectorText;
@FXML private  TextField reitingText;
@FXML private  TextField searchEq_numberText;
@FXML private  TextField deleteEq_numberText;

// Table Instruments
@FXML private TableView instrumentTable;
@FXML private TableColumn<Instrument,String> insIsinColumn;
@FXML private TableColumn<Instrument,String> insCusipColumn;
@FXML private  TableColumn<Instrument,String> insName_instrColumn;
@FXML private  TableColumn<Instrument,String> insType_instrColumn;
@FXML private  TableColumn<Instrument,String> insCurrencyColumn;
@FXML private  TableColumn<Instrument,Double> insCouponColumn;
@FXML private  TableColumn<Instrument,String> insRate_typeColumn;
@FXML private  TableColumn<Instrument,Integer> insNumber_pmntsColumn;
@FXML private  TableColumn<Instrument,String> insBaseColumn;
@FXML private  TableColumn<Instrument,Date> insMaturityColumn;
@FXML private  TableColumn<Instrument,Integer> insEq_numberColumn;

    //Table Issuer
    @FXML private  TableView issuerTable;
    @FXML private TableColumn<Issuer,Integer>issEq_numberColumn;
    @FXML private TableColumn<Issuer,String>issCompany_nameColumn;
    @FXML private  TableColumn<Issuer,String>issCountryColumn;
    @FXML private  TableColumn<Issuer,String>issSectorColumn;
    @FXML private  TableColumn<Issuer,String>issReitingColumn;

//Buttons re Instruments
@FXML private Button addInsBtn;
@FXML private Button searchInsbyIsinBtn;
@FXML private Button searchInsbyCusipBtn;
@FXML private Button deleteInsBtn;
@FXML private Button showAllBtn;

//Buttons re Issuer
@FXML private  Button addIssBtn;
@FXML private  Button showIssAlllBtn;
@FXML private  Button searchIssBtn;
@FXML private  Button deleteIssBtn;

//Initialize() method for Instruments and for Issuer
    @FXML private  void initialize() {
        insIsinColumn.setCellValueFactory(cellData -> cellData.getValue().isinProperty());
        insCusipColumn.setCellValueFactory(cellData -> cellData.getValue().cusipProperty());
        insName_instrColumn.setCellValueFactory(cellData -> cellData.getValue().name_instrProperty());
        insType_instrColumn.setCellValueFactory(cellData -> cellData.getValue().type_instrProperty());
        insCurrencyColumn.setCellValueFactory(cellData -> cellData.getValue().currencyProperty());
        insCouponColumn.setCellValueFactory(cellData -> cellData.getValue().couponProperty().asObject());
        insRate_typeColumn.setCellValueFactory(cellData -> cellData.getValue().rate_typeProperty());
        insNumber_pmntsColumn.setCellValueFactory(cellData -> cellData.getValue().number_pmntsProperty().asObject());
        insBaseColumn.setCellValueFactory(cellData->cellData.getValue().baseProperty());
        insMaturityColumn.setCellValueFactory(cellData ->cellData.getValue().maturityProperty());
        insEq_numberColumn.setCellValueFactory(cellData -> cellData.getValue().eq_numberProperty().asObject());
        issEq_numberColumn.setCellValueFactory(cellData->cellData.getValue().eq_numberProperty().asObject());
        issCompany_nameColumn.setCellValueFactory(cellData->cellData.getValue().company_nameProperty());
        issCountryColumn.setCellValueFactory(cellData->cellData.getValue().countryProperty());
        issSectorColumn.setCellValueFactory(cellData->cellData.getValue().sectorProperty());
        issReitingColumn.setCellValueFactory(cellData->cellData.getValue().reitingProperty());

    }



 //Add an Instrument to the DB
    @FXML private  void insertInstrument (ActionEvent actionEvent)throws SQLException, ClassNotFoundException{
        try{
            InstrumentDAO.insertIns(isinText.getText(),couponText.getText(),currencyText.getText(),type_instrText.getText(),
                    maturityDate.getEditor().getText(),rate_typeText.getText(),cusipText.getText(),number_pmntsText.getText(),
                    name_instrText.getText(),baseText.getText(),eq_numberText.getText());
            resultArea.setText("Instrument inserted! \n");
        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while inserting instrument" +e);
            throw  e;
        }
    }

    @FXML private void showAllInstruments (ActionEvent actionEvent)throws  ClassNotFoundException,SQLException{
        try {
            //Get all Instruments information
            ObservableList<Instrument> insData=InstrumentDAO.searchInstruments();
            //Populate Instruments on TableView
            populateInstruments(insData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting instruments information from DB.\n"+e);
        }
    }
 //Populate Trade
    @FXML private  void populateInstrument(Instrument ins)throws ClassNotFoundException{
        //Declare and ObservableList for table view
        ObservableList<Instrument> insData= FXCollections.observableArrayList();
        //Add instrument to the ObservableList
        insData.add(ins);
        //Set item to the instrumentTable
        instrumentTable.setItems(insData);
    }

    //Populate Instruments for TableView
    @FXML private  void populateInstruments(ObservableList<Instrument> insData) throws ClassNotFoundException{
        instrumentTable.setItems(insData);
    }


// Search Instrument by Isin
    @FXML private void searchInstrumentbyIsin(ActionEvent actionEvent)throws ClassNotFoundException, SQLException{
        try{
            //Get Instrument Information
            model.Instrument ins= InstrumentDAO.searchInstrumentIsin(searchIsinText.getText());
            // Populate Instrument on TableView and Display on TextArea
            populateAndShowInstrument(ins);
        } catch (SQLException e){
            e.printStackTrace();
            resultArea.clear();
            resultArea.setText("Error occurred while getting instrument information from DB.\n"+e);
        }
    }

 // Search Instrument by Cusip
    @FXML private void searchInstrumentbyCusip(ActionEvent actionEvent)throws ClassNotFoundException, SQLException{
        try{
            //Get Instrument Information
            model.Instrument ins= InstrumentDAO.searchInstrumentCusip(searchCusipText.getText());
            // Populate Instrument on TableView and Display on TextArea
            populateAndShowInstrument(ins);
        } catch (SQLException e){
            e.printStackTrace();
            resultArea.clear();
            resultArea.setText("Error occurred while getting instrument information from DB.\n"+e);
        }
    }

 //Populate Instrument for TableView and Display Instrument on TextArea
    @FXML private  void populateAndShowInstrument(Instrument ins) throws  ClassNotFoundException{
        if (ins!=null){
            populateInstrument(ins);
            setInsInfoToTextArea(ins);}
        else{
            resultArea.clear();
            resultArea.setText("This Instrument does not exist!\n");
        }
    }

 //Set Instrument information to Text Area
    @FXML private  void setInsInfoToTextArea(Instrument ins){ resultArea.clear();
        resultArea.setText("Instrument: " + ins.getIsin() + "\n" + "name: "+ins.getName_instr());
    }

 //Delete an instrument with a given isin from DB
    @FXML private  void deleteInstrument(ActionEvent actionEvent)throws  SQLException, ClassNotFoundException{
        try {
            InstrumentDAO.deleteInsWithIsin(isinDeleteText.getText());
            resultArea.clear();
            resultArea.setText("Instrument deleted! Instrument: " + isinDeleteText.getText() + "\n");
        }catch (SQLException e){
            resultArea.clear();
            resultArea.setText("Problem occured while deleting instrument"+ e);
            throw e;
        }
    }

 //Add an Issuer to the DB
    @FXML private  void insertIssuer (ActionEvent actionEvent)throws SQLException, ClassNotFoundException{
        try{
            IssuerDAO.insertIss(eq_number2Text.getText(),company_nameText.getText(),countryText.getText(),sectorText.getText(),reitingText.getText());
            resultArea.clear();
            resultArea.setText("Issuer inserted! \n");
        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while inserting issuer" +e);
            throw  e;
        }
    }

 // Show in Table all Issuers
    @FXML private void showIssuersAll(ActionEvent actionEvent)throws  ClassNotFoundException,SQLException{
        try {
            //Get all Issuers information
            ObservableList<Issuer> issData=IssuerDAO.searchIssuers();
            //Populate Issuers on TableView
            populateIssuers(issData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting issures information from DB.\n"+e);
        }
    }


 //Populate Issuers for TableView
    @FXML private  void populateIssuers(ObservableList<Issuer> issData) throws ClassNotFoundException{
        issuerTable.setItems(issData);
    }

 // Search Issuer by eq_number
    @FXML private void searchIssuer(ActionEvent actionEvent)throws ClassNotFoundException, SQLException{
        try{
            //Get Issuer Information
            model.Issuer iss=IssuerDAO.searchIssuer (searchEq_numberText.getText());
            // Populate Issuer TableView and Display on TextArea
            populateAndShowIssuer(iss);
        } catch (SQLException e){
            e.printStackTrace();
            resultArea.clear();
            resultArea.setText("Error occurred while getting issuer information from DB.\n"+e);
        }
    }
 //Populate Issuer
    @FXML private  void populateIssuer(Issuer iss)throws ClassNotFoundException{
        //Declare and ObservableList for table view
        ObservableList<Issuer> issData= FXCollections.observableArrayList();
        //Add issuer to the ObservableList
        issData.add(iss);
        //Set item to the issuerTable
        issuerTable.setItems(issData);
    }


 //Populate Instrument for TableView and Display Instrument on TextArea
    @FXML private  void populateAndShowIssuer(Issuer iss) throws  ClassNotFoundException{
        if (iss!=null){
            populateIssuer(iss);
            setIssInfoToTextArea(iss);}
        else{
            resultArea.clear();
            resultArea.setText("This Issuer does not exist!\n");
        }
    }
 //Set Issuer information to Text Area
    @FXML private  void setIssInfoToTextArea(Issuer iss){ resultArea.clear();
        resultArea.setText("Issuer: " + iss.getEq_number() + "\n" + "name: "+iss.getCompany_name());
    }

 //Delete an issuer with a given qe_number from DB
    @FXML private  void deleteIssuer(ActionEvent actionEvent)throws  SQLException, ClassNotFoundException{
        try {
            IssuerDAO.deleteIssWithId(deleteEq_numberText.getText());
            resultArea.clear();
            resultArea.setText("Issuer deleted! Issuer: " + deleteEq_numberText.getText() + "\n");
        }catch (SQLException e){
            resultArea.clear();
            resultArea.setText("Problem occured while deleting issuer"+ e);
            throw e;
        }
    }
}
