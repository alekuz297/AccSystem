package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Trade;
import dao.TradeDAO;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Alex on 3/26/2017.
 */
public class TradeInputController {

 @FXML private TextField trdIdText;
 @FXML  private TextArea resultArea;
 @FXML private  TextField newPriceText;
 @FXML private  TextField portfolioNameText;
 @FXML private  TextField isinText;
@FXML private  TextField sideText;
@FXML private TextField quantityText;
@FXML private TextField priceText;
@FXML private TextField accruedIntText;
@FXML private  TextField nameText;
@FXML private  DatePicker tradeDateDate;
@FXML private  DatePicker settlementDateDate;
@FXML private  TextField counterpartyText;
@FXML private  TextField custodyText;
@FXML private  TextField traderText;

@FXML private TableView tradeTable;
@FXML private TableColumn<Trade,Integer> trdTradeIdColumn;
@FXML private  TableColumn<Trade, String> trdPortfolioNameColumn;
@FXML private TableColumn<Trade,String> trdIsinColumn;
@FXML private  TableColumn<Trade,String>trdSideColumn;
@FXML private  TableColumn<Trade,Integer>trdQuantityColumn;
@FXML private  TableColumn<Trade,Double>trdPriceColumn;
@FXML private  TableColumn<Trade,Double>trdAccruedIntColumn;
@FXML private  TableColumn<Trade,String>trdNameColumn;
@FXML private  TableColumn<Trade,Date>trdTradeDateColumn;
@FXML private  TableColumn<Trade,Date>trdSettlementDateColumn;
@FXML private TableColumn<Trade,String>trdCounterpartyColumn;
@FXML private  TableColumn<Trade,String>trdCustodyColumn;
@FXML private TableColumn<Trade,String>trdTraderColumn;

@FXML private Button searchTrdBtn;
@FXML private  Button searchTrdsBtn;
@FXML private  Button updatePriceBtn;
@FXML private  Button addTrdBtn;
@FXML private  Button deleteTrdBtn;


@FXML
    private ChoiceBox choosePortfolio;
    ObservableList<String> choosePortfolioList = FXCollections.observableArrayList("afs", "htm");

    @FXML
    private ChoiceBox chooseSide;
    ObservableList<String> chooseSideList = FXCollections.observableArrayList("buy", "sell");
    @FXML private ChoiceBox chooseCntrprt;
    ObservableList<String> chooseCntrprtList=FXCollections.observableArrayList("knight","rzbm","alfn","otkr","psbf","nmos","dan_witer","fxsn");
    @FXML ChoiceBox choseCustody;
    ObservableList<String> choseCustodyList=FXCollections.observableArrayList("street","euroclear","otkritye","promsvyaz");
    @FXML ChoiceBox  chooseTrader;
    ObservableList<String> chooseTraderList=FXCollections.observableArrayList("tr-1","tr-2", "tr-3","tr-4");


   // Search Trade
    @FXML private void searchTrade(ActionEvent actionEvent)throws ClassNotFoundException, SQLException{
        try{
            //Get Trade Information
            Trade trd= TradeDAO.searchTrade(trdIdText.getText());
            // Populate Trade on TableView and Display on TextArea
            populateAndShowTrade(trd);
        } catch (SQLException e){
            e.printStackTrace();
            resultArea.setText("Error occurred while getting trade information from DB.\n"+e);
        }
}
   //Search all Trades
   @FXML private void searchTrades(ActionEvent actionEvent)throws  ClassNotFoundException,SQLException{
 try {
  //Get all Trades information
  ObservableList<Trade>trdData=TradeDAO.searchTrades();
  //Populate Trades on TableView
  populateTrades(trdData);
  } catch (SQLException e){
  System.out.println("Error occurred while getting trades information from DB.\n"+e);
 }
 }

