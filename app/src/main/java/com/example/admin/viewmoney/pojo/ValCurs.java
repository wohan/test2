package com.example.admin.viewmoney.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;

@Root
public class ValCurs {

    @Attribute
    private String Date;
    @Attribute
    private String name;
    @ElementList (entry="Valute", inline = true)
    private List<Valute> Valute;


    public void setListValute(List<Valute> listValute) {
        this.Valute = listValute;
    }

    public List<Valute> getListValute() {
        return Valute;
    }
}
