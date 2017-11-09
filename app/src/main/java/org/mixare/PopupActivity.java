package org.mixare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by jooyon on 2017-09-14.
 */


public class PopupActivity  extends Activity{
    public RatingBar ratingBar;

    public TextView txtText;
    TextView resultText;
    Button button;
  /*  public TextView namer;
    public TextView categoryr;
    public TextView addrr;
    public TextView menur;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("intent성공?", "야쓰야쓰 성고오오오옹~~ 예에");
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);


        //UI 객체생성
        //txtText = (TextView) findViewById(R.id.txtText);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

       /* namer = (TextView) findViewById(R.id.namer);
        categoryr = (TextView) findViewById(R.id.categoryr);
        addrr = (TextView) findViewById(R.id.addrr);
        menur = (TextView) findViewById(R. id.menur);*/

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String[] xy = data.split("&");
        String x = xy[0]; String y = xy[1];
        Log.i("x값", x);
        Log.i("y값", y);

        SelectData task = new SelectData();
        task.execute(x, y);

        //txtText.setText(data);
    }

    class SelectData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(PopupActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //textviewresult.setText(result);
            Toast.makeText(PopupActivity.this, result, Toast.LENGTH_SHORT).show();
            Log.i("result값", result);

            if(!result.equals("0") && !result.equals("데이터를 입력하세요 ")) {
               /* String[] data = result.split("&");
                String name = data[0];
                String category = data[1];
                String addr = data[2];
                String menu = data[3];
                String price = data[4];
                String d = "/";
                String mp = menu.concat(d);
                String rr = mp.concat(price);*/


            }

        }


        @Override
        protected String doInBackground(String... params) {
            try {

                String x = (String)params[0];
                String y = (String)params[1];
                String serverURL = "http://ec2-13-124-56-231.ap-northeast-2.compute.amazonaws.com/select_Store.php";
                String postParameters = "x=" + x + "&y=" + y;

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

    //확인 버튼 클릭
    public void mOnClose(View v) {
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }



}



