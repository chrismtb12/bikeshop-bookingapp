package com.example.thefrothybikecobookingsys;

import java.io.Serializable;

public class CustomerName implements Serializable {

    private String ID;
    private String A_DateTime;
    private String B_Customer_Name;
    private String C_Contact_Number;
    private String D_Bike_Arrival;
    private String E_Bike_Collection;
    private String F_Part_Quotation;
    private String G_Labour_Quotation;
    private String H_Repair_Quotation;
    private String J_In_Stock;
    private String K_Needs_Ordered;
    private String L_Addit_Info;
    private String Bike_Type;

    private double finalPrice;

    public CustomerName() {
        // This is default constructor.
    }

    public double getFinalPrice() {
        finalPrice = Double.valueOf(H_Repair_Quotation) +
                Double.valueOf(F_Part_Quotation) +
                Double.valueOf(G_Labour_Quotation)
        ;
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getA_DateTime() {
        return A_DateTime;
    }

    public void setA_DateTime(String A_DateTime) {
        this.A_DateTime = A_DateTime;
    }


    public String getB_Customer_Name() {

        return B_Customer_Name;
    }

    public void setB_Customer_Name(String B_Customer_Name) {

        this.B_Customer_Name = B_Customer_Name;
    }

    public String getC_Contact_Number() {

        return C_Contact_Number;
    }

    public void setC_Contact_Number(String C_Contact_Number) {

        this.C_Contact_Number = C_Contact_Number;
    }

    public String getD_Bike_Arrival() {

        return D_Bike_Arrival;
    }

    public void setD_Bike_Arrival(String D_Bike_Arrival) {

        this.D_Bike_Arrival = D_Bike_Arrival;
    }

    public String getE_Bike_Collection() {

        return E_Bike_Collection;
    }

    public void setE_Bike_Collection(String E_Bike_Collection) {

        this.E_Bike_Collection = E_Bike_Collection;
    }

    public String getF_Part_Quotation() {

        return F_Part_Quotation;
    }

    public void setF_Part_Quotation(String F_Part_Quotation) {

        this.F_Part_Quotation = F_Part_Quotation;
    }

    public String getG_Labour_Quotation() {

        return G_Labour_Quotation;
    }

    public void setG_Labour_Quotation(String G_Labour_Quotation) {

        this.G_Labour_Quotation = G_Labour_Quotation;
    }

    public String getH_Repair_Quotation() {

        return H_Repair_Quotation;
    }

    public void setH_Repair_Quotation(String H_Repair_Quotation) {

        this.H_Repair_Quotation = H_Repair_Quotation;

    }

    public String getJ_In_Stock() {

        return J_In_Stock;
    }

    public void setJ_In_Stock(String J_In_Stock) {

        this.J_In_Stock = J_In_Stock;
    }

    public String getK_Needs_Ordered() {

        return K_Needs_Ordered;
    }

    public void setK_Needs_Ordered(String K_Needs_Ordered) {

        this.K_Needs_Ordered = K_Needs_Ordered;
    }

    public String getL_Addit_Info() {

        return L_Addit_Info;
    }

    public void setL_Addit_Info(String L_Addit_Info) {

        this.L_Addit_Info = L_Addit_Info;
    }

    public String getBike_Type() {
        return Bike_Type;
    }

    public void setBike_Type(String Bike_Type) {
        this.Bike_Type = Bike_Type;
    }

}