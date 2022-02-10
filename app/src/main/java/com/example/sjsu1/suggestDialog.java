package com.example.sjsu1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class suggestDialog {
    private Context context;

    String[] articles = { "Charger", "Toothbrush", "Mask", "Wet tissue", "hanger"};
    Number[] amounts = {5,10,15,20};


    public suggestDialog(Context context) {
        this.context= context;
    }


    public void callFunction(final LinearLayout article5_layout) {
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.suggest_dialog);
        dlg.show();

        final Spinner articleSpinner = (Spinner) dlg.findViewById(R.id.article_spinner);
        final Spinner amountSpinner = (Spinner) dlg.findViewById(R.id.amount_spinner);
        final Button dialogSuggestButton = (Button)  dlg.findViewById(R.id.dialog_suggest_button);
        final Button dialogCancelButton = (Button) dlg.findViewById(R.id.dialog_cancel_button);

        ArrayAdapter<String> articleAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, articles);
        articleSpinner.setAdapter(articleAdapter);

        ArrayAdapter<Number> amountAdapter = new ArrayAdapter<Number>(context, android.R.layout.simple_spinner_dropdown_item, amounts);
        amountSpinner.setAdapter(amountAdapter);



        //dialog suggest 버튼 클릭
        dialogSuggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                article5_layout.setVisibility(View.VISIBLE);
                dlg.dismiss();
            }
        });

        //dialog cancel 버튼 클릭
        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });
    }
}
