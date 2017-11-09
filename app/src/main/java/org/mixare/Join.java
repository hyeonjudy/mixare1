package org.mixare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Join extends Activity implements View.OnClickListener{
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
        setContentView(R.layout.join);

        //TextViewResult = (TextView)findViewById(R.id.textViewResult);

        editID = (EditText) findViewById(R.id.editID);
        editPW = (EditText) findViewById(R.id.editPW);
        editPWRE = (EditText) findViewById(R.id.editPWRE);
        editNAME = (EditText) findViewById(R.id.editNAME);
        editAGE = (EditText) findViewById(R.id.editAGE);
        editHP = (EditText) findViewById(R.id.editHP);
        editEMAIL = (EditText) findViewById(R.id.editEMAIL);

        Button buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(this);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);

        findViewById(R.id.checkBox1).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Checked(v); // 체크되었을 때 동작코드
            }
        });

        // option2 체크박스가 눌렸을 때
        findViewById(R.id.checkBox2).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Checked(v); // 체크되었을 때 동작코드
            }
        });

        //어댑터 생성
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.taste, android.R.layout.simple_spinner_item);

        //스피너와 어댑터 연결
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);



    }

    public String Checked(View v) { // 체크되었을 때 동작할 메소드 구현

//        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
//        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        String resultText = ""; // 체크되었을 때 값을 저장할 스트링 값
        if (checkBox1.isChecked()) { // option1 이 체크되었다면
            resultText = "남";
        }
        if (checkBox2.isChecked()) {
            resultText = "여"; // option2 이 체크되었다면
        }

        return resultText; // 체크된 값 리턴
    }

    public void onClick (View v){

        String ID = editID.getText().toString();
        String PW = editPW.getText().toString();
        String PWRE = editPWRE.getText().toString();
        String NAME = editNAME.getText().toString();
        String AGE = editAGE.getText().toString();
        String HP = editHP.getText().toString();
        String EMAIL = editEMAIL.getText().toString();
        String gender = Checked(v);
        String attribute1 = spinner1.getSelectedItem().toString();
        String attribute2 = spinner2.getSelectedItem().toString();
        String attribute3 = spinner3.getSelectedItem().toString();

        InsertData task = new InsertData(this);
        task.execute(ID, PW, PWRE, NAME, AGE, HP, EMAIL, gender, attribute1, attribute2, attribute3);



        editID.setText("");
        editPW.setText("");
        editPWRE.setText("");
        editNAME.setText("");
        editAGE.setText("");
        editHP.setText("");
        editEMAIL.setText("");
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        String defaultsp = "속성을 선택해 주세요";
        ArrayAdapter adapter1 = (ArrayAdapter) spinner1.getAdapter();
        ArrayAdapter adapter2 = (ArrayAdapter) spinner2.getAdapter();
        ArrayAdapter adapter3 = (ArrayAdapter) spinner3.getAdapter();
        int spinnerPosition1 = adapter1.getPosition(defaultsp);
        int spinnerPosition2 = adapter2.getPosition(defaultsp);
        int spinnerPosition3 = adapter3.getPosition(defaultsp);
        spinner1.setSelection(spinnerPosition1);
        spinner2.setSelection(spinnerPosition2);
        spinner3.setSelection(spinnerPosition3);


    }



    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        Context context;
        private InsertData(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Join.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //TextViewResult.setText(result);

            Toast.makeText(Join.this, result, Toast.LENGTH_SHORT).show();
            Log.i("result값", result);

            String cp = new String("회원가입에 성공하셨습니다.");
            if(result.equals(cp)){
                Log.i("회원가입 성공시 if문 들어왔는지 유무", "들어왔다고 근데 인텐트 오류라고");
                Intent i = new Intent(Join.this,Login.class);

                        //i.putExtra( 뭔가 데이터를 줘야한다면.. ) ;
                        startActivity ( i );
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[0];
            String pw = (String)params[1];
            String pwre = (String)params[2];
            String name = (String)params[3];
            String age = (String)params[4];
            String hp = (String)params[5];
            String email = (String)params[6];
            String gender = (String)params[7];
            String attribute1 = (String)params[8];
            String attribute2 = (String)params[9];
            String attribute3 = (String)params[10];


            String serverURL = "http://ec2-13-124-56-231.ap-northeast-2.compute.amazonaws.com/insert_Person.php";
            String postParameters = "id=" + id + "&pw=" + pw + "&pwre=" + pwre + "&name=" + name + "&age=" + age + "&hp=" + hp + "&email=" + email + "&gender=" + gender + "&attribute1=" + attribute1 + "&attribute2=" + attribute2 + "&attribute3=" + attribute3;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);//서버에서 읽기 모드 지정
                httpURLConnection.setDoOutput(true);//서버에서 쓰기 모드 지정
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;


                while((line = bufferedReader.readLine()) != null){
                   sb.append(line);
                }


                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {

                return new String("Error: " + e.getMessage());

            }

        }
    }

}