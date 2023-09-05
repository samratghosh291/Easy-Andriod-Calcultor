package com.example.easycalculator;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton btnC,btnBracketOpen,btnBracketClose;
    MaterialButton btnDivide,btnMul,btnSub,btnAdd,btnEquals;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    MaterialButton btnAC,btnDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assignId(btnC,R.id.button_c);
        assignId(btnBracketOpen,R.id.button_open_bracket);
        assignId(btnBracketClose,R.id.button_closing_bracket);
        assignId(btnDivide,R.id.button_divide);
        assignId(btnMul,R.id.button_mul);
        assignId(btnSub,R.id.button_sub);
        assignId(btnAdd,R.id.button_add);
        assignId(btnEquals,R.id.button_equals);
        assignId(btn0,R.id.button_0);
        assignId(btn1,R.id.button_1);
        assignId(btn2,R.id.button_2);
        assignId(btn3,R.id.button_3);
        assignId(btn4,R.id.button_4);
        assignId(btn5,R.id.button_5);
        assignId(btn6,R.id.button_6);
        assignId(btn7,R.id.button_7);
        assignId(btn8,R.id.button_8);
        assignId(btn9,R.id.button_9);
        assignId(btnAC,R.id.button_ac);
        assignId(btnDot,R.id.button_dot);

    }


    //function for assigning the value
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate=solutionTv.getText().toString();


        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate=dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }



    // for calculation
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scope = context.initSafeStandardObjects();
            Object result = context.evaluateString(scope, data, "Javascript", 1, null);

            if (result != null) {
                String finalResult = Context.toString(result);
                if (finalResult.endsWith(".0")) {
                    finalResult = finalResult.replace(".0", "");
                }
                return finalResult;
            } else {
                return "Err";
            }
        } catch (Exception e) {
            return "Err";
        } finally {
            Context.exit();
        }
    }






}