package com.pplingo.la_push.callback;

import com.pplingo.la_push.bean.LaPushMessageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhqoQi on 2021/2/24 17:08
 * E-Mail Address：550655294@qq.com
 */

public interface CommPushCallBack {


    /**
     * 标签检查结果
     * @param commonBean
     */
    void onTagCheckResult(LaPushMessageBean commonBean);

    /**
     * 标签操作结果
     * @param commonBean
     */
    void onTagOperateResult(LaPushMessageBean commonBean);

    /**
     * 别名操作结果
     * @param commonBean
     */
    void onAliasOperatorResult(LaPushMessageBean commonBean);


    void transmitReceiveRegistrationId(String rId);

    void transmitMessageReceive(String title, Map<String, Object> extras);

    void transmitNotificationOpen(String title, String alert, Map<String, Object> extras);

    void transmitNotificationReceive(String title, String alert, Map<String, Object> extras);


    void onReceiveNotificationAuthorization(HashMap<String, Object> map);

}
