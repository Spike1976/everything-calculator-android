package com.michaelstokes.everythingcalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import java.util.Locale;

public class MainActivity extends Activity {
    private WebView webView; private TextToSpeech speech;
    @SuppressLint("SetJavaScriptEnabled") @Override public void onCreate(Bundle state){super.onCreate(state);getWindow().setStatusBarColor(Color.rgb(7,17,31));getWindow().setNavigationBarColor(Color.rgb(7,17,31));
        FrameLayout root=new FrameLayout(this);webView=new WebView(this);webView.setBackgroundColor(Color.rgb(7,17,31));root.addView(webView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));setContentView(root);
        if(android.os.Build.VERSION.SDK_INT>=30){getWindow().setDecorFitsSystemWindows(false);root.setOnApplyWindowInsetsListener((view,insets)->{android.graphics.Insets bars=insets.getInsets(WindowInsets.Type.systemBars());FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams)webView.getLayoutParams();lp.topMargin=bars.top;lp.bottomMargin=bars.bottom;lp.leftMargin=0;lp.rightMargin=0;webView.setLayoutParams(lp);return insets;});root.requestApplyInsets();}
        WebSettings s=webView.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);s.setBuiltInZoomControls(false);s.setDisplayZoomControls(false);webView.setWebViewClient(new WebViewClient());speech=new TextToSpeech(this,status->{if(status==TextToSpeech.SUCCESS){speech.setLanguage(Locale.US);speech.setSpeechRate(.9f);}});webView.addJavascriptInterface(new AndroidBridge(),"Android");webView.loadUrl("file:///android_asset/index.html");}
    public class AndroidBridge {@JavascriptInterface public void speak(String text){runOnUiThread(()->speech.speak(text,TextToSpeech.QUEUE_FLUSH,null,"answer"));}@JavascriptInterface public void vibrate(){try{Vibrator v=(Vibrator)getSystemService(VIBRATOR_SERVICE);if(v!=null)v.vibrate(VibrationEffect.createOneShot(18,VibrationEffect.DEFAULT_AMPLITUDE));}catch(Exception ignored){}}}
    @Override public void onBackPressed(){webView.evaluateJavascript("appBack()",null);}
    @Override protected void onDestroy(){if(speech!=null){speech.stop();speech.shutdown();}if(webView!=null)webView.destroy();super.onDestroy();}
}
