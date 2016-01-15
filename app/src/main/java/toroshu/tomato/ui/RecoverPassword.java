package toroshu.tomato.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import toroshu.tomato.R;
import toroshu.tomato.core.Phone;

/*
    Displays a recover password screen
*/

public class RecoverPassword extends Activity {

    FormEditText mguessNumberField;
    Phone myPhone;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        mContext = getApplicationContext();
        myPhone = new Phone(mContext);
        mguessNumberField = (FormEditText) findViewById(R.id.heroNumberField);
        mguessNumberField.setTypeface(myPhone.getTypeface());
        mguessNumberField.setHint(getResources().getString(R.string.msg_guess_number) + " " +
                myPhone.getFSH().substring(0, 6)
                + "****");

    }

    public void proceed(View v) {


        if (mguessNumberField.testValidity() &&
                myPhone.getFSH().equals(mguessNumberField.getText().toString())) {

            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(
                    myPhone.getFSH(),
                    null,
                    "Your password is: " + myPhone.getPassword() +
                            " . Sent via Tomato Safety App.", null, null);
            Toast.makeText(RecoverPassword.this,
                    "Password Sent", Toast.LENGTH_SHORT).show();
            this.finish();

        } else {
            Crouton.makeText(RecoverPassword.this,
                    "Invalid Number. Try Again", Style.ALERT).show();

        }
    }


}
