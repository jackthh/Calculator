package com.example.gemyni.calculatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvDisplay;
    TableLayout tbl;
    Button btnAC, btnPercent, btnPoint, btnDel, btnDivide, btnX, btnSub, btnAdd, btnEqual;
    TableRow tblRow3, tblRow4, tblRow5, tblRow6;
    Button[] numberBtn;
    IoString data = new IoString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        tbl = findViewById(R.id.tblLayout);

        btnAC = findViewById(R.id.btnAC);
        btnAC.setOnClickListener(this);

        btnPercent = findViewById(R.id.btnPercent);
        btnPercent.setOnClickListener(this);

        btnPoint = findViewById(R.id.btnPoint);
        btnPoint.setOnClickListener(this);

        btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(this);

        btnDel = findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        btnDivide = findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(this);

        btnX = findViewById(R.id.btnX);
        btnX.setOnClickListener(this);

        btnSub = findViewById(R.id.btnSubtract);
        btnSub.setOnClickListener(this);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        tblRow3 = findViewById(R.id.tblRow3);
        tblRow4 = findViewById(R.id.tblRow4);
        tblRow5 = findViewById(R.id.tblRow5);
        tblRow6 = findViewById(R.id.tblRow6);

        numberBtn = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberBtn[i] = new Button(this);
            numberBtn[i].setId(321321 + i);
            numberBtn[i].setText(Integer.toString(i));
            numberBtn[i].setOnClickListener(this);
        }
        tblRow3.addView(numberBtn[7], 0);
        tblRow3.addView(numberBtn[8], 1);
        tblRow3.addView(numberBtn[9], 2);

        tblRow4.addView(numberBtn[4], 0);
        tblRow4.addView(numberBtn[5], 1);
        tblRow4.addView(numberBtn[6], 2);

        tblRow5.addView(numberBtn[1], 0);
        tblRow5.addView(numberBtn[2], 1);
        tblRow5.addView(numberBtn[3], 2);

        tblRow6.addView(numberBtn[0], 0);
    }

    @Override
    public void onClick(View v) {
//        boolean isNum = false;

        // Check whether button is number button or not
        for (int i = 0; i < 10; i++) {
            if (v.getId() == numberBtn[i].getId()) {
                data.Add((numberBtn[i].getText()).toString());
//                isNum = true;
            }
        }

        // Handle the operator button
        // No idea, but commenting out the if statement runs everything
//        if (isNum = false) {
        switch (v.getId()) {
            case R.id.btnAC:
                data.Clear();
                break;
            case R.id.btnAdd:
                data.Add("+");
                break;
            case R.id.btnDel:
                data.BackSpace();
                break;
            case R.id.btnDivide:
                data.Add("/");
                break;
            case R.id.btnEqual:
                this.Compute();
                break;
            case R.id.btnPercent:
                data.Add("%");
                break;
            case R.id.btnPoint:
                data.Add(".");
                break;
            case R.id.btnSubtract:
                data.Add("-");
                break;
            case R.id.btnX:
                data.Add("*");
                break;
        }
        // Display on screen
        tvDisplay.setText(data.GetData());
//        }
    }

    // Optional features
    void Compute() {

        // Percentage
        for (int i = 0; i < data.GetDataSize(); i++) {
            if (data.GetData(i) == "%") {
                Double cache = data.GetPreviousNumber(i) / 100;
                data.SetData(i, Double.toString(cache));
                data.RemovePreviousNumber(i);
            }
        }

        // Make 2 loops to maintain the computing order
        for (int i = 0; i < data.GetDataSize(); i++) {
            if (data.GetData(i) == "*") {
                // Calculate the result of the multiplication
                double result = data.GetPreviousNumber(i) * data.GetNextNumber(i);
                // Put result into the center node
                data.SetData(i, Double.toString(result));
                // Remove beside nodes
                data.RemoveBesideNumbers(i);
                continue;
            }
            // The same as above blocks
            if (data.GetData(i) == "/") {
                // Calculate the result of the division
                double result = data.GetPreviousNumber(i) / data.GetNextNumber(i);
                // Put result into the center node
                data.SetData(i, Double.toString(result));
                // Remove beside nodes
                data.RemoveBesideNumbers(i);
                continue;
            }
        }

        // The same as above blocks
        for (int i = 0; i < data.GetDataSize(); i++) {
            if (data.GetData(i) == "+") {
                // Calculate the result of the add
                double result = data.GetPreviousNumber(i) + data.GetNextNumber(i);
                // Put result into the center node
                data.SetData(i, Double.toString(result));
                // Remove beside nodes
                data.RemoveBesideNumbers(i);
                continue;
            }
            // The same as above blocks
            if (data.GetData(i) == "-") {
                // Calculate the result of the subtraction
                double result = data.GetPreviousNumber(i) - data.GetNextNumber(i);
                // Put result into the center node
                data.SetData(i, Double.toString(result));
                // Remove beside nodes
                data.RemoveBesideNumbers(i);
                continue;
            }
        }
    }

}
