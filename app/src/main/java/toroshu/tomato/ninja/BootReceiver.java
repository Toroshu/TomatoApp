package toroshu.tomato.ninja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import toroshu.tomato.core.Phone;

/**
 * Created by Saini on 2/5/2015.
 */
public class BootReceiver extends BroadcastReceiver {

    TelephonyManager tm;
    Phone myPhone;
    Context mContext;

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {
            mContext = context;
            myPhone = new Phone(context);
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();*/

            // Execute some code after 2 seconds have passed
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    bootChecks();
                }
            }, 25000);


        } catch (Exception e) {
            //Ultimate catch
            //Toast.makeText(context, "Outer try: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        myPhone.setnu(0);
    }

    void bootChecks() {
        tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);


        boolean protectionOn = myPhone.isProtectionOn();
        String newSimID = tm.getSimSerialNumber();
        String oldSimID = myPhone.getSIMId();

        if (protectionOn && newSimID.length() != 0 && !newSimID.equals(oldSimID)) {
            // sim is changed

            myPhone.startAlertMode();
            try {
                SmsManager manager = SmsManager.getDefault();
                String msg = myPhone.getUsername() +
                        "\'s phone with IMEI no. " + tm.getDeviceId() +
                        " has been stolen." +
                        " It is being used by the sender of this message.";

                manager.sendTextMessage(
                        myPhone.getFSH(),
                        null,
                        msg, null, null);

                manager.sendTextMessage(
                        myPhone.getSSH(),
                        null,
                        msg,
                        null, null);


            } catch (Exception e) {

            }
        } else {
            //Toast.makeText(context, "No Action Needed...", Toast.LENGTH_LONG).show();
            myPhone.stopAlertMode();

        }
    }

}
