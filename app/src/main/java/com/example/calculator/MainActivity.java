package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView solution_tv,result_tv;
    MaterialButton button_divide,button_closebracket,button_openbracket,button_c;
    MaterialButton button_7,button_multiply,button_9,button_8;
    MaterialButton button_plus,button_6,button_5,button_4;
    MaterialButton button_1,button_minis,button_3,button_2;
    MaterialButton button_equals,button_,button_0,button_AC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);

        assignId(button_divide,R.id.button_divide);
        assignId(button_closebracket,R.id.button_closebracket);
        assignId(button_openbracket,R.id.button_openbracket);
        assignId(button_c,R.id.button_c);
        assignId(button_7,R.id.button_7);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_9,R.id.button_9);
        assignId(button_8,R.id.button_8);
        assignId(button_plus,R.id.button_plus);
        assignId(button_6,R.id.button_6);
        assignId(button_5,R.id.button_5);
        assignId(button_4,R.id.button_4);
        assignId(button_1,R.id.button_1);
        assignId(button_minis,R.id.button_minis);
        assignId(button_3,R.id.button_3);
        assignId(button_2,R.id.button_2);
        assignId(button_equals,R.id.button_equals);
        assignId(button_,R.id.button_dot);
        assignId(button_0,R.id.button_0);
        assignId(button_AC,R.id.button_AC);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String datatocalculate=solution_tv.getText().toString();

        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttonText.equals("C")){
            datatocalculate=datatocalculate.substring(0,datatocalculate.length()-1);
        }else {
            datatocalculate = datatocalculate+buttonText;
        }

        solution_tv.setText(datatocalculate);

        String finalResilt=getResult(datatocalculate);

        if (!finalResilt.equals("Err")){
            result_tv.setText(finalResilt);
        }
    }

    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResilt= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResilt.endsWith(".0")){
                finalResilt=finalResilt.replace(".0","");
            }
            return finalResilt;
        }catch (Exception e) {
            return "Err";
        }
    }
}