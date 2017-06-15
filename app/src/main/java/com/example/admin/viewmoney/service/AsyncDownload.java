package com.example.admin.viewmoney.service;

import android.os.AsyncTask;
import com.example.admin.viewmoney.pojo.ValCurs;
import com.example.admin.viewmoney.pojo.Valute;
import java.util.List;
import static com.example.admin.viewmoney.service.Content.getFileOnline;
import static com.example.admin.viewmoney.service.XmlToItem.xmlToItem;

public class AsyncDownload extends AsyncTask<String, String, String> {
    private static String fileString;
    private static List<Valute> listValute;
    private static final String path = "http://cbr.ru/scripts/XML_daily.asp";

    public static String getFileString() {
        return fileString;
    }

    public static List<Valute> getListValute() {
        return listValute;
    }

    @Override
    protected String doInBackground(String... voids) {
        fileString = getFileOnline(path);
        if (!fileString.equals("error")) {
            ValCurs massItem = xmlToItem(fileString);
            listValute = massItem.getListValute();
        } else {
            listValute = null;
        }
        return fileString;
    }

}
