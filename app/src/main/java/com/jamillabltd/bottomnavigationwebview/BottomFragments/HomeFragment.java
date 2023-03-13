package com.jamillabltd.bottomnavigationwebview.BottomFragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.jamillabltd.bottomnavigationwebview.R;

public class HomeFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    public static WebView mWebView; //make it public for access globally
    String webUrlHome = "https://sites.google.com/view/developer-jamil/home";
    private ProgressBar progressBar;
    private FrameLayout webView_container;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize webView - JavaScript Enable - load url
        mWebView = view.findViewById(R.id.webViewIdHome);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(webUrlHome);

        progressBar = view.findViewById(R.id.progress_bar);
        // Find the FrameLayout and set it to mLayout
        webView_container = view.findViewById(R.id.webView_container_id);

        // Find the SwipeRefreshLayout and set its OnRefreshListener
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Reload the web page when the user swipes down to refresh
            mWebView.reload();
            swipeRefreshLayout.setRefreshing(false);
        });

        //page loading start and end action
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

        // Add an OnScrollChangeListener to the WebView to disable the SwipeRefreshLayout
        mWebView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == 0 && !mWebView.canScrollVertically(-1)) {
                swipeRefreshLayout.setEnabled(true);
            } else if (scrollY == (v.getHeight() - v.getScrollY()) && !mWebView.canScrollVertically(1)) {
                swipeRefreshLayout.setEnabled(false);
            } else {
                swipeRefreshLayout.setEnabled(false);
            }
        });


    }

}