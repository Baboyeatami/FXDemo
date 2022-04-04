/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication_jamie;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class Table1_Model {

    String idPersons, F_Name, L_Name, Suffix, Address, PostCode;

    public Table1_Model() {
    }

    public Table1_Model(String idPersons, String F_Name, String L_Name, String Suffix, String Address, String PostCode) {
        this.idPersons = idPersons;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Suffix = Suffix;
        this.Address = Address;
        this.PostCode = PostCode;
    }

    public String getIdPersons() {
        return idPersons;
    }

    public void setIdPersons(String idPersons) {
        this.idPersons = idPersons;
    }

    public String getF_Name() {
        return F_Name;
    }

    public void setF_Name(String F_Name) {
        this.F_Name = F_Name;
    }

    public String getL_Name() {
        return L_Name;
    }

    public void setL_Name(String L_Name) {
        this.L_Name = L_Name;
    }

    public String getSuffix() {
        return Suffix;
    }

    public void setSuffix(String Suffix) {
        this.Suffix = Suffix;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String PostCode) {
        this.PostCode = PostCode;
    }

}
