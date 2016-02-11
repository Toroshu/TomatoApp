package toroshu.tomato.ninja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;

import toroshu.tomato.R;
import toroshu.tomato.core.Phone;

/*
starts a siren sound on receiving a sms
*/
public class SMSReceiver extends BroadcastReceiver {

    private static MediaPlayer siren;

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


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

                        siren = MediaPlayer.create(context, R.raw.siren);

                        AudioManager audioManager = (AudioManager)
                                context.getSystemService(Context.AUDIO_SERVICE);

                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                                AudioManager.FLAG_SHOW_UI
                                        | AudioManager.FLAG_PLAY_SOUND
                        );

                        siren.start();
                    }

                } // end for loop
            } // bundle is null

        } catch (Exception e) {

        }

    }
}
