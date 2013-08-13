package org.weldra.browser;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class ChromeClient extends WebChromeClient {

  private MainActivity webactivity;
  
  public ChromeClient(MainActivity wv) {
    super();
    this.webactivity = wv;
  }

  public void onProgressChanged(WebView view, int progress) {
    webactivity.setProgress(progress * 100);
  }
}
