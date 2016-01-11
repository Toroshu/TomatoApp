package toroshu.tomato.ninja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;

import toroshu.tomato.R;
import toroshu.tomato.core.Constants;
import toroshu.tomato.core.Phone;
/*
starts a siren sound on receiving a sms
*/
public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AudioManager audioManager = (AudioManager)
                context.getSystemService(Context.AUDIO_SERVICE);

        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String message = currentMessage.getDisplayMessageBody().trim();

                    Phone myPhone = new Phone(context);
                    String password = myPhone.getPassword();
                    int start = password.length() - 4;

                    // Show alert

                    if (message.length() == 10 && message.substring(0, 10).equalsIgnoreCase("siren " +
                            password.substring(start))) {

                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                                AudioManager.FLAG_SHOW_UI
                                        | AudioManager.FLAG_PLAY_SOUND
                        );

                        MediaPlayer siren = MediaPlayer.create(context, R.raw.siren);

                        siren.start();
                    } else {
                        //Do nothing
                    }

                } // end for loop
            } // bundle is null

        } catch (Exception e) {

        }

    }
}
