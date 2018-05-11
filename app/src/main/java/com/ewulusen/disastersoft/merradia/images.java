package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class images extends AppCompatActivity {

    public static Intent intent;
    public static String id;
    public ImageView h1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        h1=findViewById(R.id.imageView2);
        switch(id){
            case "1":
                h1.setImageResource(R.drawable.help1);
                break;
            case "2":
                h1.setImageResource(R.drawable.help2);
                break;
            case "3":
                h1.setImageResource(R.drawable.help3);
                break;
            case "4":
                h1.setImageResource(R.drawable.help4);
                break;case "5":
                h1.setImageResource(R.drawable.help5);
                break;
        }
    }
}
