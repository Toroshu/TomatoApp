package toroshu.tomato.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.andreabaccega.formedittextvalidator.Validator;
import com.andreabaccega.widget.FormEditText;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import toroshu.tomato.R;
import toroshu.tomato.core.Phone;

/*
    Plays the role of login + registration screen
*/
public class Login extends AppCompatActivity {

    ButtonRectangle mSign;
    ButtonRectangle mRegister;
    ButtonFlat mfyp;
    FormEditText mUserName, mPassword;
    TextView mWelcomeMessage;

    String username, password;

    Context mContext;
    Phone myPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        //Edit texts
        mUserName = (FormEditText) findViewById(R.id.usernameField);
        mPassword = (FormEditText) findViewById(R.id.heroNumberField);

        mWelcomeMessage = (TextView) findViewById(R.id.welcomeText);
        mWelcomeMessage.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf"));
        mContext = getApplicationContext();

        //Buttons
        mSign = (ButtonRectangle) findViewById(R.id.signButton);
        mRegister = (ButtonRectangle) findViewById(R.id.registerButton);
        mfyp = (ButtonFlat) findViewById(R.id.fypButton);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //for saving data
        myPhone = new Phone(mContext);

        if (!myPhone.isRegistered()) {
            //sign up
            mSign.setVisibility(View.GONE);
            mfyp.setVisibility(View.GONE);
            register();
        } else {
            //sign in
            mWelcomeMessage.setVisibility(View.VISIBLE);
            mWelcomeMessage.setText("Welcome, " + myPhone.getUsername());
            mUserName.setVisibility(View.GONE);
            mRegister.setVisibility(View.GONE);
            mPassword.requestFocus();
            mSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (authenticate()) {
                        startActivity(new Intent(mContext, StatusActivity.class));
                        Login.this.finish();
                    } else {
                        //invalid details
                        YoYo.with(Techniques.Wobble).duration(1000).playOn(mPassword);
                        Crouton.makeText(Login.this,
                                "Invalid details. Try Again",
                                Style.ALERT).show();
                    }
                }
            });

        }

    }

    private boolean authenticate() {
        // returns true if user entered correct password
        password = mPassword.getText().toString().trim();
        return (password.equals(myPhone.getPassword()));
    }


    public void recoverPassword(View v) {
        //redirects to recover password screen
        if (myPhone.isRegistered()) {
            startActivity(new Intent(mContext, RecoverPassword.class));
        }
    }

    public void register() {

        mRegister.setOnClickListener
                (new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         username = mUserName.getText().toString().trim();
                         password = mPassword.getText().toString().trim();

                         mUserName.addValidator(new Validator("Entered name too short ") {
                             @Override
                             public boolean isValid(EditText editText) {
                                 return username.length() >= 4;
                             }
                         });

                         mPassword.addValidator(new Validator("Entered password too short ") {
                             @Override
                             public boolean isValid(EditText editText) {
                                 return password.length() >= 6;
                             }
                         });


                         if (mUserName.testValidity() && mPassword.testValidity()) {
                             myPhone.setPassword(password);
                             myPhone.setUsername(username);
                             startActivity(new Intent(mContext, Register.class));
                         }
                     }
                 }

                );

    }


}
