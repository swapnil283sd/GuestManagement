package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class MobileCheck extends AppCompatActivity {
Button checkMobile;
String mobilecheckUrl,mobileString,rewardsString;
EditText mobileEdittext;
ProgressBar progressBar;
Bundle Extra;
    StringRequest checkMobileRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_check);

        checkMobile=(Button) findViewById(R.id.checkmobilebtn);
        mobileEdittext=(EditText) findViewById(R.id.mobileeditText);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        Extra=getIntent().getExtras();
        rewardsString=Extra.getString("rewardpoints");

        mobilecheckUrl=getString(R.string.detectingmobileurl);
        checkMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                AppController.getInstance().addToRequestQueue(checkMobileRequest);

            }
        });


     checkMobileRequest=new StringRequest(Request.Method.POST, mobilecheckUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject=new JSONObject(response);
                    String flagcheck=jsonObject.getString("sucess");
                    if(flagcheck.equals("1")){
                        Intent GuestDetails=new Intent(MobileCheck.this,GuestDetails.class);
                        GuestDetails.putExtra("mobilenumber",mobileString);
                        GuestDetails.putExtra("rewardpoints",rewardsString);
                        startActivity(GuestDetails);
                    }else{

                        Toast.makeText(MobileCheck.this, "You are about to add new member", Toast.LENGTH_SHORT).show();
                        Intent GuestDetails=new Intent(MobileCheck.this,GuestDetails.class);
                        GuestDetails.putExtra("mobilenumber",mobileString);
                        startActivity(GuestDetails);
                    }
                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        }){
         @Override
         protected Map<String, String> getParams() throws AuthFailureError {
             Map<String, String> param = new HashMap<String, String>();
             mobileString=mobileEdittext.getText().toString();
             param.put("guest_mobilenumber", mobileString);

             return param;
         }
     };
    }
}
