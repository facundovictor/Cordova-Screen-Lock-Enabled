package com.abarak64.screenlock;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.*;
import android.provider.*;
import android.app.*;

public class ScreenLock extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        Context context=this.cordova.getActivity().getApplicationContext(); 
        boolean hasSecurity = doesDeviceHaveSecuritySetup(context);
        callbackContext.success(hasSecurity ? 1 : 0);

        return true;
    }

    private static boolean doesDeviceHaveSecuritySetup(Context context)
    {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE); //api 16+
        // https://developer.android.com/reference/android/app/KeyguardManager.html#isKeyguardSecure()
        // Return whether the keyguard is secured by a PIN, pattern or password or a SIM card is currently locked.
        return keyguardManager.isKeyguardSecure();
    }
}
