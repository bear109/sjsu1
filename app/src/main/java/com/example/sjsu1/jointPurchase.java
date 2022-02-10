package com.example.sjsu1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;

public class jointPurchase extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joint_purchase);


        final LinearLayout article5_layout = (LinearLayout) findViewById(R.id.article5_layout);

        //article1 button
        TextView article1_amount = findViewById(R.id.article1_amount);
        ImageButton article1Button = findViewById(R.id.article1);
        article1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재화면의 제어권자
                        detailJointPurchase.class); // 다음넘어갈 화면
                startActivity(intent);
                Handler timeHandler = new Handler();
                timeHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        article1_amount.setText("5/5");
                        article1Button.setImageResource(R.drawable.soldout);
                    }
                }, 500);
            }
        });

        //뒤로가기 버튼
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        //suggestButton
        ImageButton suggestButton = findViewById(R.id.suggest_button);
        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suggestDialog suggestDialog = new suggestDialog(jointPurchase.this);
                suggestDialog.callFunction(article5_layout);
            }
        });

    }
}