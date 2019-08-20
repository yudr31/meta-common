package com.spring.boot.common.constant;

/**
 * @author yuderen
 * @version 2019/8/15 13:49
 */
public class MqConstant {

    public static final String NOTIFY_EXCHANGE = "notify_exchange";

    public static final String PHONE_MESSAGE_NOTIFY_QUEUE = "phone_message_queue";
    public static final String PHONE_CALL_NOTIFY_QUEUE = "phone_call_queue";
    public static final String DINGDING_MESSAGE_NOTIFY_QUEUE = "dingding_message_queue";
    public static final String WECHAT_MESSAGE_NOTIFY_QUEUE = "wechat_message_queue";

    public static final String PHONE_MESSAGE_NOTIFY_KEY = "#.phone_message.#";
    public static final String PHONE_CALL_NOTIFY_KEY = "#.phone_call.#";
    public static final String DINGDING_MESSAGE_NOTIFY_KEY = "#.dingding_message.#";
    public static final String WECHAT_MESSAGE_NOTIFY_KEY = "#.wechat_message.#";

}
