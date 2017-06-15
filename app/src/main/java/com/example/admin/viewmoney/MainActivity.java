package com.example.admin.viewmoney;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.admin.viewmoney.pojo.ValCurs;
import com.example.admin.viewmoney.pojo.Valute;
import com.example.admin.viewmoney.service.AsyncDownload;
import java.util.List;
import static com.example.admin.viewmoney.service.AsyncDownload.getFileString;
import static com.example.admin.viewmoney.service.AsyncDownload.getListValute;
import static com.example.admin.viewmoney.service.Content.calculateSumNew;
import static com.example.admin.viewmoney.service.Content.getListNameFromValute;
import static com.example.admin.viewmoney.service.XmlToItem.xmlToItem;
import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {
    public static final String CACHE_VAL_CURS = "ValCurs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncDownload asyncDownload = new AsyncDownload();
        asyncDownload.execute();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SharedPreferences cacheValCurs = getSharedPreferences(CACHE_VAL_CURS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cacheValCurs.edit();
        String[] listNameValute;
        List<Valute> listValute = getListValute();
        if (listValute != null) {
            listNameValute = getListNameFromValute(listValute);
            editor.putString(CACHE_VAL_CURS, getFileString());
            editor.apply();
        } else {
            if(cacheValCurs.contains(CACHE_VAL_CURS)){
                ValCurs massItem = xmlToItem(cacheValCurs.getString(CACHE_VAL_CURS, ""));
                listValute = massItem.getListValute();
                listNameValute = getListNameFromValute(listValute);
            } else {
                listNameValute = new String[]{"Нет подключения к интернету!"};
            }
        }

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNameValute);
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listNameValute);
        adapter2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner2.setAdapter(adapter2);
    }

    public void onClickCalculate(View view) {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String selected2 = spinner2.getSelectedItem().toString();
        int itemSelect = spinner.getSelectedItemPosition();
        int itemSelect2 = spinner2.getSelectedItemPosition();
        EditText editText = (EditText) findViewById(R.id.editText);
        TextView textView = (TextView) findViewById(R.id.textView);
        List<Valute> listValute = getListValute();
        if(listValute!=null) {
            try {
                Double sum = Double.valueOf(String.valueOf(editText.getText()));
                textView.setText(String.valueOf(calculateSumNew(listValute, itemSelect, itemSelect2, sum)));
            } catch (NumberFormatException e) {
                textView.setText(R.string.app2);
            }
        } else {
            SharedPreferences cacheValCurs = getSharedPreferences(CACHE_VAL_CURS, Context.MODE_PRIVATE);
            if(cacheValCurs.contains(CACHE_VAL_CURS)){
                ValCurs massItem = xmlToItem(cacheValCurs.getString(CACHE_VAL_CURS, ""));
                listValute = massItem.getListValute();
                try {
                    Double sum = Double.valueOf(String.valueOf(editText.getText()));
                    textView.setText(String.valueOf(calculateSumNew(listValute, itemSelect, itemSelect2, sum)));
                } catch (NumberFormatException e) {
                    textView.setText(R.string.app2);
                }
            } else {
                textView.setText(R.string.app3);
            }
        }
    }
}
