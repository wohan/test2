package com.example.admin.viewmoney.service;
import android.util.Log;
import com.example.admin.viewmoney.pojo.Valute;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import static android.content.ContentValues.TAG;

public class Content {

    public static String getFileOnline(String path) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(path);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openStream(), "windows-1251"));
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            Log.e(TAG, "Ошибка чтения:  " + e.getMessage());
            return "error";
        }
        return stringBuilder.toString();
    }

    public static String [] getListNameFromValute(List<Valute> list){
        String[] listNameValute = new String[list.size()];
        int i = 0;
        for (Valute valute: list){
            listNameValute[i++] = valute.getName();
        }
        return listNameValute;
    }

    public static double calculateSumNew(
            List<Valute> list, int itemSelect1, int itemSelect2 , double sum) {
        Valute valute1 = list.get(itemSelect1);
        Valute valute2 = list.get(itemSelect2);
        double nominal1 = valute1.getNominal();
        double nominal2 = valute2.getNominal();
        double value1 = Double.valueOf(valute1.getValue().replace(",","."));
        double value2 = Double.valueOf(valute2.getValue().replace(",","."));
        return sum*(nominal2/nominal1)*(value1/value2);
    }
}
