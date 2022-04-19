package com.nakilnat.nakilnat.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nakilnat.nakilnat.R;

public class WebViewFragment extends BaseFragment {
    WebView webView;
    String url, title = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_web_view);
        bottomBarSetup(R.id.bottomProfile);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                url = null;
                title = null;
            } else {
                url = extras.getString("url");
                title = extras.getString("title");
            }
        } else {
            url = (String) savedInstanceState.getSerializable("url");
            title = (String) savedInstanceState.getSerializable("title");
        }
        initTopBarContents(title);
        navigateToWebUrl(url);
    }

    public void navigateToWebUrl(String url) {
        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        webView.loadUrl(url);
    }

}
