package toroshu.tomato.ninja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import toroshu.tomato.core.Constants;
import toroshu.tomato.core.Phone;

/**
 * Created by Saini on 2/5/2015.
 */
public class BootReceiver extends BroadcastReceiver {

    TelephonyManager tm;
    Phone myPhone;

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {

            myPhone = new Phone(context);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();


            tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);


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
                Toast.makeText(context, "No Action Needed...", Toast.LENGTH_LONG).show();
                myPhone.stopAlertMode();

            }

        } catch (Exception e) {
            //Ultimate catch
            //Toast.makeText(context, "Outer try: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        myPhone.setnu(0);
    }


}
