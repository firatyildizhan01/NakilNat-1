package com.nakilnat.nakilnat.ui.profile.offers;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;
import butterknife.BindView;

public class OffersLayoutFragment extends BaseFragment {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @BindView(R.id.main_container)
    FrameLayout mainContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_layout);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Teklifler");
        InitSubContents();
    }

    private void InitSubContents() {
        mainContainer= (FrameLayout) findViewById(R.id.main_container);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        fragment = new new_offers_fragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
    }
}