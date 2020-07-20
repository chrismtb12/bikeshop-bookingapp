package com.example.thefrothybikecobookingsys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class webSites extends AppCompatActivity {


    public Button button100Percent, buttonBurgtec, buttonFiveTen, buttonHope, buttonMadison, buttonMondraker, buttonRaceface,
            buttonSantaCruz, buttonSchwalbe, buttonSaddleback, buttonSilverfish, buttonRRP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_sites);

        button100Percent = (Button) findViewById(R.id.button100Percent);
        buttonBurgtec = (Button) findViewById(R.id.buttonBurgtec);
        buttonFiveTen = (Button) findViewById(R.id.buttonFiveTen);
        buttonHope = (Button) findViewById(R.id.buttonHope);
        buttonMadison = (Button) findViewById(R.id.buttonMadison);
        buttonMondraker = (Button) findViewById(R.id.buttonMondraker);
        buttonRaceface = (Button) findViewById(R.id.buttonRaceface);
        buttonSantaCruz = (Button) findViewById(R.id.buttonSantaCruz);
        buttonSchwalbe = (Button) findViewById(R.id.buttonSchwalbe);
        buttonSaddleback = (Button) findViewById(R.id.buttonSaddleback);
        buttonSilverfish = (Button) findViewById(R.id.buttonSilverfish);
        buttonRRP = (Button) findViewById(R.id.buttonRRP);


        button100Percent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webView100Percent);

                Intent percent = new Intent(webSites.this, webView100Percent.class);
                startActivity(percent);
            }
        });


        buttonBurgtec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewBurgtec);

                Intent burgtec = new Intent(webSites.this, webViewBurgtec.class);
                startActivity(burgtec);
            }
        });

        buttonFiveTen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewFiveTen);

                Intent fiveten = new Intent(webSites.this, webViewFiveTen.class);
                startActivity(fiveten);
            }
        });

        buttonHope.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewHope);

                Intent hope = new Intent(webSites.this, webViewHope.class);
                startActivity(hope);
            }
        });

        buttonMadison.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewMadison);

                Intent madison = new Intent(webSites.this, webViewMadison.class);
                startActivity(madison);
            }
        });

        buttonMondraker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewMondraker);

                Intent mondraker = new Intent(webSites.this, webViewMondraker.class);
                startActivity(mondraker);
            }
        });

        buttonRaceface.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewRaceface);

                Intent raceface = new Intent(webSites.this, webViewRaceface.class);
                startActivity(raceface);
            }
        });

        buttonSantaCruz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewSantaCruz);

                Intent santacruz = new Intent(webSites.this, webViewSantaCruz.class);
                startActivity(santacruz);
            }
        });

        buttonSchwalbe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewSchwalbe);

                Intent schwalbe = new Intent(webSites.this, webViewSchwalbe.class);
                startActivity(schwalbe);
            }
        });

        buttonSaddleback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewSaddleback);

                Intent saddleback = new Intent(webSites.this, webViewSaddleback.class);
                startActivity(saddleback);
            }
        });

        buttonSilverfish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewSilverfish);

                Intent silverfish = new Intent(webSites.this, webViewSilverfish.class);
                startActivity(silverfish);
            }
        });

        buttonRRP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                WebView webView = (WebView) findViewById(R.id.webViewRRP);

                Intent rrp = new Intent(webSites.this, webViewRRP.class);
                startActivity(rrp);
            }
        });


    }
}
