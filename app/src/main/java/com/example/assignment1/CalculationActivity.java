package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        this.setTitle("Monthly Payment Info");

        Intent intent = getIntent();
        double monthly_payment = intent.getDoubleExtra("MONTHLY_PAYMENT", 0.0);

        TextView monthly_payment_text = (TextView) findViewById(R.id.monthly_payment);
        monthly_payment_text.setText("$" + monthly_payment + " per month");
    }

    /**
     * This method brings the user back to the previous activity, where they can enter loan details
     * @param v: View object representing the pressed button
     */
    public void back_button(View v){
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}