package org.mixare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by jooyon on 2017-09-15.
 */

public class FirstLayout extends Fragment {

    View v;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.content_main, container, false);

       // Button startarbtn = (Button) findViewById(R.id.buttonalter);
        /*startarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstLayout.this, MypageMainActivity.class);
                startActivity(i);
                Toast.makeText(FirstLayout.this, "하나 이상을 선택해주세요!", Toast.LENGTH_LONG).show();

            }
        });*/
        return v;
    }

}




