package toroshu.tomato.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import toroshu.tomato.R;

/*
Shows licenses of the 3rd party modules used in the project
*/

public class Credits extends AppCompatActivity {

    @BindView(R.id.wv_credits)
    WebView creditsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        ButterKnife.bind(this);

        String[] n = {"Android View Animations", "Crouton",
                "Material Design Library", "Android edittext validator",
                "Material Dialogs", "Material Drawer",
                "License Dialog",
        };



    }


}