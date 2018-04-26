package com.ewulusen.disastersoft.merradia;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import br.com.bloder.magic.view.MagicButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class mainScreen extends AppCompatActivity implements  RewardedVideoAdListener{
    public static Intent intent;
    private RewardedVideoAd mRewardedVideoAd;
    public static String id,idC,seged;
    public static String[] seged_a;
    DatabaseHelper userDB;
    TextView kiir;
    MagicButton  mkchar,editChar,fight,trainer,blacksmith,armorer;
    RewardedVideoAd mAd;
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
        //MobileAds.initialize(this, "ca-app-pub-7036765120364615/6159648359");
       MobileAds.initialize(this, "ca-app-pub-3940256099942544/5224354917");
        mAd=MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        mAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());
        MobileAds.setAppVolume(0.5f);
        Cursor localCursor=userDB.getName(idC);
        AdView adView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
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
                    finish();

                }
            });
            blacksmith.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, Blacksmith.class);
                    intent2.putExtra("datas", seged);
                    startActivity(intent2);
                    finish();

                }
            });
            armorer.setMagicButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = null;
                    intent2 = new Intent(mainScreen.this, Armorer.class);
                    intent2.putExtra("datas", seged);
                    startActivity(intent2);
                    finish();
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
                    finish();

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
    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(mainScreen.this, getString(R.string.reward),
                Toast.LENGTH_SHORT).show();
        Log.d("An ad has loaded","a"+reward.getAmount());
        userDB.chesFound(idC,reward.getAmount());
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d("An ad has loaded","a");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Log.d("An ad has loaded","b");
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.d("An ad has loaded","c");
        if(mAd.isLoaded())
        {
            mAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.d("An ad has loaded","d");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.d("An ad has loaded","e");
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.d("An ad has loaded","f");
    }

    @Override
    public void onResume() {
         mAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mAd.destroy(this);
        super.onDestroy();
    }

}
