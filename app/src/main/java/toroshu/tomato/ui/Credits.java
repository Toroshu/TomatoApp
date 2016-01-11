package toroshu.tomato.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import toroshu.tomato.R;

/*
Shows licenses of the 3rd party modules used in the project
*/

public class Credits extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        String[] n = {"Android View Animations", "Crouton",
                "Material Design Library", "Android edittext validator",
                "Material Dialogs", "Material Drawer",
                "License Dialog",
        };
        setListAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                n));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        final String name;
        final String url;
        final String copyright;
        final License license;

        switch (position) {
            case 0:
                name = "daimajia";
                url = "https://github.com/daimajia/AndroidViewAnimations";
                copyright = "Copyright (c) 2014 daimajia";
                license = new MITLicense();
                break;
            case 1:
                name = "keyboardsurfer";
                url = "https://github.com/keyboardsurfer/Crouton";
                copyright = "The initial version was written by Benjamin Weiss. The name and the idea of Crouton originates in a blog article by Cyril Mottier.\n" +
                        "\n" +
                        "The Crouton logo has been created by Marie Schweiz";
                license = new ApacheSoftwareLicense20();
                break;
            case 2:
                name = "navasmdc";
                url = "https://github.com/navasmdc/MaterialDesignLibrary";
                copyright = "Copyright (c) 2014 navasmdc";
                license = new ApacheSoftwareLicense20();
                break;
            case 3:
                name = "Andrea Baccega";
                url = "https://github.com/vekexasia/android-edittext-validator";
                copyright = "Copyright (c) 2012 Andrea Baccega <me@andreabaccega.com>";
                license = new MITLicense();
                break;
            case 4:
                name = "Aidan Michael Follestad";
                url = "https://github.com/afollestad/material-dialogs";
                copyright = "Copyright (c) 2015 Aidan Michael Follestad\n";
                license = new MITLicense();
                break;
            case 5:
                name = "Mike Penz";
                url = "https://github.com/mikepenz/MaterialDrawer";
                copyright = "Copyright 2015 Mike Penz";
                license = new ApacheSoftwareLicense20();
                break;
            case 6:
                name = "Philip Schiffer";
                url = "https://github.com/PSDev/LicensesDialog";
                copyright = "Copyright 2013 Philip Schiffer";
                license = new ApacheSoftwareLicense20();
                break;
            case 7:
                name = "Mike Penz";
                url = "https://github.com/mikepenz/MaterialDrawer";
                copyright = "Copyright 2015 Mike Penz";
                license = new ApacheSoftwareLicense20();
                break;
            default:
                name = "Mike Penz";
                url = "https://github.com/mikepenz/MaterialDrawer";
                copyright = "Copyright 2015 Mike Penz";
                license = new ApacheSoftwareLicense20();
                break;

        }

        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();

    }
}