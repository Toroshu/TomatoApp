package toroshu.tomato.ui;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import toroshu.tomato.R;

/*
Displays app faq mode = 1 + about us page mode = 0
 */

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (getIntent().getIntExtra("mode", 0) == 0) {
                setContentView(R.layout.activity_about);
            } else {
                setContentView(R.layout.activity_about_faq);
                WebView webView = (WebView) findViewById(R.id.webView);
                webView.loadUrl("file:///android_asset/FAQ.html");
              /*  webView.getSettings().setJavaScriptEnabled(true);

                webView.loadUrl("http://www.ducic.ac.in");
                webView.setWebViewClient(new WebViewClient() {
                    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                        handler.proceed();
                    }
                });*/
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void playStore(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://goo.gl/EIYSAo"));
        startActivity(intent);
    }


}
