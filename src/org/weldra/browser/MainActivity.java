package org.welrda.browser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewChromeClient;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

  private WebView webview;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    requestWindowFeature(Window.FEATURE_PROGRESS);

    webview = new WebView(this);

    setContentView(webview);  

    WebSettings settings = webview.getSettings();

    settings.setAllowFileAccess(false);
    settings.setAppCacheEnabled(true);
    settings.setBuiltInZoomControls(true);
    settings.setDatabaseEnabled(false);
    settings.setDisplayZoomControls(false); 
    settings.setDomStorageEnabled(true);
    settings.setGeolocationEnabled(false);
    settings.setJavaScriptEnabled(true);
    settings.setSavePassword(false);
    settings.setUseWideViewPort(true);

    final Activity activity = this;

    webview.setWebChromeClient(new WebChromeClient() {
      public void onProgressChanged(WebView view, int progress) {
        activity.setProgress(progress * 1000);
      }
    });

    webview.setWebViewClient(new WebViewClient() {
      /*public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
      }*/
    });

    String url = "http://google.com";
    Intent intent = getIntent();
    if (intent != null && Intent.ACTION_VIEW.equals(intent.getAction())) {
      Uri uri = intent.getData();
      url = uri.toString();
    }

    webview.loadUrl(url);           
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_MENU) {
      // generate menu from relevant <menu type="context">
      return true;
    }

    return super.onKeyUp(keyCode, event);
  }
}
