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

public class GuestDetails extends AppCompatActivity {
Button creditRewardBtn;
EditText firstnameEditText,lastnameEditText,dobedittext,mobedittext,doaedittext,moaedittext;
String firstNamestr,larstNamestr,dobstr,mobstr,doastr,moastr,addguestUrl,mobileStr,rewardStr;
    StringRequest addguestdetailRequest;
    JSONObject jsonObject;
Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_details);

        extras=getIntent().getExtras();
        mobileStr=extras.getString("mobilenumber");
        rewardStr=extras.getString("rewardpoints");

        Toast.makeText(this, mobileStr, Toast.LENGTH_SHORT).show();
        creditRewardBtn=(Button)findViewById(R.id.creditrewardsbtn);

        firstnameEditText=(EditText)findViewById(R.id.firstnameedittxt);
        lastnameEditText=(EditText)findViewById(R.id.lastnameedittxt);

        dobedittext=(EditText)findViewById(R.id.dobedittxt);
        mobedittext=(EditText)findViewById(R.id.mobedittxt);
        doaedittext=(EditText)findViewById(R.id.doaeditTxt);
        moaedittext=(EditText)findViewById(R.id.moaeditTxt);
        addguestUrl=getString(R.string.addnewguesturl);


        creditRewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppController.getInstance().addToRequestQueue(addguestdetailRequest);


            }
        });


      addguestdetailRequest=new StringRequest(Request.Method.POST, addguestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    jsonObject=new JSONObject(response);
                    String status=jsonObject.getString("success");
                    if(status.equals("1")){
                        Intent SuccessMessage=new Intent(GuestDetails.this,SuccessFullmessage.class);
                        startActivity(SuccessMessage);
                    }else{
                        Toast.makeText(GuestDetails.this, "Something Went Wrong!!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(GuestDetails.this, "Something Went Wrong!!!", Toast.LENGTH_SHORT).show();
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
                firstNamestr=firstnameEditText.getText().toString();
                larstNamestr=lastnameEditText.getText().toString();

                dobstr=dobedittext.getText().toString();
                mobstr=mobedittext.getText().toString();
                doastr=doaedittext.getText().toString();
                moastr=moaedittext.getText().toString();

                param.put("guest_mobilenumber", mobileStr);
                param.put("guest_name", firstNamestr+" "+larstNamestr);
                param.put("guest_dob", dobstr+"/"+mobstr);
                param.put("guest_doa", doastr+"/"+moastr);
                param.put("rewardpoints", rewardStr);

                return param;
            }
        };
    }
}
