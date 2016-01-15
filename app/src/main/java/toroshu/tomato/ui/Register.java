package toroshu.tomato.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.andreabaccega.formedittextvalidator.Validator;
import com.andreabaccega.widget.FormEditText;
import com.gc.materialdesign.views.ButtonRectangle;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import toroshu.tomato.R;
import toroshu.tomato.core.Constants;
import toroshu.tomato.core.Phone;

/*
    Second screen for registration
    User is asked for mobile numbers of super hero
*/
public class Register extends AppCompatActivity {

    FormEditText mFirstSuperHero, mSecondSuperHero;
    String firstSuperHero, secondSuperHero;
    ButtonRectangle mProceed;
    CheckBox mCheckBox;

    Phone myPhone;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    private void initView() {
        mContext = getApplicationContext();
        myPhone = new Phone(mContext);

        mFirstSuperHero = (FormEditText) findViewById(R.id.firsthnField);
        mSecondSuperHero = (FormEditText) findViewById(R.id.secondhnField);
        mFirstSuperHero.setTypeface(myPhone.getTypeface());
        mSecondSuperHero.setTypeface(myPhone.getTypeface());

        mCheckBox = (CheckBox) findViewById(R.id.checkbox);


        mFirstSuperHero.addValidator(new Validator("Choose a 10 phone number") {
            @Override
            public boolean isValid(EditText editText) {
                return (firstSuperHero.length() == 10);
            }
        });

        mSecondSuperHero.addValidator(new Validator("Choose another 10 phone number") {
            @Override
            public boolean isValid(EditText editText) {
                return (secondSuperHero.length() == 10);
            }
        });

        mProceed = (ButtonRectangle) findViewById(R.id.proceedButton);
        mProceed.setVisibility(View.INVISIBLE);
        mProceed.setEnabled(false);

        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBox.isChecked()) {
                    mProceed.setVisibility(View.VISIBLE);
                    mProceed.setEnabled(true);
                } else {
                    mProceed.setVisibility(View.INVISIBLE);
                    mProceed.setEnabled(false);
                }
            }
        });

        showHNDetails(mFirstSuperHero);
    }


    public void finish(View v) {

        firstSuperHero = mFirstSuperHero.getText().toString().trim();
        secondSuperHero = mSecondSuperHero.getText().toString().trim();

        if (firstSuperHero.equals(secondSuperHero)) {
            Crouton.makeText(this, "You need to enter two different phone numbers", Style.ALERT).show();
            return;
        }
        if (mFirstSuperHero.testValidity() && mSecondSuperHero.testValidity()) {

            myPhone.registerPhone(firstSuperHero, secondSuperHero);

            // login
            Intent intent = new Intent(Register.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(Constants.DISPLAY_ACCOUNT_CONFIRMATION, true);
            startActivity(intent);
        } else {
            // Try again
            //Toast.makeText(getBaseContext(), "Try Again "
            //      , Toast.LENGTH_SHORT).show();
        }
    }

    public void readTnCDetails(View v) {
        startActivity(new Intent(Register.this
                , TermsAndConditions.class));
    }

    public void showHNDetails(View v) {

        new MaterialDialog.Builder(this)
                .title("Whose phone numbers should I add ?")
                .content("If your phone ever gets stolen, these two numbers will get the alerts " +
                        " and the details of intruder\'s sim via SMS." +
                        " Note: Enter the phone numbers of only those persons, you trust.")
                .positiveText("Okay")
                .show();

    }

}
