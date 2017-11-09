package org.mixare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Login extends Activity implements View.OnClickListener{
    /**
     * Called when the activity is first created.
     */

    private EditText editID;
    private EditText editPW;

    //private TextView textviewresult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //textviewresult = (TextView)findViewById(R.id.textviewresult);

        editID = (EditText) findViewById(R.id.editID);
        editPW = (EditText) findViewById(R.id.editPW);

        Button buttonSelect = (Button) findViewById(R.id.buttonSelect);
        buttonSelect.setOnClickListener(this);

        Button buttonGotoJoin = (Button) findViewById(R.id.buttonGotoJoin);
        buttonGotoJoin.setOnClickListener(this);

    }

    public void onClick (View v){

        if( v.getId() == R.id.buttonSelect){
            String ID = editID.getText().toString();
            String PW = editPW.getText().toString();

            SelectData task = new SelectData();
            task.execute(ID, PW);

            editID.setText("");
            editPW.setText("");

        }else if(v.getId() == R.id.buttonGotoJoin){
           Intent intent = new Intent(this, Join.class);
            startActivity(intent);

        }


    }

    class SelectData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Login.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //textviewresult.setText(result);
            Toast.makeText(Login.this, result, Toast.LENGTH_SHORT).show();
            Log.i("result값", result);

            String cp = new String("로그인 성공");
            if(result.equals(cp)){
                Log.i("로그인 성공시 if문 들어왔는지 유무", "들어왔다고 근데 인텐트 오류라고");
                Intent i = new Intent(Login.this,SetCategoryActivity.class);

                //i.putExtra( 뭔가 데이터를 줘야한다면.. ) ;
                startActivity ( i );
            }
        }


        @Override
        protected String doInBackground(String... params) {
            try {

                String id = (String)params[0];
                String pw = (String)params[1];
                String serverURL = "http://ec2-13-124-56-231.ap-northeast-2.compute.amazonaws.com/select_Person.php";
                String postParameters = "id=" + id + "&pw=" + pw;

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