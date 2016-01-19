package toroshu.tomato.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import toroshu.tomato.R;
import toroshu.tomato.core.Phone;


public class Status extends AppCompatActivity {


    Phone myPhone;
    Context mContext;
    TelephonyManager tm;

    Vibrator vibrator;
    Toolbar toolbar;
    ImageView mStatus;

    AccountHeader headerResult;
    Drawer result;

    ButtonRectangle mturnoff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        // mAdView.loadAd(adRequest);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mContext = getApplicationContext();
        myPhone = new Phone(mContext);

        mturnoff = (ButtonRectangle) findViewById(R.id.pinButton);
        mturnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hearShakes();
            }
        });
        //mturnoff.setText(getResources().getString(R.string.title_activity_recover_password));

        initND();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);


        Toast.makeText(this, "Touch anywhere to toggle protection", Toast.LENGTH_SHORT).show();
        mStatus = (ImageView) findViewById(R.id.statusImage);


        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        if (myPhone.isProtectionOn()) {
            mStatus.setImageResource(R.drawable.dragon);

        } else {
            mStatus.setImageResource(R.drawable.sad);
            mturnoff.setVisibility(View.GONE);
        }

        mStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hearShakes();
            }
        });


    }


    private void initND() {
        String n;
        int i;
        boolean b = myPhone.isProtectionOn();
        try {
            //n = onOff();
            if (b) {
                i = R.drawable.happy;
                n = "ON";
            } else {
                i = R.drawable.sadsmiley;
                n = "OFF";
            }

        } catch (Exception e) {
            n = "Error";
            i = 0;
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.md_black_1000)
                .addProfiles(
                        new ProfileDrawerItem().withName("")
                                .withEmail("Protection Status: " + n)
                                .withIcon(getResources().getDrawable(i))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        result = new DrawerBuilder()
                .withActivity(Status.this)
                .withToolbar(toolbar).withSelectedItem(-1)
                .withActionBarDrawerToggle(true).withAccountHeader(headerResult)
                .addDrawerItems(new PrimaryDrawerItem().withName("Siren Whistle PIN").withIcon(R.drawable.ic_grade_black_24dp),
                        new PrimaryDrawerItem().withName("Change Password").withIcon(R.drawable.ic_build_black_24dp),
                        new PrimaryDrawerItem().withName("Switching to a new Sim ?").withIcon(R.drawable.ic_swap_vertical_circle_black_24dp),
                        new PrimaryDrawerItem().withName("Frequently Asked Questions").withIcon(R.drawable.ic_help_black_36dp),
                        new PrimaryDrawerItem().withName("Credits").withIcon(R.drawable.ic_favorite_black_24dp),
                        new PrimaryDrawerItem().withName("About Us").withIcon(R.drawable.ic_info_black_24dp)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Intent intent = new Intent(getBaseContext(), About.class);

                        switch (position) {
                            case 0:
                                showPin(mStatus);
                                break;
                            case 1:
                                changePassword(mStatus);
                                break;
                            case 2:
                                info(mStatus);
                                break;
                            case 3:
                                intent.putExtra("mode", 1);
                                startActivity(intent);
                                break;
                            case 4:
                                startActivity(new Intent(getApplicationContext(), Credits.class));
                                break;
                            case 5:
                                intent.putExtra("mode", 0);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                })
                .build();


    }


    private void changePassword(ImageView mStatus) {
        String oldPassword = myPhone.getPassword();
        new MaterialDialog.Builder(this)
                .title("Enter a new password")
                .content("The new password must contain atleast six characters")
                .input("Password", oldPassword, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        String newPassword = input.toString().trim();
                        if (newPassword.length() >= 6) {
                            myPhone.setPassword(newPassword);
                            Toast.makeText(getApplicationContext(),
                                    "Password has been successfully changed.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "This password can\'t be accepted", Toast.LENGTH_LONG).show();
                        }
                    }
                }).show();
    }

    private void showWarning(View v) {
        new MaterialDialog.Builder(this)
                .title("Warning")
                .icon(getResources().getDrawable(R.drawable.ic_warning_black_18dp))
                .content("You are turning off the tomato protection." +
                        " Do you want to proceed ?")
                .positiveText("Yes")
                .negativeText("No").callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                super.onPositive(dialog);
                myPhone.disableProtection();
                mStatus.setImageResource(R.drawable.sad);
                mturnoff.setVisibility(View.GONE);
                // toolbar.setBackgroundColor(getResources().getColor(R.color.md_red_300));
                initND();

            }

            @Override
            public void onNegative(MaterialDialog dialog) {
                super.onNegative(dialog);
            }
        }).show();
    }

    public void hearShakes() {

        //Toast.makeText(getApplicationContext(), "Shake me!!", Toast.LENGTH_SHORT).show();


        vibrator.vibrate(500);

        if (myPhone.isProtectionOn()) {
            showWarning(mStatus);

        } else {

            myPhone.setSIMId(tm.getSimSerialNumber());
            mStatus.setImageResource(R.drawable.dragon);

            //toolbar.setBackgroundColor(getResources().getColor(R.color.ok));
            initND();
            mturnoff.setVisibility(View.VISIBLE);
            showFeatures(mStatus);

        }


    }

    public void showFeatures(View v) {


        new MaterialDialog.Builder(this)
                .title("Info")
                .icon(getResources().getDrawable(R.drawable.ic_info_black_24dp))
                .content("Now you can:\n\t-get SIM change alerts\n\t-track your phone with a SMS" +
                        "\n\t-start siren with another SMS")
                .positiveText("Got it")
                .show();
    }


    public void showPin(View v) {
        String password = myPhone.getPassword();
        int start = password.length() - 4;

        new MaterialDialog.Builder(this)
                .title("PIN Details")
                .content("Send a custom SMS \"siren " + password.substring(start) +
                        "\" (last 4 characters of your password) from any number " +
                        "to start the siren in your phone. Check out the FAQ for more details.")
                .positiveText("Got it")
                .show();
    }


    public void info(View v) {
        new MaterialDialog.Builder(this)
                .title("Important")
                .content("You should turn off the Tomato protection before doing that. " +
                        "After putting in the new SIM you can turn on the protection. Please note " +
                        "that if you are using a dual SIM phone, the position of SIM cards do matter.")
                .positiveText("Got it")
                .show();
    }

    public String onOff() {
        //By law of inverse ha ha things will get inverted here :D
        if (myPhone.isProtectionOn()) {
            return "ON";
        } else {
            return "OFF";
        }
    }

}


