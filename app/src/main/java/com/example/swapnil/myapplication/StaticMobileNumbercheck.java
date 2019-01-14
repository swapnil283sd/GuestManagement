package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class StaticMobileNumbercheck extends AppCompatActivity {
TextView rewardsText;
Button checkrewards;
EditText MobileEtext;
String mobilecheckUrl;
StringRequest checkMobileRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_mobile_numbercheck);
        rewardsText=(TextView)findViewById(R.id.rewardsText);
        MobileEtext=(EditText)findViewById(R.id.mobileeditTextscheck);
        checkrewards=(Button)findViewById(R.id.checkmobilebtnscheck);

        mobilecheckUrl=getString(R.string.detectingmobileurl);
        checkrewards.setOnClickListener(new View.OnClickListener() {
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

                        String availbalanceStr=jsonObject.getString("avail_balance");
                        rewardsText.setText(availbalanceStr);

                    }else{

                        Toast.makeText(StaticMobileNumbercheck.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

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
               String mobileString=MobileEtext.getText().toString();
                param.put("guest_mobilenumber", mobileString);

                return param;
            }
        };

    }
}
