package com.example.gemyni.calculatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvDisplay;
    TableLayout tbl;
    Button btnAC, btnPercent, btnDel, btnDivide, btnX, btnSub, btnAdd;
    TableRow tblRow3, tblRow4, tblRow5, tblRow6;
    Button[] numberBtn;
    LinkedList<String> ioString = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        tbl = findViewById(R.id.tblLayout);

        btnAC = findViewById(R.id.btnAC);
//        btnAC.setOnClickListener(this);

        btnPercent = findViewById(R.id.btnPercent);
//        btnPercent.setOnClickListener(this);

        btnDel = findViewById(R.id.btnDel);
//        btnDel.setOnClickListener(this);

        btnDivide = findViewById(R.id.btnDivide);
//        btnDivide.setOnClickListener(this);

        btnX = findViewById(R.id.btnX);
//        btnX.setOnClickListener(this);

        btnSub = findViewById(R.id.btnSubtract);
//        btnSub.setOnClickListener(this);

        btnAdd = findViewById(R.id.btnAdd);
//        btnAdd.setOnClickListener(this);

        tblRow3 = findViewById(R.id.tblRow3);
        tblRow4 = findViewById(R.id.tblRow4);
        tblRow5 = findViewById(R.id.tblRow5);
        tblRow6 = findViewById(R.id.tblRow6);

        numberBtn = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberBtn[i].setId(Integer.parseInt("btn" + i));
            numberBtn[i].setText(i);
            /*numberBtn[i].setOnClickListener(this)*/;
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
        boolean isNum = false;

        // Check whether button is number button or not
        for (int i = 0; i < 10; i++) {
            if (v.getId() == numberBtn[i].getId()) {
                ioString.add(numberBtn[i].getText().toString());
                isNum = true;
            }
        }

        // Handle the operator button
        if (isNum = false) {
            switch (v.getId()) {
                case R.id.btnAC:
                    this.Clear();
                    break;
                case R.id.btnAdd:
                    ioString.add("+");
                    break;
                case R.id.btnDel:
                    this.Backspace();
                    break;
                case R.id.btnDivide:
                    ioString.add("/");
                    break;
                case R.id.btnEqual:
                    this.Compute();
                case R.id.btnPercent:
                    ioString.add("%");
                    break;
                case R.id.btnSubtract:
                    ioString.add("-");
                    break;
                case R.id.btnX:
                    ioString.add("*");
                    break;
            }
            // Display on screen
            tvDisplay.setText(ioString.toString());
        }

    }

    // Optional features
    void Clear()
    {
        ioString.clear();
    }

    void Backspace()
    {
        ioString.removeLast();
    }

    void Compute()
    {
        // Make 2 loops to maintain the computing order
        for(int i = 0; i < ioString.size(); i++)
        {
            if (ioString.get(i) == "*")
            {
                // Calculate the result of the multiplication
                int result = Integer.parseInt(ioString.get(i - 1)) * Integer.parseInt(ioString.get(i + 1));
                // Put result into the center node
                ioString.set(i, Integer.toString(result));
                // Remove beside nodes
                ioString.remove(ioString.get(i - 1));
                ioString.remove(ioString.get(i + 1));
            }
            // The same as above blocks
            if (ioString.get(i) == "/")
            {
                int result = Integer.parseInt(ioString.get(i - 1)) / Integer.parseInt(ioString.get(i + 1));
                ioString.set(i, Integer.toString(result));
                ioString.remove(ioString.get(i - 1));
                ioString.remove(ioString.get(i + 1));
            }
        }

        // The same as above blocks
        for(int i = 0; i < ioString.size(); i++)
        {
            if (ioString.get(i) == "+")
            {
                int result = Integer.parseInt(ioString.get(i - 1)) + Integer.parseInt(ioString.get(i + 1));
                ioString.set(i, Integer.toString(result));
                ioString.remove(ioString.get(i - 1));
                ioString.remove(ioString.get(i + 1));
            }
            if (ioString.get(i) == "-")
            {
                int result = Integer.parseInt(ioString.get(i - 1)) - Integer.parseInt(ioString.get(i + 1));
                ioString.set(i, Integer.toString(result));
                ioString.remove(ioString.get(i - 1));
                ioString.remove(ioString.get(i + 1));
            }
        }
    }
}
