package com.optimahorizonapps.basiccalcupatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultEt: EditText
    private lateinit var newNumberEt: EditText
    private val displayOperationTv by lazy (LazyThreadSafetyMode.NONE){ findViewById<TextView>(R.id.operation_tv) }

    // Variables to hold the operands and type of calculation
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultEt = findViewById(R.id.result_et)
        newNumberEt = findViewById(R.id.newNumber_et)

        // Data input buttons
        val button0: Button = findViewById(R.id.button_zero)
        val button1: Button = findViewById(R.id.button_one)
        val button2: Button = findViewById(R.id.button_two)
        val button3: Button = findViewById(R.id.button_three)
        val button4: Button = findViewById(R.id.button_four)
        val button5: Button = findViewById(R.id.button_five)
        val button6: Button = findViewById(R.id.button_six)
        val button7: Button = findViewById(R.id.button_seven)
        val button8: Button = findViewById(R.id.button_eight)
        val button9: Button = findViewById(R.id.button_nine)
        val buttonDecimal: Button = findViewById(R.id.button_decimal)

        // Operation buttons
        val buttonEquals: Button = findViewById(R.id.button_equals)
        val buttonDivide: Button = findViewById(R.id.button_divide)
        val buttonMultiply: Button = findViewById(R.id.button_multiply)
        val buttonMinus: Button = findViewById(R.id.button_minus)
        val buttonPlus: Button = findViewById(R.id.button_plus)

        val listener = View.OnClickListener { view ->
            val b = view as Button
            newNumberEt.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDecimal.setOnClickListener(listener)

        val operationListener = View.OnClickListener { view ->
            val operation = (view as Button).text.toString()
            val value = newNumberEt.text.toString()
            if(value.isNotEmpty()) {
                performOperation(value, operation)
            }
            pendingOperation = operation
            displayOperationTv.text = pendingOperation
        }

        buttonEquals.setOnClickListener(operationListener)
        buttonDivide.setOnClickListener(operationListener)
        buttonMultiply.setOnClickListener(operationListener)
        buttonMinus.setOnClickListener(operationListener)
        buttonPlus.setOnClickListener(operationListener)

    }

    private fun performOperation(value: String, operation: String) {
        displayOperationTv.text = operation
    }
}