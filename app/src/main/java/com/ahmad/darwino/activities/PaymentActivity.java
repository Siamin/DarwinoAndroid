package com.ahmad.darwino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmad.darwino.R;

public class PaymentActivity extends BaseActivity {


    LinearLayout LinearOffMonny, LinearAllMoney;
    ImageView show_money, back_page, gate_zarinpal, commodity_image;
    TextView submit_offer, total_price, offer_price, final_price, ok_payment, commodity_title, commodity_price;
    EditText offer_text;
    boolean Show = false, gate_zarinpal_status = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        init();

        submit_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offer_text.getText().length() > 0) {

                } else {
                    Toast.makeText(getApplicationContext(), "لطفا کد تخفیف را وارد کنید!", Toast.LENGTH_LONG).show();
                }
            }
        });

        gate_zarinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gate_zarinpal_status) {
                    gate_zarinpal_status = false;
                    gate_zarinpal.setBackground(getResources().getDrawable(R.drawable.ic_rectangle_gary));
                } else {
                    gate_zarinpal_status = true;
                    gate_zarinpal.setBackground(getResources().getDrawable(R.drawable.ic_rectangle));
                }
            }
        });

        ok_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gate_zarinpal_status) {
                    // go to bank

                    startActivity(new Intent(PaymentActivity.this, Payment_status_activity.class));


                } else {
                    // Show Massage ' select gate'
                    Toast.makeText(getApplicationContext(), "لطفا درگاه بانکی خود را انتخاب کنید!", Toast.LENGTH_LONG).show();
                }

            }
        });

        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        show_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Show) {
                    SetAnimationTolayoutPrice(false,View.GONE,-180,R.anim.slide_down);
                } else {
                    SetAnimationTolayoutPrice(true,View.VISIBLE,0,R.anim.slide_up);
                }
            }
        });


    }

    void SetAnimationTolayoutPrice(boolean status, int Visibility, int rotation, int  animation) {

        Show = status;

        LinearAllMoney.setVisibility(Visibility);
        LinearOffMonny.setVisibility(Visibility);

        show_money.animate().rotation(rotation).setDuration(400).start();

//        Animation AnimaTion = AnimationUtils.loadAnimation(getApplicationContext(), animation);
//        LinearAllMoney.startAnimation(AnimaTion);
//        LinearOffMonny.startAnimation(AnimaTion);
//
//        AnimaTion.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation arg0) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation arg0) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation arg0) {
//
//            }
//        });

    }

    void init() {

        offer_text = findViewById(R.id.payment_offer_edittext);

        LinearAllMoney = findViewById(R.id.payment_all_money);
        LinearOffMonny = findViewById(R.id.payment_off_money);

        show_money = findViewById(R.id.payment_show_money);
        back_page = findViewById(R.id.payment_back_page);
        gate_zarinpal = findViewById(R.id.payment_gate_zarinpal);

        submit_offer = findViewById(R.id.payment_offer_submit);
        total_price = findViewById(R.id.payment_price_total);
        offer_price = findViewById(R.id.payment_price_offer);
        final_price = findViewById(R.id.payment_price_final);
        ok_payment = findViewById(R.id.payment_ok);

        commodity_image = findViewById(R.id.payment_commodity_img);
        commodity_title = findViewById(R.id.payment_commodity_title);
        commodity_price = findViewById(R.id.payment_commodity_price);

    }

    @Override
    public void loggedIn() {

    }

    @Override
    public void loginFailed(String message) {

    }

}
