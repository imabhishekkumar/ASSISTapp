package com.theworkingbros.ak.assist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.theworkingbros.ak.assist.R;

public class results extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        webView = findViewById(R.id.resultsWV);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://cloudportal.sathyabama.ac.in/resultsnov2018/login.php");
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {

            Intent main = new Intent(results.this,MainActivity.class);
            startActivity(main);



    }
}
