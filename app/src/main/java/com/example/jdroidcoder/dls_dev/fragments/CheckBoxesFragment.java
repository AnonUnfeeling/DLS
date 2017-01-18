package com.example.jdroidcoder.dls_dev.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.jdroidcoder.dls_dev.R;

/**
 * Created by jdroidcoder on 17.01.17.
 */
public class CheckBoxesFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    //platforms
    private boolean android = false;
    private boolean apple = false;
    private boolean windows = false;
    private boolean site =false;
    //functions
    private boolean paid = false;
    private boolean push = false;
    private boolean location = false;
    private boolean camera =false;
    private boolean pass = false;
    private boolean social = false;
    private boolean controlSystem = false;
    //api
    private boolean noApi = false;
    private boolean fromYou = false;
    private boolean fromMe = false;
    //garant
    private boolean garant = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false);
        //platforms
        ((CheckBox) view.findViewById(R.id.androidCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.appleCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.windowsCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.siteCheckBox)).setOnCheckedChangeListener(this);
        //functions
        ((CheckBox) view.findViewById(R.id.paidContentCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.pushNotificationCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.locationCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.cameraCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.passCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.socialCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.controlSystemCheckBox)).setOnCheckedChangeListener(this);
        //api
        ((CheckBox) view.findViewById(R.id.noWebApiCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.fromYouCheckBox)).setOnCheckedChangeListener(this);
        ((CheckBox) view.findViewById(R.id.fromMeCheckBox)).setOnCheckedChangeListener(this);
        //garant
        ((CheckBox) view.findViewById(R.id.garantCheckBox)).setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.androidCheckBox) {
            android = isChecked;
        } else if (buttonView.getId() == R.id.appleCheckBox) {
            apple = isChecked;
        } else if (buttonView.getId() == R.id.windowsCheckBox) {
            windows = isChecked;
        }else if (buttonView.getId() == R.id.siteCheckBox) {
            site = isChecked;
        }else if (buttonView.getId() == R.id.paidContentCheckBox) {
            paid = isChecked;
        } else if (buttonView.getId() == R.id.pushNotificationCheckBox) {
            push = isChecked;
        } else if (buttonView.getId() == R.id.locationCheckBox) {
            location = isChecked;
        }else if (buttonView.getId() == R.id.cameraCheckBox) {
            camera = isChecked;
        }else if (buttonView.getId() == R.id.passCheckBox) {
            pass = isChecked;
        }else if (buttonView.getId() == R.id.socialCheckBox) {
            social = isChecked;
        }else if (buttonView.getId() == R.id.controlSystemCheckBox) {
            controlSystem = isChecked;
        } else if (buttonView.getId() == R.id.noWebApiCheckBox) {
            noApi = isChecked;
        } else if (buttonView.getId() == R.id.fromMeCheckBox) {
            fromMe = isChecked;
        }else if (buttonView.getId() == R.id.fromYouCheckBox) {
            fromYou = isChecked;
        }else if(buttonView.getId() == R.id.garantCheckBox){
            garant = isChecked;
        }
    }

    public Bundle getCheckBoxes() {
        Bundle bundle = new Bundle();
        //platforms
        bundle.putBoolean("android", android);
        bundle.putBoolean("apple", apple);
        bundle.putBoolean("windows", windows);
        bundle.putBoolean("site", site);
        //functions
        bundle.putBoolean("paid", paid);
        bundle.putBoolean("push", push);
        bundle.putBoolean("location", location);
        bundle.putBoolean("camera", camera);
        bundle.putBoolean("pass", pass);
        bundle.putBoolean("social", social);
        bundle.putBoolean("controlSystem", controlSystem);
        //api
        bundle.putBoolean("noApi", noApi);
        bundle.putBoolean("fromYou", fromYou);
        bundle.putBoolean("fromMe", fromMe);
        //garant
        bundle.putBoolean("garant", garant);
        return bundle;
    }
}
