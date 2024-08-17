package com.example.calculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import org.mozilla.javascript.Scriptable

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var resultTv: TextView? = null
    var solutionTv: TextView? = null
    var button_c: MaterialButton? = null
    var button_open: MaterialButton? = null
    var button_close: MaterialButton? = null
    var button_divide: MaterialButton? = null
    var button7: MaterialButton? = null
    var button8: MaterialButton? = null
    var button9: MaterialButton? = null
    var button6: MaterialButton? = null
    var button5: MaterialButton? = null
    var button4: MaterialButton? = null
    var button3: MaterialButton? = null
    var button2: MaterialButton? = null
    var button1: MaterialButton? = null
    var button0: MaterialButton? = null
    var button_dot: MaterialButton? = null
    var button_mul: MaterialButton? = null
    var button_add: MaterialButton? = null
    var button_min: MaterialButton? = null
    var button_equ: MaterialButton? = null
    var button_Ac: MaterialButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTv = findViewById(R.id.result_tv)
        solutionTv = findViewById(R.id.solution_tv)
        assignId(button_c, R.id.button_c)
        assignId(button_Ac, R.id.button_Ac)
        assignId(button_open, R.id.button_open)
        assignId(button_close, R.id.button_close)
        assignId(button_divide, R.id.button_divide)
        assignId(button_mul, R.id.button_mul)
        assignId(button_add, R.id.button_add)
        assignId(button_min, R.id.button_min)
        assignId(button_equ, R.id.button_equ)
        assignId(button_dot, R.id.button_dot)
        assignId(button0, R.id.button0)
        assignId(button1, R.id.button1)
        assignId(button2, R.id.button2)
        assignId(button3, R.id.button3)
        assignId(button4, R.id.button4)
        assignId(button5, R.id.button5)
        assignId(button6, R.id.button6)
        assignId(button7, R.id.button7)
        assignId(button8, R.id.button8)
        assignId(button9, R.id.button9)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun assignId(btn: MaterialButton?, id: Int) {
        var btn = btn
        btn = findViewById(id)
        btn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val button = view as MaterialButton
        val buttonText = button.getText().toString()
        var dataToCalculate = solutionTv!!.getText().toString()
        if (buttonText == "Ac") {
            solutionTv!!.text = ""
            resultTv!!.text = "0"
            return
        }
        if (buttonText == "=") {
            solutionTv!!.text = resultTv!!.getText()
            return
        }
        if (buttonText == "C") {
            if (dataToCalculate.length == 1 || dataToCalculate.length == 0) {
                solutionTv!!.text = ""
                resultTv!!.text = "0"
                return
            } else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length - 1)
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText
        }
        solutionTv!!.text = dataToCalculate
        val finalResult = getResult(dataToCalculate)
        if (finalResult != "Err") {
            resultTv!!.text = finalResult
        }
    }

    fun getResult(data: String?): String {
        return try {
            val context = org.mozilla.javascript.Context.enter()
            context.setOptimizationLevel(-1)
            val scriptable: Scriptable = context.initStandardObjects()
            var finalResult =
                context.evaluateString(scriptable, data, "Javascript", 1, null).toString()
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "")
            }
            finalResult
        } catch (e: Exception) {
            "Err"
        }
    }
}


