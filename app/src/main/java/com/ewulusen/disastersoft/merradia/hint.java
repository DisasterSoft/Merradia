package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class hint extends AppCompatActivity {

    public static Intent intent;
    public static String id;
    public ImageView h1,h2,h3,h4,h5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        intent = getIntent();
        id = intent.getStringExtra("datas");
        h1=findViewById(R.id.image1);
        h2=findViewById(R.id.image2);
        h3=findViewById(R.id.image3);
        h4=findViewById(R.id.image4);
        h5=findViewById(R.id.image5);
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(hint.this, images.class);
                intent2.putExtra("datas", "1");
                startActivity(intent2);
            }
        });
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(hint.this, images.class);
                intent2.putExtra("datas", "2");
                startActivity(intent2);
            }
        });
        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(hint.this, images.class);
                intent2.putExtra("datas", "3");
                startActivity(intent2);
            }
        });
        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(hint.this, images.class);
                intent2.putExtra("datas", "4");
                startActivity(intent2);
            }
        });
        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = null;
                intent2 = new Intent(hint.this, images.class);
                intent2.putExtra("datas", "5");
                startActivity(intent2);
            }
        });
    }
}
