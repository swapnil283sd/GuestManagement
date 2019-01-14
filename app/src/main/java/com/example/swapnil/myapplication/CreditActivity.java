package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class CreditActivity extends AppCompatActivity {

    TextView guestName,rewardPoints;
String guestnameStr,rewardsStr,availbalStr,mobilenumberString;
Bundle extras;
Button creditButton;
    StringRequest creditRequest;
String addTransactionUrl=getString(R.string.addtransactionurl);

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        guestName=(TextView)findViewById(R.id.nameofguest);
        rewardPoints=(TextView)findViewById(R.id.rewardpoints);
        creditButton=(Button)findViewById(R.id.creditbtn);

        Intent CreditIntent=getIntent();
        extras=CreditIntent.getExtras();
        guestnameStr=extras.getString("guestname");
        rewardsStr=extras.getString("rewardpoints");
        availbalStr=extras.getString("availbalance");
        mobilenumberString=extras.getString("mobilenumber");

        guestName.setText(guestnameStr+", Available Balance:-"+availbalStr);
        rewardPoints.setText(rewardsStr);

        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.getInstance().addToRequestQueue(creditRequest);
            }
        });

    creditRequest=new StringRequest(Request.Method.POST, addTransactionUrl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
    }
}
