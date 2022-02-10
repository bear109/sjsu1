package com.example.sjsu1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dinuscxj.progressbar.CircleProgressBar;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ErrorListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;

public class detailJointPurchase extends AppCompatActivity {

    // 결제
    private int stuck = 10;

    CircleProgressBar circleProgressBar;

    ImageView detail5;
    ImageButton join_button;
    boolean i = true;
    ImageButton backButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_joint_purchase);

        // 초기설정 - 해당 프로젝트(안드로이드)의 application id 값을 설정합니다. 결제와 통계를 위해 꼭 필요합니다.
        BootpayAnalytics.init(this, "[6204fae7e38c30001f15ea4b]");

        Intent intent = getIntent();

        //원형 프로그레스바
        circleProgressBar=findViewById(R.id.circle_bar);
        circleProgressBar.setMax(25);
        circleProgressBar.setProgressFormatter(new MyProgressFormatter());
        circleProgressBar.setProgress(20);


        //뒤로가기 버튼
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


        //조인 버튼
        detail5 = findViewById(R.id.detail5);
        join_button = findViewById(R.id.join_button);
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == true) {
                    detail5.setImageResource(R.drawable.detail6);
                    i = false;
                    circleProgressBar.setProgress(25);
                    Toast join_msg = Toast.makeText(detailJointPurchase.this, "Thank you for join!", Toast.LENGTH_LONG);
                    join_msg.show();

                    //결제 테스트
                    BootUser bootUser = new BootUser().setPhone("010-1234-5678");
                    BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0,2,3});

                    Bootpay.init(getFragmentManager())
                            .setApplicationId("6204fae7e38c30001f15ea4b") // 해당 프로젝트(안드로이드)의 application id 값
                .setPG(PG.INICIS) // 결제할 PG 사
                            .setMethod(Method.CARD) // 결제수단
                            .setContext(detailJointPurchase.this)
                            .setBootUser(bootUser)
                            .setBootExtra(bootExtra)
                            .setUX(UX.PG_DIALOG)
//                .setUserPhone("010-1234-5678") // 구매자 전화번호
                            .setName("Tissue") // 결제할 상품명
                            .setOrderId("1234") // 결제 고유번호expire_month
                            .setPrice(5000) // 결제할 금액
                            .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                                @Override
                                public void onConfirm(@Nullable String message) {

                                    if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                    else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                    Log.d("confirm", message);
                                }
                            })
                            .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                                @Override
                                public void onDone(@Nullable String message) {
                                    Log.d("done", message);
                                }
                            })
                            .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                                @Override
                                public void onReady(@Nullable String message) {
                                    Log.d("ready", message);
                                }
                            })
                            .onCancel(new CancelListener() { // 결제 취소시 호출
                                @Override
                                public void onCancel(@Nullable String message) {

                                    Log.d("cancel", message);
                                }
                            })
                            .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                                @Override
                                public void onError(@Nullable String message) {
                                    Log.d("error", message);
                                }
                            })
                            .onClose(
                                    new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                        @Override
                                        public void onClose(String message) {
                                            Log.d("close", "close");
                                        }
                                    })
                            .request();



                } else {
                    detail5.setImageResource(R.drawable.detail5);
                    i=true;
                    circleProgressBar.setProgress(20);
                }
            }
        });
    }

    //원형프로그레스바 설정
    private static final class MyProgressFormatter implements CircleProgressBar.ProgressFormatter {
        private static final String DEFAULT_PATTERN = "%d$/%d$";

        @Override
        public CharSequence format(int progress, int max) {
            max = 25;
            return String.format(DEFAULT_PATTERN, (int) progress,(int) max);
        }
    }
}


