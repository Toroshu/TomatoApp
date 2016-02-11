package toroshu.tomato.ninja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import toroshu.tomato.core.Phone;


public class BootReceiver extends BroadcastReceiver {

    TelephonyManager tm;
    Phone myPhone;
    Context mContext;
    int READ_SIM_SERIAL_NUMBER_PERMISSION;

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {

            mContext = context;
            myPhone = new Phone(context);

            //toast("Starting the phone");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //toast("sleeping");
                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        toast(e.toString());
                    }
                }
            }).start();


            //toast("awake");
            bootChecks();


        } catch (Exception e) {
            //toast(e.toString());
        }
    }

    void bootChecks() {
        tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        boolean protectionOn = myPhone.isProtectionOn();
        String newSimID = tm.getSimSerialNumber();
        String oldSimID = myPhone.getSIMId();

       /* if (protectionOn)
            toast("on");
        else
            toast("off");*/

        if (protectionOn && newSimID.length() != 0 && !newSimID.equals(oldSimID)) {
            // sim is changed
            //toast("sim is changed");

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
                //toast(e.toString());
            }
        } else {
            //toast("No Action Needed...");
            myPhone.stopAlertMode();
        }
    }

    public void toast(String s) {

        //Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
        Log.e("TOMATO", s);
    }

}
