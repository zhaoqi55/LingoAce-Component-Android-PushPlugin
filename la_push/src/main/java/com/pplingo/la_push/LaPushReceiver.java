package com.pplingo.la_push;

/**
 * Created by ZhqoQi on 2021/2/26 14:31
 * E-Mail Address：550655294@qq.com
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * 接收自定义消息,通知,通知点击事件等事件的广播
 * 文档链接:http://docs.jiguang.cn/client/android_api/
 */
public  class LaPushReceiver extends BroadcastReceiver {

    private static final List<String> IGNORED_EXTRAS_KEYS = Arrays.asList("cn.jpush.android.TITLE",
            "cn.jpush.android.MESSAGE", "cn.jpush.android.APPKEY", "cn.jpush.android.NOTIFICATION_CONTENT_TITLE", "key_show_entity", "platform");

    public LaPushReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(JPushInterface.ACTION_REGISTRATION_ID)) {
            String rId = intent.getStringExtra(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d("JPushPlugin", "on get registration");
            LaPushManager.getInstance().callBack.transmitReceiveRegistrationId(rId);

        } else if (action.equals(JPushInterface.ACTION_MESSAGE_RECEIVED)) {
            handlingMessageReceive(intent);
        } else if (action.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
            handlingNotificationReceive(context, intent);
        } else if (action.equals(JPushInterface.ACTION_NOTIFICATION_OPENED)) {
            handlingNotificationOpen(context, intent);
        }
    }

    private void handlingMessageReceive(Intent intent) {
        Log.d("JPushReceiver", "handlingMessageReceive " + intent.getAction());

        String msg = intent.getStringExtra(JPushInterface.EXTRA_MESSAGE);
        Map<String, Object> extras = getNotificationExtras(intent);
        LaPushManager.getInstance().callBack.transmitMessageReceive(msg, extras);
    }

    private void handlingNotificationOpen(Context context, Intent intent) {
        Log.d("JPushReceiver", "handlingNotificationOpen " + intent.getAction());

        String title = intent.getStringExtra(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String alert = intent.getStringExtra(JPushInterface.EXTRA_ALERT);
        Map<String, Object> extras = getNotificationExtras(intent);
        LaPushManager.getInstance().callBack.transmitNotificationOpen(title, alert, extras);

        Intent launch = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launch != null) {
            launch.addCategory(Intent.CATEGORY_LAUNCHER);
            launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(launch);
        }
    }

    private void handlingNotificationReceive(Context context, Intent intent) {
        Log.d("JPushReceiver", "handlingNotificationReceive " + intent.getAction());

        String title = intent.getStringExtra(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String alert = intent.getStringExtra(JPushInterface.EXTRA_ALERT);
        Map<String, Object> extras = getNotificationExtras(intent);
        LaPushManager.getInstance().callBack.transmitNotificationReceive(title, alert, extras);
    }

    private Map<String, Object> getNotificationExtras(Intent intent) {
        Log.d("JPushReceiver", "");

        Map<String, Object> extrasMap = new HashMap<String, Object>();
        for (String key : intent.getExtras().keySet()) {
            if (!IGNORED_EXTRAS_KEYS.contains(key)) {
                if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                    extrasMap.put(key, intent.getIntExtra(key, 0));
                } else {
                    extrasMap.put(key, intent.getStringExtra(key));
                }
            }
        }
        return extrasMap;
    }
}
