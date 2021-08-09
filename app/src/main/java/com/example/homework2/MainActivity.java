package com.example.homework2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {

    static String operator = "0";
    private Calculator calc;
    private EditText text;


    public View.OnClickListener numberButtonsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MaterialButton tv = (MaterialButton) v;
            String textFromTV = tv.getText().toString();
            text.append(textFromTV);
        }
    };
    public View.OnClickListener numberClearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text.setText("");
            calc.number2 = "";
            calc.number1 = "";
            calc.result = 0;
        }
    };
    public View.OnClickListener returnButtonsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = text.getText().toString().trim();
            if (str.length() == 0) return;
            str = str.substring(0, str.length() - 1);
            text.setText(str);
        }
    };
    public View.OnClickListener operationPlusButtonsClickListener = v -> setOperator("+");
    public View.OnClickListener operationMinButtonsClickListener = v -> setOperator("-");
    public View.OnClickListener operationDivButtonsClickListener = v -> setOperator("/");
    public View.OnClickListener operationMultButtonsClickListener = v -> setOperator("*");
    public View.OnClickListener equalButtonsClickListener = v -> Equals();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = new Calculator();
        initView();
    }

    private void initView() {
        text = findViewById(R.id.key_result);
        initButtonsClickListener();
    }

    private void initButtonsClickListener() {
        Button[] arrayButton = new Button[10];
        for (int i = 0; i < 10; i++) {
            String abc = "key_" + i;
            int resID = getResources().getIdentifier(abc, "id", getPackageName());
            arrayButton[i] = findViewById(resID);
            arrayButton[i].setOnClickListener(numberButtonsClickListener);
        }

        Button btn_plus = findViewById(R.id.key_addition);
        Button btn_min = findViewById(R.id.key_subtraction);
        Button btn_mult = findViewById(R.id.key_composition);
        Button btn_div = findViewById(R.id.key_division);
        Button btn_clear = findViewById(R.id.key_clear);
        Button btn_equal = findViewById(R.id.key_equally);
        Button btn_dot = findViewById(R.id.key_dot);


        btn_dot.setOnClickListener(numberButtonsClickListener);
        btn_clear.setOnClickListener(numberClearClickListener);
        btn_plus.setOnClickListener(operationPlusButtonsClickListener);
        btn_min.setOnClickListener(operationMinButtonsClickListener);
        btn_div.setOnClickListener(operationDivButtonsClickListener);
        btn_mult.setOnClickListener(operationMultButtonsClickListener);
        btn_equal.setOnClickListener(equalButtonsClickListener);
    }


    public void setOperator(String _operator) {
        if (calc.number1.equals("")) {
            calc.number1 = text.getText().toString().trim();
        } else {
            calc.number2 = text.getText().toString().trim();
            calc.number1 = String.valueOf(calc.result);
        }
        operator = _operator;
        text.setText("");
    }


    public void Equals() {
        String str = text.getText().toString().trim();
        if (str.length() == 0) return;

        calc.number2 = text.getText().toString().trim();
        if (calc.number1.equals("")) return;

        switch (operator) {
            case "+":
                calc.Sum();
                break;
            case "-":
                calc.Min();
                break;
            case "*":
                calc.Mult();
                break;
            case "/":
                calc.Div();
                break;

            default:
                calc.result = 0;

        }
        text.setText("" + calc.result);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("calc", calc);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        calc = savedInstanceState.getParcelable("calc");
        text = findViewById(R.id.key_result);
        text.setText(String.valueOf(calc.result));
    }
}
