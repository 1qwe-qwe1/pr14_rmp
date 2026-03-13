package com.example.pr14_rmp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextDisplay;
    private Button buttonAC, buttonBack, buttonPercent, buttonDivision;
    private Button buttonSeven, buttonEight, buttonNine, buttonMultiplication;
    private Button buttonFour, buttonFive, buttonSix, buttonMinus;
    private Button buttonOne, buttonTwo, buttonThree, buttonPlus;
    private Button buttonZero, buttonComma, buttonExpansion, buttonEqually;

    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private double secondNumber = 0;
    private boolean isNewOperation = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        setListeners();
    }

    private void initViews() {
        editTextDisplay = findViewById(R.id.editTextNumberDecimal);

        buttonAC = findViewById(R.id.buttonAC);
        buttonBack = findViewById(R.id.buttonBack);
        buttonPercent = findViewById(R.id.buttonPercent);
        buttonDivision = findViewById(R.id.buttonDivision);

        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonMultiplication = findViewById(R.id.buttonMultiplication);

        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonMinus = findViewById(R.id.buttonMinus);

        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonPlus = findViewById(R.id.buttonPlus);

        buttonZero = findViewById(R.id.buttonZero);
        buttonComma = findViewById(R.id.buttonComma);
        buttonExpansion = findViewById(R.id.buttonExpansion);
        buttonEqually = findViewById(R.id.buttonEqually);

    }

    private void setListeners() {
        buttonAC.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);

        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);

        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);

        buttonZero.setOnClickListener(this);
        buttonComma.setOnClickListener(this);
        buttonExpansion.setOnClickListener(this);
        buttonEqually.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonZero) appendNumber("0");
        else if (id == R.id.buttonOne) appendNumber("1");
        else if (id == R.id.buttonTwo) appendNumber("2");
        else if (id == R.id.buttonThree) appendNumber("3");
        else if (id == R.id.buttonFour) appendNumber("4");
        else if (id == R.id.buttonFive) appendNumber("5");
        else if (id == R.id.buttonSix) appendNumber("6");
        else if (id == R.id.buttonSeven) appendNumber("7");
        else if (id == R.id.buttonEight) appendNumber("8");
        else if (id == R.id.buttonNine) appendNumber("9");

        else if (id == R.id.buttonPlus) setOperator("+");
        else if (id == R.id.buttonMinus) setOperator("-");
        else if (id == R.id.buttonMultiplication) setOperator("×");
        else if (id == R.id.buttonDivision) setOperator("÷");
        else if (id == R.id.buttonPercent) setOperator("%");

        else if (id == R.id.buttonComma) addDecimalPoint();
        else if (id == R.id.buttonAC) clearAll();
        else if (id == R.id.buttonBack) backspace();
        else if (id == R.id.buttonExpansion) toggleSign();
        else if (id == R.id.buttonEqually) calculateResult();
    }

    private void appendNumber(String number) {
        if (isNewOperation) {
            currentInput = "";
            isNewOperation = false;
        }

        if (currentInput.length() < 15) {
            currentInput += number;
            editTextDisplay.setText(currentInput);
        }
    }

    private void addDecimalPoint() {
        if (isNewOperation) {
            currentInput = "0";
            isNewOperation = false;
        }

        if (!currentInput.contains(".")) {
            currentInput += ".";
            editTextDisplay.setText(currentInput);
        }
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
            isNewOperation = false;
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            secondNumber = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        Toast.makeText(this, "Ошибка: деление на ноль", Toast.LENGTH_SHORT).show();
                        clearAll();
                        return;
                    }
                    break;
                case "%":
                    result = firstNumber % secondNumber;
                    break;
            }

            String resultString;
            if (result == (long) result) {
                resultString = String.valueOf((long) result);
            } else {
                resultString = String.valueOf(result);
            }

            editTextDisplay.setText(resultString);
            currentInput = resultString;
            operator = "";
            isNewOperation = true;
        }
    }

    private void clearAll() {
        currentInput = "";
        operator = "";
        firstNumber = 0;
        secondNumber = 0;
        isNewOperation = true;
        editTextDisplay.setText("");
    }

    private void backspace() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            editTextDisplay.setText(currentInput);
        }
    }

    private void toggleSign() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            value = -value;

            if (value == (long) value) {
                currentInput = String.valueOf((long) value);
            } else {
                currentInput = String.valueOf(value);
            }
            editTextDisplay.setText(currentInput);
        }
    }
}