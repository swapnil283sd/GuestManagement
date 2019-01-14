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

public class DebitActivity extends AppCompatActivity {
String rewardPoints,guestName,mobileNumberStr,addTransactionUrl;
TextView guestNameTxt,rewardPointsTxt;
Button debitBtn;
StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);
        addTransactionUrl=getString(R.string.addtransactionurl);
        guestNameTxt=(TextView)findViewById(R.id.nameofguestdebit);
        rewardPointsTxt=(TextView)findViewById(R.id.rewardpointsdebit);
        debitBtn=(Button)findViewById(R.id.debitbtn);

        debitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.getInstance().addToRequestQueue(stringRequest);
            }
        });

    rewardPoints=getIntent().getExtras().getString("rewardpoints");
    guestName=getIntent().getExtras().getString("guestname");
    mobileNumberStr=getIntent().getExtras().getString("mobilenumber");

    guestNameTxt.setText(guestName);
    rewardPointsTxt.setText(rewardPoints);



    stringRequest=new StringRequest(Request.Method.POST, addTransactionUrl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject responseObject=new JSONObject(response);
                String status=responseObject.getString("sucess");
                if(status.equals("1")){
                    Intent SuccessMessage=new Intent(DebitActivity.this,SuccessFullmessage.class);
                    SuccessMessage.putExtra("message",rewardPoints+" Points Debited from"+ guestName+"`s GreenLeaf Wallet.");
                    startActivity(SuccessMessage);
                }else {
                    Toast.makeText(DebitActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> param = new HashMap<String, String>();
            param.put("guest_mobilenumber", mobileNumberStr);
            param.put("trans_mode", "0");
            param.put("trans_value", rewardPoints);
            return param;
        }
    }
    ;



       // Toast.makeText(this, guestName+" "+rewardPoints, Toast.LENGTH_SHORT).show();

    }
}
