package com.github.franciscan.remotetile.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.github.franciscan.remotetile.R;
import com.github.franciscan.remotetile.utils.Constant;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText evIpAddress = (EditText) findViewById(R.id.ev_ipaddress);
        EditText evPort = (EditText) findViewById(R.id.ev_port);

        evIpAddress.setText(Prefs.getString(Constant.IP_ADDRESS, ""));
        evPort.setText(String.valueOf(Prefs.getInt(Constant.PORT, 4545)));

        link(evIpAddress, Constant.IP_ADDRESS);
        link(evPort, Constant.PORT);

    }


    private void link(EditText ev, final String key) {
        ev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (key.equals(Constant.PORT) && charSequence.length() > 0) {
                    Prefs.putInt(Constant.PORT, Integer.valueOf(charSequence.toString()));
                    return;
                }
                Prefs.putString(key, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


}
