package com.example.thefrothybikecobookingsys;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientImpl extends WebViewClient {

    private Activity activity = null;

    public WebViewClientImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if(url.contains("100percent.com/pages/bike")) return false;
        if(url.contains("burgtec.co.uk/")) return false;
        if(url.contains("adidasoutdoor.com/sports-all_mountain")) return false;
        if(url.contains("hopetech.com/")) return false;
        if(url.contains("madison.co.uk/brands")) return false;
        if(url.contains("mondraker.com/es/en")) return false;
        if(url.contains("raceface.com/#")) return false;
        if(url.contains("santacruzbicycles.com/en-GB")) return false;
        if(url.contains("schwalbe.com/en/mtb")) return false;
        if(url.contains("saddleback.co.uk")) return false;
        if(url.contains("silverfish-uk.com")) return false;
        if(url.contains("rapidracerproducts.com/")) return false;


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }
}
