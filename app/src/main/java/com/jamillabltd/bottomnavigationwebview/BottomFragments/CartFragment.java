package com.jamillabltd.bottomnavigationwebview.BottomFragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.jamillabltd.bottomnavigationwebview.R;

public class CartFragment extends Fragment {
    String webUrlCart = "https://sites.google.com/view/developer-jamil/cart";
    private ProgressBar progressBar;
    private FrameLayout webView_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize webView - JavaScript Enable - load url
        WebView mWebView = view.findViewById(R.id.webViewId);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(webUrlCart);

        progressBar = view.findViewById(R.id.progress_bar);
        // Find the FrameLayout and set it to mLayout
        webView_container = view.findViewById(R.id.webView_container_id);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Show the progress bar when the page starts loading
                progressBar.setVisibility(View.VISIBLE);
                webView_container.setAlpha(0.5f);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                // Hide the progress bar when the page finishes loading
                progressBar.setVisibility(View.GONE);
                webView_container.setAlpha(1.0f);
            }
        });

    }
}