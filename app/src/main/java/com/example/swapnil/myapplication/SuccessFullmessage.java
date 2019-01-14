package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessFullmessage extends AppCompatActivity {

    String nameString,rewardsPoint;
    Button doneBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_fullmessage);
        doneBtn=(Button)findViewById(R.id.donebtn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SuccessMessage=new Intent(SuccessFullmessage.this,CalculateRewards.class);
                startActivity(SuccessMessage);
            }
        });
    }
}
