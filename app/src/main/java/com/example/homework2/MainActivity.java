package com.example.homework2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity<i> extends AppCompatActivity {

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
        Button btn1 = findViewById(R.id.key_1);
        Button btn2 = findViewById(R.id.key_2);
        Button btn3 = findViewById(R.id.key_3);
        Button btn4 = findViewById(R.id.key_4);
        Button btn5 = findViewById(R.id.key_5);
        Button btn6 = findViewById(R.id.key_6);
        Button btn7 = findViewById(R.id.key_7);
        Button btn8 = findViewById(R.id.key_8);
        Button btn9 = findViewById(R.id.key_9);
        Button btn0 = findViewById(R.id.key_0);
        Button btn_plus = findViewById(R.id.key_addition);
        Button btn_min = findViewById(R.id.key_subtraction);
        Button btn_mult = findViewById(R.id.key_composition);
        Button btn_div = findViewById(R.id.key_division);
        Button btn_clear = findViewById(R.id.key_clear);
        Button btn_equal = findViewById(R.id.key_equally);
        Button btn_dot = findViewById(R.id.key_dot);

        btn1.setOnClickListener(numberButtonsClickListener);
        btn2.setOnClickListener(numberButtonsClickListener);
        btn3.setOnClickListener(numberButtonsClickListener);
        btn4.setOnClickListener(numberButtonsClickListener);
        btn5.setOnClickListener(numberButtonsClickListener);
        btn6.setOnClickListener(numberButtonsClickListener);
        btn7.setOnClickListener(numberButtonsClickListener);
        btn8.setOnClickListener(numberButtonsClickListener);
        btn9.setOnClickListener(numberButtonsClickListener);
        btn0.setOnClickListener(numberButtonsClickListener);
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