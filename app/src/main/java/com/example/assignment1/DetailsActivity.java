package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.setTitle("Enter Details");
    }

    /**
     * This method reads in the users loan details, calculates the monthly payment,
     * and then sends it to the next activity which will display the payment amount
     * @param v: View object representing the pressed button
     */
    public void calculationActivity(View v) {
        //The following three lines read the numbers entered in the boxes on the page
        EditText principal_amount_text = (EditText) findViewById(R.id.principal_amount);
        EditText interest_rate_text = (EditText) findViewById(R.id.interest_rate);
        EditText amortization_period_text = (EditText) findViewById(R.id.amortization_period);

        //This if statement is used for validation to ensure all loan details are filled by the user
        if (TextUtils.isEmpty(principal_amount_text.getText().toString()) || TextUtils.isEmpty(interest_rate_text.getText().toString())
        || TextUtils.isEmpty(amortization_period_text.getText().toString())) {
            Toast.makeText(this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
        } else { //Go to else if all loan detailes have been filled out

            //The following three lines convert the text values read above into numerical values
            double principal_amount = Double.parseDouble(principal_amount_text.getText().toString());
            //Divide by 12 to get monthly interest and then by 100 to get it as a decimal
            double interest_rate = Double.parseDouble(interest_rate_text.getText().toString())/12/100;
            //Divide by 12 as the amortization period is given in years
            int amortization_period = Integer.parseInt(amortization_period_text.getText().toString())*12;

            //Formula to calculate the monthly payment, I have split it into numerator and denominator because it is a long formula
            double numerator = principal_amount * interest_rate * (Math.pow(1+interest_rate, amortization_period));
            double denominator = Math.pow(1+interest_rate, amortization_period) - 1;

            //Calculate monthly payment
            double monthly_payment = numerator/denominator;
            monthly_payment = Math.round(monthly_payment * 100.0) / 100.0; //Round to 2 decimal places

            Intent intent = new Intent(this, CalculationActivity.class);
            intent.putExtra("MONTHLY_PAYMENT", monthly_payment); //Pass the monthly payment to the next activity
            startActivity(intent);
        }
    }

}