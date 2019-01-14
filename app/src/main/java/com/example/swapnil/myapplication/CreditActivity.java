package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CreditActivity extends AppCompatActivity {
TextView guestName,rewardPoints;
String guestnameStr,rewardsStr,availbalStr;
Bundle extras;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        guestName=(TextView)findViewById(R.id.nameofguest);
        rewardPoints=(TextView)findViewById(R.id.rewardpoints);
        Intent CreditIntent=getIntent();
        extras=CreditIntent.getExtras();
        guestnameStr=extras.getString("guestname");
        rewardsStr=extras.getString("rewardpoints");
        availbalStr=extras.getString("availbalance");

        guestName.setText(guestnameStr+", Available Balance:-"+availbalStr);
        rewardPoints.setText(rewardsStr);

    }
}
