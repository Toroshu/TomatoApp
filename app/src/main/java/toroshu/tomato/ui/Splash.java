package toroshu.tomato.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import toroshu.tomato.R;


public class Splash extends AppCompatActivity {


    ImageView logo; //logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView) findViewById(R.id.logo);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    navigate(logo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void navigate(View v) {
        //YoYo.with(Techniques.Bounce).duration(500).playOn(logo);
        startActivity(new Intent(getBaseContext(), Login.class));

    }

    public void gotomid(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("http://www.madewithlove.org.in"));
        startActivity(intent);

    }


}


