package controller;

import dao.AccountsDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;



import model.Results;
import util.CSVLoader;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import static dao.AccountsDAO.getDate;

/**
 * Created by Alex on 4/23/2017.
 */
public class AccountsController {

    @FXML
    private TextArea resultArea;

    @FXML
    private Label CurrentValueTotal;
    @FXML
    private DatePicker revalueDate;
    @FXML
    private DatePicker revaluePortfolioDate;
    @FXML
    private ChoiceBox choosePortfolio;
    ObservableList<String> choosePortfolioList = FXCollections.observableArrayList("afs", "htm");

   //Table Account
    @FXML
    private TableView accountTable;
    @FXML
    private TableColumn<Results, String> accIsinColumn;
    @FXML
    private TableColumn<Results, Double> accTotalIsinColumn;
    @FXML
    private TableColumn<Results, String> accCurrencyColumn;
    @FXML
    private TableColumn<Results, Double> accPriceColumn;
    @FXML
    private TableColumn<Results, Integer> accQuantityColumn;
    @FXML
    private TableColumn<Results, Date> accDateColumn;
    @FXML
    private TableColumn<Results, String> accPortfolioColumn;


    //Initialize() method for Accounts
    @FXML
    private void initialize() {
        accIsinColumn.setCellValueFactory(cellData -> cellData.getValue().isinProperty());
        accTotalIsinColumn.setCellValueFactory(cellData -> cellData.getValue().total_per_isinProperty().asObject());
        accCurrencyColumn.setCellValueFactory(cellData -> cellData.getValue().currencyProperty());
        accPriceColumn.setCellValueFactory(cellData -> cellData.getValue().rateProperty().asObject());
        accQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        accDateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        accPortfolioColumn.setCellValueFactory(cellData -> cellData.getValue().portfolio_nameProperty());


        choosePortfolio.setValue("htm");
        choosePortfolio.setItems(choosePortfolioList);

    }


    @FXML
    private void makeRevaluation(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            AccountsDAO.updateNE();
            revaluationInfoToTextArea();
        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while make re-evaluation in the database" + e);
            throw e;
        }
    }

    @FXML
    private void checkTheDate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Results revDate = AccountsDAO.getDate();
            setRevlInfoToTextArea(revDate);

        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while pre-checking date for re-evaluation" + e);
        }
    }

    //Set information about Re-evaluation to Text Area
    @FXML
    private void revaluationInfoToTextArea() {
        resultArea.clear();
        resultArea.setText("The re-evaluation process for HTM and AFS portfolios\n" + " has been done successfully.");
    }

    //Set Pre_Revaluation date  information to Text Area
    @FXML
    private void setRevlInfoToTextArea(Results revDate) {
        resultArea.setText("The last Re-evaluation has been done on: " + revDate.getDate());
    }


    @FXML
    private void clearResultArea(ActionEvent actionEvent) {
        resultArea.clear();
    }

    //Show Current Total Value of Portfolio
    @FXML
    private void currentValueTotal(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            ObservableList<Results> cur = AccountsDAO.getCurrentTotal();
            populateAccountTable(cur);
            Results res = AccountsDAO.currentValueTotalResult();
            setCurrentValuTotalToLabel(res);

        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while make SELECT operation in the database" + e);
            throw e;
        }


    }

    //Populate data into TableView
    @FXML
    private void populateAccountTable(ObservableList<Results> cur) throws ClassNotFoundException {
        accountTable.setItems(cur);
    }

    //Show current invested total value in Label
    @FXML
    private void setCurrentValuTotalToLabel(Results res) {

        Double buf = res.getTotal_per_isin();
        // System.out.println(buf);
        CurrentValueTotal.setText(customFormat("###,###,###.##", buf));

    }

    //Show total investment per isin per currency per average price
    @FXML
    private void investQuantityPrice(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Results> cur = AccountsDAO.getInvestQuantityPrice();
            populateAccountTable(cur);

        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while make SELECT operation in the database" + e);
            throw e;
        }
    }

    //Show re-evaluation figures (acc_NE) for each isin per currencies on choosen date
    @FXML
    private void revaluationPerInstrument(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Results> rev = AccountsDAO.getRevaluationPerInstrument(revalueDate.getEditor().getText());
            populateAccountTable(rev);

        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while make SELECT operation in the database" + e);
            throw e;
        }
    }

    //Show re-evaluation figures (acc_NE) for each Portfolio per isin per currencies on choosen date
    @FXML
    private void revaluationPerPortfolio(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Results> rev = AccountsDAO.getRevaluationPerPortfolio(choosePortfolio.getSelectionModel().getSelectedItem().toString(), revaluePortfolioDate.getEditor().getText());
            populateAccountTable(rev);

        } catch (SQLException e) {
            resultArea.clear();
            resultArea.setText("Problem occured while make SELECT operation in the database" + e);
            throw e;
        }
    }


    // Give custom format when show figures in Label
    public String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;

    }



}








