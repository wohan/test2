package com.example.admin.viewmoney.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Valute {
    @Attribute
    private String ID;
    @Element
    private int NumCode;
    @Element
    private String CharCode;
    @Element
    private int Nominal;
    @Element
    private String Name;
    @Element
    private String Value;

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public String getValue() {
        return Value;
    }
}




