package toroshu.tomato.deprecated;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import toroshu.tomato.R;
import toroshu.tomato.utils.Phone;

public class FeatureX extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_x);
    }

    public void trySomethingNew(View v) {
        Phone myPhone = new Phone(getApplicationContext());
        TelephonyManager tm;
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


        boolean protectionOn = myPhone.isProtectionOn();
        String newSimID = tm.getSimSerialNumber();
        String oldSimID = myPhone.getSIMId();

        if (protectionOn && newSimID.length() != 0 && !newSimID.equals(oldSimID)) {
            // sim is changed
            myPhone.toast("sim is changed");

            myPhone.startAlertMode();
           /* try {
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

            }*/
        } else {
            myPhone.toast("No Action Needed...");
            myPhone.stopAlertMode();

        }
        if (myPhone.isAlertModeOn()) {
            myPhone.toast("Sim has been swapped");
        } else {
            myPhone.toast("No action needed");
        }
    }


}
