package com.pplingo.la_push.manager;

import android.content.Context;

import com.pplingo.la_push.callback.PushSeqCallBack;

import java.util.Map;
import java.util.Set;

/**
 * Created by ZhqoQi on 2021/2/24 12:05
 * E-Mail Address：550655294@qq.com
 */



public interface CommonPushManager {


    /**
     * 初始化需要的context
     * @param conn
     */
    void init(Context conn);
    /**
     * 初始化极光推送
     * @param conn
     * @param isDebug 是否是debug模式
     */
    void setUp(Context conn, boolean isDebug,String channel);

    /**
     * 获取注册Id
     * @param conn
     * @return
     */
    String getRegistrationID(Context conn);

    /**
     * 设置别名
     * @param conn
     * @param alias
     */
    void setAlias(Context conn, String alias, PushSeqCallBack seqCallBack);

    /**
     * 获取别名 需要通过监听获取到别名
     * @param conn
     */
    void getAlias(Context conn, PushSeqCallBack seqCallBack);

    /**
     * 删除别名
     * @param conn
     */
    void deleteAlias(Context conn, PushSeqCallBack seqCallBack);

    /**
     * 设置标签
     * @param conn
     * @param Tags
     */
    void setTags(Context conn, Set<String> Tags, PushSeqCallBack seqCallBack);

    /**
     * 添加标签
     * @param conn
     * @param Tags
     */
    void addTags(Context conn, Set<String> Tags, PushSeqCallBack seqCallBack);

    /**
     * 获取全部标签
     * @param conn
     */
    void getAllTags(Context conn, PushSeqCallBack seqCallBack);

    /**
     * 清除tag
     * @param conn
     */
    void cleanTags(Context conn, PushSeqCallBack seqCallBack);


    /**
     * 查询标签
     * @param conn
     */
    void checkTag(Context conn, Set<String> Tags, PushSeqCallBack seqCallBack);

    /**
     * 停止推送
     */
    void stopPush();

    /**
     * 恢复推送
     */
    void resumePush();

    /**
     * 清除全部极光推送通知
     */
    void clearAllNotification();

    /**
     * 清除对应id对通知
     * @param id
     */
    void clearNotificationById(int id);

    /**
     * 发送本地推送
     * @param map
     */
    void sendLocalNotification(Map<String, Object> map);

    /**
     * 设置app角标（红数字）
     * @param num
     */
    void setBadge(int num);

    /**
     * 判断当前通知权限是否开启
     * @return
     */
    boolean isNotificationEnabled();

    /**
     * 打开设置推送权限界面
     */
    void openSettingsForNotification();



}
