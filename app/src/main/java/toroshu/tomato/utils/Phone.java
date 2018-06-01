package toroshu.tomato.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.widget.Toast;

/**
 * Created by saini on 08-01-2016.
 */
public class Phone {

    SharedPreferences prefs;
    Context mContext;
    SharedPreferences.Editor editor;


    public Phone(Context context) {
        mContext = context;
        prefs = mContext.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public boolean isRegistered() {
        return prefs.getBoolean(Constants.ALREADY_REGISTERED, false);
    }

    public String getUsername() {
        return prefs.getString(Constants.USERNAME, null);
    }

    public void setUsername(String username) {
        editor.putString(Constants.USERNAME, username);
        editor.commit();
    }

    public String getPassword() {
        return prefs.getString(Constants.PASSWORD, null);
    }

    public void setPassword(String password) {
        editor.putString(Constants.PASSWORD, password);
        editor.commit();
    }

    public void registerPhone(String firstSuperHero, String secondSuperHero) {

        editor.putString(Constants.FIRST_SUPERHERO, firstSuperHero);

        editor.putString(Constants.SECOND_SUPERHERO, secondSuperHero);
        editor.putBoolean(Constants.ALREADY_REGISTERED, true);
        editor.putString(Constants.PIN, String.valueOf(System.currentTimeMillis()).substring(6, 10));
        editor.apply();
    }

    public String getFSH() {
        return prefs.getString(Constants.FIRST_SUPERHERO, null);
    }

    public boolean isProtectionOn() {
        return prefs.getBoolean(Constants.PROTECTION_ON, false);
    }

    public void disableProtection() {
        editor.putBoolean(Constants.PROTECTION_ON, false);
        editor.apply();
    }

    public String getSIMId() {
        return prefs.getString(Constants.SIM_ID, null);
    }

    public void setSIMId(String id) {
        editor.putBoolean(Constants.PROTECTION_ON, true);
        editor.putString(Constants.SIM_ID, id);
        editor.commit();
    }

    public void startAlertMode() {
        editor.putBoolean(Constants.ALERT_MODE, true);
        editor.apply();
    }

    public boolean isAlertModeOn() {
        return prefs.getBoolean(Constants.ALERT_MODE, false);
    }

    public void stopAlertMode() {
        editor.putBoolean(Constants.ALERT_MODE, false);
        editor.apply();
    }

    public String getSSH() {
        return prefs.getString(Constants.SECOND_SUPERHERO, null);
    }

    public void setnu(int n) {
        editor.putInt(Constants.NUM_UPDATES, n);
        editor.apply();
    }

    public boolean getTrackingStatus() {
        return prefs.getBoolean(Constants.TRACK, false);
    }

    public void setTrackingStatus(boolean status) {
        editor.putBoolean(Constants.TRACK, status);
        editor.apply();
    }

    public Typeface getTypeface() {
        return Typeface.createFromAsset(mContext.getAssets(), "fonts/latom.ttf");
    }

    public void toast(String s) {
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
    }


}
