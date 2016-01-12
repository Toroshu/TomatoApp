package toroshu.tomato.ui;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import toroshu.tomato.R;
import toroshu.tomato.core.Constants;

public class FeatureX extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_x);
        mContext = getApplicationContext();
    }

    public void go(View v) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        if (isGooglePlayServicesAvailable())
            mGoogleApiClient.connect();
        else
            toast("No gps");

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        toast("connected");

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            toast("location not null");
            toast(mLastLocation.getLatitude() + " " + mLastLocation.getLongitude());
            mGoogleApiClient.disconnect();
        } else {
            toast("location null");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        toast("connection suspended");
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


        toast("connection failed " + connectionResult.toString());
    }

    void toast(String s) {
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();

    }


    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        Constants.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }

            return false;
        }
        return true;
    }

}
