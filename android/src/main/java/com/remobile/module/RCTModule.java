package com.remobile.module;

import android.content.Intent;
import android.os.Handler;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

public class RCTModule extends ReactContextBaseJavaModule {
    public RCTModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    private Callback callback;

    @Override
    public String getName() {
        return "RCTModule";
    }

    @ReactMethod
    public void load(String url, String moduleName, ReadableMap props, Callback callback) {
        this.callback = callback;
        ReactApplicationContext context = getReactApplicationContext();
        Intent intent = new Intent(context, ModuleActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("moduleName", moduleName);
        intent.putExtra("props", Arguments.toBundle(props));
        ModuleManager.setModule(this);
        getCurrentActivity().startActivity(intent);
    }

    @ReactMethod
    public void unload(final ReadableMap props) {
        ModuleActivity activity = ModuleManager.getActivity();
        if (activity != null) {
            activity.finish();
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                WritableMap newProps = Arguments.createMap();
                newProps.merge(props);
                ModuleManager.getModule().callback.invoke(newProps);
            }
        }, 500);
    }
}
