package com.example.swapnil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateRewards extends AppCompatActivity {
Button checkrewardsBtn,continueBtn;
String billvalueString;
int rewardsPoint;
TextView rewardTextview;
EditText rewardTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_rewards);
        checkrewardsBtn=(Button)findViewById(R.id.checkrewardsbtn);
        rewardTextview=(TextView)findViewById(R.id.rewardText);
        rewardTextEdit=(EditText)findViewById(R.id.billvalueetxt);
        continueBtn=(Button)findViewById(R.id.continueBtn);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int flagcheck=0;

                try{
                    flagcheck=Integer.valueOf(rewardTextview.getText().toString());

                }catch (Exception e){
                    flagcheck=0;
                }

                if(flagcheck>0){
                    Intent MobileChek=new Intent(CalculateRewards.this,MobileCheck.class);
                    MobileChek.putExtra("rewardpoints",flagcheck);
                    startActivity(MobileChek);
                }else {
                    Toast.makeText(CalculateRewards.this, " Please Enter Valid Inputs ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkrewardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billvalueString=rewardTextEdit.getText().toString();
                rewardsPoint=Integer.valueOf(billvalueString);
                rewardsPoint=rewardsPoint/10;
                billvalueString=String.valueOf(rewardsPoint);
                rewardTextview.setText(billvalueString);

            }
        });
    }
}
