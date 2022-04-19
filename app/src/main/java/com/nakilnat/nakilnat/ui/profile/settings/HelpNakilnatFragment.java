package com.nakilnat.nakilnat.ui.profile.settings;

import android.os.Bundle;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;

public class HelpNakilnatFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_help_nakilnat);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Yardımda Nakilnat");
    }
}
