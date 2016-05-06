package com.liu.liu;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;


public class MainActivity extends Activity {

    private WebView mWebView;
    private ImageView imageView;

    private final int REQUEST_CODE_CHOOSE_FILE = 1111;

    private ValueCallback<Uri> mValueCallBack;
    public static boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setmWebView();

        Intent intent = new Intent(this,MyService.class);
        startService(intent);

        Intent intent2 = new Intent("com.liu.liu.aoo");
        sendBroadcast(intent2);
    }

    private void setmWebView(){
        mWebView = (WebView) this.findViewById(R.id.webview);
        TextView textView = (TextView) this.findViewById(R.id.tv);
        imageView = (ImageView) this.findViewById(R.id.img);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:show()");
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("info", "----->" + view.getTitle());
            }

        });

        mWebView.setWebChromeClient(new TestChromeClient1(new WebChromeClient()) {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                MainActivity.this.setProgress(newProgress * 1000);
            }

            @Override
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType) {
                Log.i("info1", acceptType + ",");
                super.openFileChooser(uploadFile);
            }

            @Override
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String capture) {
                Log.i("info2", acceptType + "," + capture);
                openFileChooser(uploadFile);
            }

            @Override
            public void openFileChooser(ValueCallback<Uri> uploadFile) {
                mValueCallBack = uploadFile;
                MainActivity.this.startActivityForResult(Intent.createChooser(createCameraIntent(), "Image Browser"), REQUEST_CODE_CHOOSE_FILE);
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        setJavaScript();
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    //------------------------------------------------------------------------------------------------------


    private Intent createCameraIntent() {
       // Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//拍照
        //=======================================================
        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);//选择图片文件
        imageIntent.setType("image/*");
        //=======================================================
        return imageIntent;
    }

    private void setJavaScript() {
        JsInterface jsInterface = new JsInterface();
        Foo foo = new Foo();
        jsInterface.setFoo(foo);
        mWebView.addJavascriptInterface(jsInterface, "jsInterface");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_FILE && resultCode == Activity.RESULT_OK) {
            if (mValueCallBack != null && data != null) {
                Uri result = (data == null || resultCode != Activity.RESULT_OK) ? null : data.getData();
                if (result != null && !TextUtils.isEmpty(result.getAuthority())) {
                    Cursor cursor = getContentResolver().query(result, null, null, null, null);
                    if (null == cursor) {
                        Toast.makeText(this, "图片没找到", Toast.LENGTH_SHORT).show();
                        mValueCallBack = null;
                        return;
                    }
                    try {
                        cursor.moveToFirst();
                        int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                        if (index > -1) {
                            String path = cursor.getString(index);
                            if (!TextUtils.isEmpty(path) && new File(path).exists()) {
                                result = Uri.fromFile(new File(path));
                            }
                        }
                    } finally {
                        cursor.close();
                    }

                }
                Log.i("info", "result:" + result);
                mValueCallBack.onReceiveValue(result);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
