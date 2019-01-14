package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DebitMobilenumberCheck extends AppCompatActivity {
EditText mobilenumberEt;
Button mobileCheck;
String MobileStr,mobilecheckUrl;
    StringRequest checkMobileRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_mobilenumber_check);
        mobilenumberEt=(EditText)findViewById(R.id.mobileeditTextd);
        mobileCheck=(Button)findViewById(R.id.checkmobilebtnd);
        mobilecheckUrl=getString(R.string.detectingmobileurl);

        mobilecheckUrl=getString(R.string.detectingmobileurl);

        mobileCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
AppController.getInstance().addToRequestQueue(checkMobileRequest);
            }
        });

        checkMobileRequest=new StringRequest(Request.Method.POST, mobilecheckUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject=new JSONObject(response);
                    String flagcheck=jsonObject.getString("sucess");
                    if(flagcheck.equals("1")){
                        String guestfirstnameStr=jsonObject.getString("guest_firstname");
                        String guestlastnameStr=jsonObject.getString("guest_lastname");
                        String availbalanceStr=jsonObject.getString("avail_balance");

                        Intent CreditDetails=new Intent(DebitMobilenumberCheck.this,DebitActivity.class);
                        CreditDetails.putExtra("guestname",guestfirstnameStr+" "+guestlastnameStr);
                        CreditDetails.putExtra("rewardpoints",availbalanceStr);
                        CreditDetails.putExtra("mobilenumber",MobileStr);
                        startActivity(CreditDetails);
                    }else{

                        Toast.makeText(DebitMobilenumberCheck.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

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
                MobileStr=mobilenumberEt.getText().toString();
                param.put("guest_mobilenumber", MobileStr);

                return param;
            }
        };
    }
    }

