package com.ewulusen.disastersoft.merradia;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import br.com.bloder.magic.view.MagicButton;
import android.widget.TextView;

public class mainScreen extends AppCompatActivity {
    public static Intent intent;
    public static String id,idC,seged;
    public static String[] seged_a;
    DatabaseHelper userDB;
    TextView kiir;
    MagicButton  mkchar,editChar,fight,trainer,blacksmith,armorer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        intent = getIntent();
        seged = intent.getStringExtra("datas");
        seged_a=seged.split(",");
        id=seged_a[0];
        idC=seged_a[1];
        userDB = new DatabaseHelper(this);
        Cursor localCursor=userDB.getName(idC);
        localCursor.moveToNext();
        if(localCursor.getCount()==0)
        {
            fight.setEnabled(false);
            editChar.setEnabled(false);
            trainer.setEnabled(false);
        }
        else {
        kiir=findViewById(R.id.welcome);
        kiir.setText("Welcome dear "+localCursor.getString(0)+"!" );
        localCursor=userDB.getCharacters(id);
        editChar=findViewById(R.id.editChar);
        fight=findViewById(R.id.fight);
        trainer=findViewById(R.id.trainer);
       blacksmith=findViewById(R.id.blacksmith);
        armorer=findViewById(R.id.armorer);

            trainer.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, Trainer.class);
                    intent2.putExtra("datas", seged);
                    startActivity(intent2);

                }
            });
            blacksmith.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, Blacksmith.class);
                    intent2.putExtra("datas", seged);
                    startActivity(intent2);

                }
            });
            armorer.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, Armorer.class);
                    intent2.putExtra("datas", seged);
                    startActivity(intent2);
                }
            });
            editChar.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, TheCharakter.class);
                    intent2.putExtra("datas", seged);
                    startActivity(intent2);

                }
            });
            fight.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, Figt_Field.class);
                    intent2.putExtra("datas", seged);
                    startActivity(intent2);

                }
            });
        }
        mkchar=findViewById(R.id.makechar);
        if(localCursor.getCount()==5)
        {
            mkchar.setEnabled(false);
        }
        else {
            mkchar.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, MakeCharakter.class);
                    intent2.putExtra("datas", id);
                    startActivity(intent2);

                }
            });
        }



    }
}
