package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    TextView answer;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        answer = findViewById(R.id.answer);
        addBtn = findViewById(R.id.addBtn);
    }

    public void add(View v) {
        double ans = Float.parseFloat(num1.getText().toString()) + Float.parseFloat(num2.getText().toString());
        answer.setText(String.format("%f", ans));
    }
    public void subtract(View v) {
        double ans = Float.parseFloat(num1.getText().toString()) - Float.parseFloat(num2.getText().toString());
        answer.setText(String.format("%f", ans));
    }
    public void multiply(View v){
        double ans = Float.parseFloat(num1.getText().toString()) * Float.parseFloat(num2.getText().toString());
        answer.setText(String.format("%f", ans));
    }
    public void divide(View v) {
        try {
            double ans = Float.parseFloat(num1.getText().toString()) / Float.parseFloat(num2.getText().toString());
            answer.setText(String.format("%f", ans));
        } catch (Exception e){
            answer.setText("Cannot Divide by Zero");
        }
    }
}
