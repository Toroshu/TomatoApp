package toroshu.tomato.ninja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import toroshu.tomato.core.Phone;

/*
tracks location on receiving a sms from the user to do so
 */
public class TrackLocation extends BroadcastReceiver
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    GoogleApiClient mGoogleApiClient;
    Phone myPhone;
    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context.getApplicationContext();
        final Bundle bundle = intent.getExtras();
        myPhone = new Phone(context);
        //Toast.makeText(context, "SMS received", Toast.LENGTH_LONG).show();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String message = currentMessage.getDisplayMessageBody().trim();

                    String password = myPhone.getPassword();
                    int start = password.length() - 4;

                    // Show alert

                    if (message.length() == 10 && message.substring(0, 10).equalsIgnoreCase("track " +
                            password.substring(start))) {
                        // Toast toast = Toast.makeText(context, ", message: " + message, duration);
                        //toast.show();


                        if (mGoogleApiClient == null) {
                            mGoogleApiClient = new GoogleApiClient.Builder(context)
                                    .addConnectionCallbacks(this)
                                    .addOnConnectionFailedListener(this)
                                    .addApi(LocationServices.API)
                                    .build();
                        }
                        mGoogleApiClient.connect();

                        //Toast.makeText(context, "Tracking started...", Toast.LENGTH_LONG).show();
                        Toast.makeText(context, "Finding location...", Toast.LENGTH_LONG).show();

                        myPhone.setTrackingStatus(true);
                    }
                }
            }
        } catch (Exception e) {
            //Toast.makeText(context, "Try-catch" + e.toString(), Toast.LENGTH_LONG).show();
        }

    }


    public void sendSMS(double lat, double lang) {
        try {


            boolean track = myPhone.getTrackingStatus();

            //Toast.makeText(mContext, "In tracklocation\nT:" + track, Toast.LENGTH_LONG).show();

            if (track) {


                String msg = "Your stolen phone is at:\n"
                        + "http://maps.google.com/maps?q=" + lat + "," + lang
                        + "\nSent via Tomato Safety App.";

                //Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
                SmsManager manager = SmsManager.getDefault();

                manager.sendTextMessage(
                        myPhone.getFSH(),
                        null,
                        msg,
                        null, null);

                manager.sendTextMessage(
                        myPhone.getSSH(),
                        null,
                        msg,
                        null, null);

                mGoogleApiClient.disconnect();

                myPhone.setTrackingStatus(false);

            }

        } catch (Exception e) {
            //Toast.makeText(mContext, e.toString(), Toast.LENGTH_LONG).show();


        }

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //toast("connected");

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            //toast("location not null");
            sendSMS(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        } else {
            // toast("location null");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //toast("connection suspended");

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //toast("connection failed");
    }

    void toast(String s) {
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();

    }


}






