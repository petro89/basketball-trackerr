package com.petro89.glasslock;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
    private WebView webView;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("glasslock", MODE_PRIVATE);
        getWindow().setStatusBarColor(Color.rgb(12, 18, 42));
        getWindow().setNavigationBarColor(Color.rgb(12, 18, 42));
        getWindow().getDecorView().setSystemUiVisibility(0);

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(12, 18, 42));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new SettingsBridge(), "GlassBridge");
        setContentView(webView);
        webView.loadUrl("file:///android_asset/index.html");
    }

    private class SettingsBridge {
        @JavascriptInterface
        public String getTheme() {
            return prefs.getString("theme", "aurora");
        }

        @JavascriptInterface
        public void setTheme(String theme) {
            if ("aurora".equals(theme) || "midnight".equals(theme) || "rose".equals(theme)) {
                prefs.edit().putString("theme", theme).apply();
            }
        }

        @JavascriptInterface
        public boolean getMusicVisible() {
            return prefs.getBoolean("music", true);
        }

        @JavascriptInterface
        public void setMusicVisible(boolean visible) {
            prefs.edit().putBoolean("music", visible).apply();
        }

        @JavascriptInterface
        public boolean getWeatherVisible() {
            return prefs.getBoolean("weather", true);
        }

        @JavascriptInterface
        public void setWeatherVisible(boolean visible) {
            prefs.edit().putBoolean("weather", visible).apply();
        }

        @JavascriptInterface
        public boolean get24Hour() {
            return prefs.getBoolean("clock24", true);
        }

        @JavascriptInterface
        public void set24Hour(boolean enabled) {
            prefs.edit().putBoolean("clock24", enabled).apply();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
