package com.example.admin.viewmoney.service;

import android.util.Log;
import com.example.admin.viewmoney.pojo.ValCurs;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import static android.content.ContentValues.TAG;
import java.io.Reader;
import java.io.StringReader;

public class XmlToItem {
    public static ValCurs xmlToItem (String file){
        Reader reader = new StringReader(file);
        Persister serializer = new Persister( new Format("<?xml version=\"1\" encoding= \"UTF-8\" ?>"));
        ValCurs massItem = new ValCurs();
        try {
            massItem = serializer.read(ValCurs.class, reader);
            Log.i(TAG, "Десиреализация успешно!");
        } catch (Exception e) {
            Log.e(TAG, "Ошибка при десиреализции:  " + e.getMessage());
        }
        return massItem;
    }




}