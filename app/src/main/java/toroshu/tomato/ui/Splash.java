package toroshu.tomato.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import toroshu.tomato.R;
import toroshu.tomato.core.Constants;


public class Splash extends AppCompatActivity {


    ImageView logo; //logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView) findViewById(R.id.logo);

        checkGooglePlayServicesAvailablility();

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
        //startActivity(new Intent(getBaseContext(), FeatureX.class));

    }

    public void gotomid(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("http://www.madewithlove.org.in"));
        startActivity(intent);

    }


    private void checkGooglePlayServicesAvailablility() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }

        }
    }

}


