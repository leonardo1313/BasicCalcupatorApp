package com.optimahorizonapps.basiccalcupatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*

private const val SAVE_OPERAND_KEY = "SaveOperandKey"
private const val PENDING_OPERATION_KEY = "PendingOperationKey"
private const val STATE_OPERAND1_STORED = "Operand1Stored"

class MainActivity : AppCompatActivity() {

//    private lateinit var resultEt: EditText
//    private lateinit var newNumberEt: EditText
//    private val displayOperationTv by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation_tv) }


    // Variables to hold the operands and type of calculation
    private var operand1: Double? = null
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        resultEt = findViewById(R.id.result_et)
//        newNumberEt = findViewById(R.id.newNumber_et)
//
//        // Data input buttons
//        val button0: Button = findViewById(R.id.button_zero)
//        val button1: Button = findViewById(R.id.button_one)
//        val button2: Button = findViewById(R.id.button_two)
//        val button3: Button = findViewById(R.id.button_three)
//        val button4: Button = findViewById(R.id.button_four)
//        val button5: Button = findViewById(R.id.button_five)
//        val button6: Button = findViewById(R.id.button_six)
//        val button7: Button = findViewById(R.id.button_seven)
//        val button8: Button = findViewById(R.id.button_eight)
//        val button9: Button = findViewById(R.id.button_nine)
//        val buttonDecimal: Button = findViewById(R.id.button_decimal)
//
//        // Operation buttons
//        val buttonEquals: Button = findViewById(R.id.button_equals)
//        val buttonDivide: Button = findViewById(R.id.button_divide)
//        val buttonMultiply: Button = findViewById(R.id.button_multiply)
//        val buttonMinus: Button = findViewById(R.id.button_minus)
//        val buttonPlus: Button = findViewById(R.id.button_plus)

        val listener = View.OnClickListener { view ->
            val b = view as Button
            newNumber_et.append(b.text)
        }

        button_zero.setOnClickListener(listener)
        button_one.setOnClickListener(listener)
        button_two.setOnClickListener(listener)
        button_three.setOnClickListener(listener)
        button_four.setOnClickListener(listener)
        button_five.setOnClickListener(listener)
        button_six.setOnClickListener(listener)
        button_seven.setOnClickListener(listener)
        button_eight.setOnClickListener(listener)
        button_nine.setOnClickListener(listener)
        button_decimal.setOnClickListener(listener)

        val operationListener = View.OnClickListener { view ->
            val operation = (view as Button).text.toString()
            try {
                val value = newNumber_et.text.toString().toDouble()
                performOperation(value, operation)
            } catch (e: NumberFormatException) {
                newNumber_et.setText("")
            }


            pendingOperation = operation
            operation_tv.text = pendingOperation
        }

        button_equals.setOnClickListener(operationListener)
        button_divide.setOnClickListener(operationListener)
        button_multiply.setOnClickListener(operationListener)
        button_minus.setOnClickListener(operationListener)
        button_plus.setOnClickListener(operationListener)
        button_negative.setOnClickListener { view ->
            val value = newNumber_et.text.toString()
            if (value.isEmpty()) {
                newNumber_et.setText("-")
            } else {
                try {
                    var doubleValue = value.toDouble()
                    doubleValue *= -1
                    newNumber_et.setText(doubleValue.toString())
                } catch (e: java.lang.NumberFormatException) {
                    //newNumber was "-" or ".", so we have to clear it
                    newNumber_et.setText("")
                }
            }
        }

    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {

            if (pendingOperation == "=") {
                pendingOperation = operation
            }

            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN // Handle attempt of dividing with 0
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        result_et.setText(operand1.toString())
        newNumber_et.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if(operand1 != null) {
            outState.putDouble(SAVE_OPERAND_KEY, operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED, true)
        }
        outState.putString(PENDING_OPERATION_KEY, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        operand1 = if(savedInstanceState.getBoolean(STATE_OPERAND1_STORED, false)) {
            savedInstanceState.getDouble(SAVE_OPERAND_KEY)
        } else {
            null
        }

        pendingOperation = savedInstanceState.getString(PENDING_OPERATION_KEY).toString()
        operation_tv.text = pendingOperation
    }
}