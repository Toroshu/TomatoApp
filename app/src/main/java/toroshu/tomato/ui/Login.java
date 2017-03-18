package toroshu.tomato.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import toroshu.tomato.R;
import toroshu.tomato.core.Phone;

/*
    Plays the role of login + registration screen
*/
public class Login extends AppCompatActivity {

    // @BindView()

    @BindView(R.id.signButton)
    Button mSign;
    @BindView(R.id.registerButton)
    Button mRegister;
    @BindView(R.id.fypButton)
    Button mfyp;

    @BindView(R.id.usernameField)
    EditText mUserName;
    @BindView(R.id.heroNumberField)
    EditText mPassword;

//    @BindView(R.id.welcomeText)
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

        ButterKnife.bind(this);
        mContext = getApplicationContext();
        //for saving data
        myPhone = new Phone(mContext);

        //Edit texts

        mUserName.setTypeface(myPhone.getTypeface());
        mPassword.setTypeface(myPhone.getTypeface());

        mWelcomeMessage.setTypeface(myPhone.getTypeface());


        //Buttons
        mSign = (Button) findViewById(R.id.signButton);
        mRegister = (Button) findViewById(R.id.registerButton);
        mfyp = (Button) findViewById(R.id.fypButton);
    }

    @Override
    protected void onResume() {
        super.onResume();


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
                        startActivity(new Intent(mContext, Status.class));
                        Login.this.finish();
                    } else {
                        //invalid details
                        Snackbar.make(mfyp,
                                "Invalid details. Try Again",
                                Snackbar.LENGTH_SHORT).show();
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




                         if (isInputValid()) {
                             myPhone.setPassword(password);
                             myPhone.setUsername(username);
                             startActivity(new Intent(mContext, Register.class));
                         }
                     }
                 }

                );

    }

    boolean isInputValid() {
        return false;
//        if (username.length() < 4)
//        mUserName.addValidator(new Validator("Entered name too short ") {
//            @Override
//            public boolean isValid(EditText editText) {
//                return ;
//            }
//        });
//
//        mPassword.addValidator(new Validator("Entered password too short ") {
//            @Override
//            public boolean isValid(EditText editText) {
//                return password.length() >= 6;
//            }
//        });
    }


}
