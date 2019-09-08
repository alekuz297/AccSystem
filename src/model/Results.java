package model;

import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by Alex on 5/6/2017.
 */
public class Results {

    private final SimpleObjectProperty<Date> date;
    private  final StringProperty isin;
    private  final DoubleProperty total_per_isin;
    private  final  StringProperty currency;
    private  final DoubleProperty price;
    private  final IntegerProperty quantity;
    private  final StringProperty portfolio_name;


    public String getPortfolio_name() {
        return portfolio_name.get();
    }

    public StringProperty portfolio_nameProperty() {
        return portfolio_name;
    }

    public void setPortfolio_name(String portfolio_name) {
        this.portfolio_name.set(portfolio_name);
    }

    public Results(){
        this.date=new SimpleObjectProperty<>();
        this.isin=new SimpleStringProperty();
        this.total_per_isin=new SimpleDoubleProperty();
        this.currency=new SimpleStringProperty();
        this.price=new SimpleDoubleProperty();
        this.quantity=new SimpleIntegerProperty();
        this.portfolio_name=new SimpleStringProperty();

    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getIsin() {
        return isin.get();
    }

    public StringProperty isinProperty() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin.set(isin);
    }

    public double getTotal_per_isin() {
        return total_per_isin.get();
    }

    public DoubleProperty total_per_isinProperty() {
        return total_per_isin;
    }

    public void setTotal_per_isin(double total_per_isin) {
        this.total_per_isin.set(total_per_isin);
    }

    public String getCurrency() {
        return currency.get();
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public double getRate() {
        return price.get();
    }

    public DoubleProperty rateProperty() {
        return price;
    }

    public void setRate(double rate) {
        this.price.set(rate);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }
}
