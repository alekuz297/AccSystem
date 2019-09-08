package model;

import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by Alex on 3/25/2017.
 */
public class Trade {

    //Declare Trade Table Columns
    private final IntegerProperty trade_id ;
    private final StringProperty portfolio_name;
    private final StringProperty isin ;
    private final StringProperty name;
    private final SimpleObjectProperty<Date>trade_date;
    private  final SimpleObjectProperty<Date> settlement_date;
    private  final IntegerProperty quantity;
    private final DoubleProperty price;
    private final DoubleProperty accrued_int;
    private  final StringProperty counterparty;
    private  final StringProperty side;
    private final StringProperty trader;
    private final StringProperty custody;


    //Constructor
    public Trade(){
        this.trade_id=new SimpleIntegerProperty();
        this.portfolio_name=new SimpleStringProperty();
        this.isin=new SimpleStringProperty();
        this.name=new SimpleStringProperty();
        this.trade_date=new SimpleObjectProperty<>();
        this.settlement_date =new SimpleObjectProperty<>();
        this.quantity=new SimpleIntegerProperty();
        this.price=new SimpleDoubleProperty();
        this.accrued_int=new SimpleDoubleProperty();
        this.counterparty=new SimpleStringProperty();
        this.side=new SimpleStringProperty();
        this.trader=new SimpleStringProperty();
        this.custody=new SimpleStringProperty();
    }
//trade_id
    public int getTrade_id() {
        return trade_id.get();
    }
    public IntegerProperty trade_idProperty() {
        return trade_id;
    }
    public void setTrade_id(int trade_id) {
        this.trade_id.set(trade_id);
    }
//portfolio_name
    public String getPortfolio_name() {
        return portfolio_name.get();
    }
    public StringProperty portfolio_nameProperty() {
        return portfolio_name;
    }
    public void setPortfolio_name(String portfolio_name) {
        this.portfolio_name.set(portfolio_name);
    }
//isin
    public String getIsin() {
        return isin.get();
    }
    public StringProperty isinProperty() {
        return isin;
    }
    public void setIsin(String isin) {
        this.isin.set(isin);
    }

//name
    public String getName() { return name.get();  }

    public StringProperty nameProperty() {  return name;   }

    public void setName(String name) {
        this.name.set(name);
    }

//trade_date
    public Date getTrade_date() {
        return trade_date.get();
    }

    public SimpleObjectProperty<Date> trade_dateProperty() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date.set(trade_date);
    }

//settelement_date
    public Date getSettlement_date() {
        return settlement_date.get();
    }

    public SimpleObjectProperty<Date> settlement_dateProperty() {
        return settlement_date;
    }

    public void setSettlement_date(Date settlement_date) {
        this.settlement_date.set(settlement_date);
    }

//quantity
    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

//price

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    //accrued_int


    public double getAccrued_int() {
        return accrued_int.get();
    }

    public DoubleProperty accrued_intProperty() {
        return accrued_int;
    }

    public void setAccrued_int(double accrued_int) {
        this.accrued_int.set(accrued_int);
    }

    //counterpary
    public String getCounterparty() {
        return counterparty.get();
    }

    public StringProperty counterpartyProperty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty.set(counterparty);
    }

//side
    public String getSide() {
        return side.get();
    }

    public StringProperty sideProperty() {
        return side;
    }

    public void setSide(String side) {
        this.side.set(side);
    }

//trader
    public String getTrader() {
        return trader.get();
    }

    public StringProperty traderProperty() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader.set(trader);
    }

//custody
    public String getCustody() {
        return custody.get();
    }

    public StringProperty custodyProperty() {
        return custody;
    }

    public void setCustody(String custody) {
        this.custody.set(custody);
    }
}
