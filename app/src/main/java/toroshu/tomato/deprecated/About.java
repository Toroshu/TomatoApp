package toroshu.tomato.deprecated;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
               /* webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient() {
                    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                        handler.proceed();
                    }
                });
                webView.loadUrl("http://www.google.com");*/

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
