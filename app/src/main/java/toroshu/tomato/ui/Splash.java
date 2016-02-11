package toroshu.tomato.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
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

        navigate(logo);
    }

    public void navigate(View v) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            startActivity(new Intent(getBaseContext(), Login.class));
        else
            new MaterialDialog.Builder(this)
                    .title("Important")
                    .content("Due to recent changes in permission system for Android M, the app might" +
                            " not work in Android M+ devices. We are working on a patch to fix the problem." +
                            "\n- Team Toroshu")
                    .positiveText("Got it")
                    .show();


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


    public void openFeatureX(View v) {
        startActivity(new Intent(getBaseContext(), FeatureX.class));
    }
}


