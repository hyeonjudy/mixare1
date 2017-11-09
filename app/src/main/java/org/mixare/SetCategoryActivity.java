package org.mixare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class SetCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setcategory_main);

        final ImageView mypage = (ImageView) findViewById(R.id.mypage);

        final CheckBox korean = (CheckBox) findViewById(R.id.korean_check);
        final CheckBox chinese = (CheckBox) findViewById(R.id.chinese_check);
        final CheckBox japanese = (CheckBox) findViewById(R.id.japanese_check);
        final CheckBox yang = (CheckBox) findViewById(R.id.yang_check);
        final CheckBox world = (CheckBox) findViewById(R.id.world_check);
        final CheckBox buffet = (CheckBox) findViewById(R.id.buffet_check);
        final CheckBox bun = (CheckBox) findViewById(R.id.bun_check);
        final CheckBox beer = (CheckBox) findViewById(R.id.beer_check);
        final CheckBox cafe = (CheckBox) findViewById(R.id.cafe_check);

        Button startarbtn = (Button) findViewById(R.id.startarbtn);

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("imageView눌림?", "눌렸다고오옹오오오옹");
                Intent intent = new Intent(SetCategoryActivity.this, MypageMainActivity.class);
                startActivity(intent);

            }
        });

        startarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tmp = "";
                if(korean.isChecked()){tmp+="한식";}
                if(chinese.isChecked()){tmp+="중식";}
                if(japanese.isChecked()){tmp+="일식";}
                if(yang.isChecked()){tmp+="양식";}
                if(world.isChecked()){tmp+="세계음식";}
                if(buffet.isChecked()){tmp+="뷔페";}
                if(bun.isChecked()){tmp+="분식";}
                if(beer.isChecked()){tmp+="주점";}
                if(cafe.isChecked()){tmp+="카페";}

                if(tmp==""){
                    Toast.makeText(SetCategoryActivity.this, "하나 이상을 선택해주세요!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(SetCategoryActivity.this, tmp, Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(SetCategoryActivity.this, MixView.class);
                startActivity(intent);

            }
        });
    }
}