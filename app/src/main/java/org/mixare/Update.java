package org.mixare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class Update extends Activity  {
    /**
     * Called when the activity is first created.
     */

    private EditText editID;
    private EditText editPW;
    private EditText editPWRE;
    private EditText editNAME;
    private EditText editAGE;
    private EditText editHP;
    private EditText editEMAIL;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    //private TextView TextViewResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        //TextViewResult = (TextView)findViewById(R.id.textViewResult);

        editID = (EditText) findViewById(R.id.editID);
        editPW = (EditText) findViewById(R.id.editPW);
        editPWRE = (EditText) findViewById(R.id.editPWRE);
        editNAME = (EditText) findViewById(R.id.editNAME);
        editAGE = (EditText) findViewById(R.id.editAGE);
        editHP = (EditText) findViewById(R.id.editHP);
        editEMAIL = (EditText) findViewById(R.id.editEMAIL);

        Button buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener((View.OnClickListener) this);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        spinner1 = (Spinner) findViewById(R.id.spinner4);
        spinner2 = (Spinner) findViewById(R.id.spinner5);
        spinner3 = (Spinner) findViewById(R.id.spinner6);


        //어댑터 생성
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.taste, android.R.layout.simple_spinner_item);

        //스피너와 어댑터 연결
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);


    }
}