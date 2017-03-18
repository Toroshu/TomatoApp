package toroshu.tomato.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import toroshu.tomato.R;
import toroshu.tomato.core.Phone;

/*
    Displays a recover password screen
*/

public class RecoverPassword extends Activity {

    @BindView(R.id.heroNumberField)
    EditText heroField;

    Phone myPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        ButterKnife.bind(this);

        myPhone = new Phone(this);

        heroField.setTypeface(myPhone.getTypeface());
        heroField.setHint(getResources().getString(R.string.msg_guess_number) + " " +
                myPhone.getFSH().substring(0, 3)
                + "*******");

    }

    public void proceed(View v) {
//heroField.testValidity() &&

        if (
                myPhone.getFSH().equals(heroField.getText().toString())) {

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
            Snackbar.make(heroField,
                    "Invalid Number. Try Again", Snackbar.LENGTH_SHORT).show();

        }
    }


}
