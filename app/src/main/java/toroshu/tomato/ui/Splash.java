package toroshu.tomato.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import toroshu.tomato.R;
import toroshu.tomato.core.Constants;


public class Splash extends AppCompatActivity {

    @BindView(R.id.iv_logo)
    ImageView logo; //logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        takePermissions();
        checkGooglePlayServicesAvailablility();
        startActivity(new Intent(getBaseContext(), Login.class));

    }

    private void takePermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission_group.SMS)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED
                ) {

        }
    }


    @OnClick(R.id.iv_mid)
    void viewMID() {
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


