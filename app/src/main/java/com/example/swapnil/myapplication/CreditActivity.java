package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreditActivity extends AppCompatActivity {

    TextView guestName,rewardPoints;
String guestnameStr,rewardsStr,availbalStr,mobilenumberString;
Bundle extras;
Button creditButton;
    StringRequest creditRequest;
String addTransactionUrl;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        guestName=(TextView)findViewById(R.id.nameofguest);
        rewardPoints=(TextView)findViewById(R.id.rewardpoints);
        creditButton=(Button)findViewById(R.id.creditbtn);
        addTransactionUrl=getString(R.string.addtransactionurl);
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
            try {
                JSONObject responseObject=new JSONObject(response);
                String status=responseObject.getString("sucess");
                if(status.equals("1")){
                    Intent SuccessMessage=new Intent(CreditActivity.this,SuccessFullmessage.class);
                    SuccessMessage.putExtra("message",rewardsStr+" Points Credited in"+ guestnameStr+"`s GreenLeaf Wallet.");
                    startActivity(SuccessMessage);
                }else {
                    Toast.makeText(CreditActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> param = new HashMap<String, String>();
            param.put("guest_mobilenumber", mobilenumberString);
            param.put("trans_mode", "1");
            param.put("trans_value", rewardsStr);
            return param;
        }
    };
    }
}
