package inshorts.com.inshortsapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import inshorts.com.inshortsapp.R;
import inshorts.com.inshortsapp.model.NewsArticle;

/**
 * Created by Shailendra on 9/17/2017.
 */

public class NewsArticleDetails extends AppCompatActivity {

    public static final String ARG_NEWS_ARTICLE = "arg_news_article";

    private static final String TAG = NewsArticleDetails.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article_details);
        final WebView webView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        if (null != intent) {
            final NewsArticle newsArticle = intent.getParcelableExtra(ARG_NEWS_ARTICLE);
            String mArticleURL = newsArticle.getURL();
            loadWebPage(webView, mArticleURL);
        }
    }

    protected void loadWebPage(final WebView webView, final String webURL) {
        if (null == webView || null == webURL || webURL.isEmpty()) {
            return;
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().equals(webURL)) {
                    // This is your web site, so do not override; let the WebView to load the page
                    return false;
                }
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                Log.i(TAG, "onReceivedSslError(),error:" + error);
            }
        });

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int progress) {
                //Handle Loading progres Data..
                Log.i(TAG, "onProgressChanged(),progress:" + progress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.i(TAG, "onProgressChanged(),title:" + title);
            }
        });

        webView.loadUrl(webURL);
    }
}