    //Initializing the controller class.
    //This method is automatically called after the fxml fail has been loaded
    @FXML private  void initialize(){
    trdTradeIdColumn.setCellValueFactory(cellData->cellData.getValue().trade_idProperty().asObject());
    trdPortfolioNameColumn.setCellValueFactory(cellData->cellData.getValue().portfolio_nameProperty());
    trdIsinColumn.setCellValueFactory(cellData->cellData.getValue().isinProperty());
    trdSideColumn.setCellValueFactory(cellData->cellData.getValue().sideProperty());
    trdQuantityColumn.setCellValueFactory(cellData->cellData.getValue().quantityProperty().asObject());
    trdPriceColumn.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());
    trdAccruedIntColumn.setCellValueFactory(cellData->cellData.getValue().accrued_intProperty().asObject());
    trdNameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
    trdTradeDateColumn.setCellValueFactory(cellData->cellData.getValue().trade_dateProperty());
    trdSettlementDateColumn.setCellValueFactory(cellData->cellData.getValue().settlement_dateProperty());
    trdCounterpartyColumn.setCellValueFactory(cellData->cellData.getValue().counterpartyProperty());
    trdCustodyColumn.setCellValueFactory(cellData->cellData.getValue().custodyProperty());
    trdTraderColumn.setCellValueFactory(cellData->cellData.getValue().traderProperty());
        choosePortfolio.setValue("htm");
        choosePortfolio.setItems(choosePortfolioList);

        chooseSide.setValue("buy");
        chooseSide.setItems(chooseSideList);

        chooseCntrprt.setValue("knight");
        chooseCntrprt.setItems(chooseCntrprtList);

        choseCustody.setValue("euroclear");
        choseCustody.setItems(choseCustodyList);

        chooseTrader.setValue("tr-1");
        chooseTrader.setItems(chooseTraderList);

 }

    //Populate Trade
    @FXML private  void populateTrade(Trade trd)throws ClassNotFoundException{
     //Declare and ObservableList for table view
        ObservableList<Trade> trdData= FXCollections.observableArrayList();
        //Add trade to the ObservableList
        trdData.add(trd);
        //Set item to the tradeTable
        tradeTable.setItems(trdData);
    }

    //Set Trade information to Text Area
    @FXML private  void setTrdInfoToTextArea(Trade trd){
     resultArea.setText("trade_id: "+ trd.getTrade_id() + "\n" + "isin: "+trd.getIsin() + "\n" + "trade_date: "+trd.getTrade_date() );
         }
    //Populate Trade for TableView and Display Trade on TextArea
    @FXML private  void populateAndShowTrade(Trade trd) throws  ClassNotFoundException{
        if (trd!=null){
            populateTrade(trd);
            setTrdInfoToTextArea(trd);}
           else{
               resultArea.setText("This Trade does not exist!\n");
            }
        }

    //Populate Trades for TableView
    @FXML private  void populateTrades(ObservableList<Trade> trdData) throws ClassNotFoundException{
        tradeTable.setItems(trdData);
    }

    //Update trade's price with the price's which is written on newPriceText field
    @FXML private void updateTradePrice(ActionEvent actionEvent) throws  SQLException,ClassNotFoundException{
        try{
        TradeDAO.updateTrdPrice(trdIdText.getText(),newPriceText.getText());
        resultArea.setText("Price has been updated for, trade id: "+ trdIdText.getText()+"\n");
            } catch (SQLException e) {
            resultArea.setText("Problem accured while updating trade" + e);
        }
        }

    //Insert an trade to the DB
    @FXML private  void insertTrade (ActionEvent actionEvent)throws SQLException, ClassNotFoundException{
        try{
            TradeDAO.insertTrd(choosePortfolio.getSelectionModel().getSelectedItem().toString(),isinText.getText(),nameText.getText(),tradeDateDate.getEditor().getText(),
                    settlementDateDate.getEditor().getText(),quantityText.getText(),priceText.getText(),accruedIntText.getText(),
                    chooseCntrprt.getSelectionModel().getSelectedItem().toString(),chooseSide.getSelectionModel().getSelectedItem().toString(),chooseTrader.getSelectionModel().getSelectedItem().toString(),choseCustody.getSelectionModel().getSelectedItem().toString());
            resultArea.setText("Trade inserted! \n");
           } catch (SQLException e) {
            resultArea.setText("Problem occured while inserting trade" +e);
            throw  e;
        }
    }
    //Delete an trade with a given trade_id from DB
    @FXML private  void deleteTrade(ActionEvent actionEvent)throws  SQLException, ClassNotFoundException{
        try {
            TradeDAO.deleteTrdWithId(trdIdText.getText());
            resultArea.setText("Trade deleted! Trade_id: " + trdIdText.getText() + "\n");
        }catch (SQLException e){
            resultArea.setText("Problem occured while deleting trade"+ e);
            throw e;
        }
    }

 }


















