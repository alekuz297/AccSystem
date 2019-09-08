package model;

import javafx.beans.property.*;

import java.util.Date;

/**
 * Created by Alex on 4/15/2017.
 */
public class Instrument {
    private  final StringProperty isin;
    private  final  StringProperty cusip;
    private  final StringProperty name_instr;
    private final  StringProperty type_instr;
    private  final  StringProperty currency;
    private  final DoubleProperty coupon;
    private  final  StringProperty rate_type;
    private  final IntegerProperty number_pmnts;
    private  final  StringProperty base;
    private  final SimpleObjectProperty<Date> maturity;
    private  final IntegerProperty eq_number;

// Constructor

public Instrument(){
    this.isin =new SimpleStringProperty();
    this.cusip=new SimpleStringProperty();
    this.name_instr=new SimpleStringProperty();
    this.type_instr=new SimpleStringProperty();
    this.currency=new SimpleStringProperty();
    this.coupon=new SimpleDoubleProperty();
    this.rate_type=new SimpleStringProperty();
    this.number_pmnts=new SimpleIntegerProperty();
    this.base=new SimpleStringProperty();
    this.maturity=new SimpleObjectProperty<>();
    this.eq_number=new SimpleIntegerProperty();
}
// isin
    public String getIsin() {
        return isin.get();
    }

    public StringProperty isinProperty() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin.set(isin);
    }

// cusip
    public String getCusip() {
        return cusip.get();
    }

    public StringProperty cusipProperty() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip.set(cusip);
    }

// name_instr
    public String getName_instr() {
        return name_instr.get();
    }

    public StringProperty name_instrProperty() {
        return name_instr;
    }

    public void setName_instr(String name_instr) {
        this.name_instr.set(name_instr);
    }

// type_instr
    public String getType_instr() {
        return type_instr.get();
    }

    public StringProperty type_instrProperty() {
        return type_instr;
    }

    public void setType_instr(String type_instr) {
        this.type_instr.set(type_instr);
    }

// currency
    public String getCurrency() {
        return currency.get();
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

// coupon
    public double getCoupon() {
        return coupon.get();
    }

    public DoubleProperty couponProperty() {
        return coupon;
    }

    public void setCoupon(double coupon) {
        this.coupon.set(coupon);
    }

// rate_type
    public String getRate_type() {
        return rate_type.get();
    }

    public StringProperty rate_typeProperty() {
        return rate_type;
    }

    public void setRate_type(String rate_type) {
        this.rate_type.set(rate_type);
    }

// number_pmnts
    public int getNumber_pmnts() {
        return number_pmnts.get();
    }

    public IntegerProperty number_pmntsProperty() {
        return number_pmnts;
    }

    public void setNumber_pmnts(int number_pmnts) {
        this.number_pmnts.set(number_pmnts);
    }

// base
    public String getBase() {
        return base.get();
    }

    public StringProperty baseProperty() {
        return base;
    }

    public void setBase(String base) {
        this.base.set(base);
    }

// maturity
    public Date getMaturity() {
        return maturity.get();
    }

    public SimpleObjectProperty<Date> maturityProperty() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
        this.maturity.set(maturity);
    }

// eq_number
    public int getEq_number() {
        return eq_number.get();
    }

    public IntegerProperty eq_numberProperty() {
        return eq_number;
    }

    public void setEq_number(int eq_number) {
        this.eq_number.set(eq_number);
    }
}
