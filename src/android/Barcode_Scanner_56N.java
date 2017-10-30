package com.phonegap.plugins.barcode_scanner_56n;

import android.app.Activity;
import android.content.Intent;

import com.zbar.lib.CaptureActivity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Barcode_Scanner_56N extends CordovaPlugin {
    
    public static final String ACTION_SCAN = "scan";

    public static final int REQUEST_CODE = 1000;

    public List<String> actionList = new ArrayList<String>();

    public CallbackContext callbackContext;

    // 用于调用js的方法
    private Barcode_Scanner_56N instance = null;
    private static Activity cordovaActivity;

    private ExecutorService threadPool = Executors.newFixedThreadPool(1);

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        cordovaActivity = cordova.getActivity();
        instance = this;

        actionList.add(ACTION_SCAN);
    }

    @Override
    public boolean execute(final String action, final JSONArray args,
                           final CallbackContext callbackContext) throws JSONException {

        final Intent intent = new Intent(cordova.getActivity(),
                CaptureActivity.class);
        if (!actionList.contains(action)) {
            callbackContext.error("Invalid Action");
            return false;
        }
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ACTION_SCAN.equals(action)){
                       cordova.startActivityForResult(
                            (CordovaPlugin) instance, intent, 0); 
                    }
                    Barcode_Scanner_56N.this.callbackContext = callbackContext;
                   

                } catch (Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                    callbackContext.error(e.getMessage());
                }
            }
        });
        return true;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 0:
                if (intent != null) {
                    String result = intent.getStringExtra("result");
                  
                    JSONObject json=new JSONObject();
                    try {
                        json.put("scanResult", result);
                        callbackContext.success(json);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        cordovaActivity = null;
    }
}