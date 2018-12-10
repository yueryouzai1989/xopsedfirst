package com.xsd.xopsed;

import android.util.Log;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookToast implements IXposedHookLoadPackage {

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
       // XposedBridge.log("不存在");
        if (loadPackageParam.packageName.equals("com.xsd.xopsed")) {
            XposedBridge.log("com.xsd.xopsed存在");
            Class clazz = loadPackageParam.classLoader.loadClass
                    ("com.xsd.xopsed.MainActivity");

            XposedHelpers.findAndHookMethod(clazz, "toastMessage", new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("你已被劫持");
                }
            });


        }
    }
}