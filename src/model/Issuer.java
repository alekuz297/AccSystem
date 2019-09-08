package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Alex on 4/20/2017.
 */
public class Issuer {
    private  final IntegerProperty eq_number;
    private  final  StringProperty company_name;
    private  final StringProperty country;
    private final  StringProperty sector;
    private  final  StringProperty reiting;

    public Issuer() {
        this.eq_number = new SimpleIntegerProperty();
        this.company_name = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
        this.sector = new SimpleStringProperty();
        this.reiting = new SimpleStringProperty();
    }

//eq_number
    public int getEq_number() { return eq_number.get(); }

    public IntegerProperty eq_numberProperty() {
        return eq_number;
    }

    public void setEq_number(int eq_number) { this.eq_number.set(eq_number); }

//company_name
    public String getCompany_name() {
        return company_name.get();
    }

    public StringProperty company_nameProperty() {
        return company_name;
    }


    public void setCompany_name(String company_name) {
        this.company_name.set(company_name);
    }

//country
    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

//sector
    public String getSector() {
        return sector.get();
    }

    public StringProperty sectorProperty() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector.set(sector);
    }

//reiting
    public String getReiting() {
        return reiting.get();
    }

    public StringProperty reitingProperty() {
        return reiting;
    }

    public void setReiting(String reiting) {
        this.reiting.set(reiting);
    }
}
