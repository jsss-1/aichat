package com.jsss.utils;

public interface Constant {

    /**
     * 主题: 体检预约
     */
    String TOPIC_APPOINTMENT = "appointment";


    /**
     * 主题: 回复体检预约
     */
    String TOPIC_REPLY_APPOINTMENT = "reply_appointment";

    /**
     * 主题:体检日历
     */
    String TOPIC_CALENDAR = "calendar";

    /**
     * 主题: 体检报告
     */
    String TOPIC_REPORT = "report";

    /**
     * 主题: 诊断意见
     */
    String TOPIC_OPINION = "opinion";

    /**
     * 主题: 咨询信息
     */
    String TOPIC_INFORMATION = "information";

    /**
     * 主题: 回复咨询信息
     */
    String TOPIC_REPLY_INFORMATION = "reply_information";

    /**
     * redis键：用户的首页日历（非医生）
     */
    String USER_CALENDAREVENTS = "calendarEvents:";


    /**
     * redis键：医生的首页日历
     */
    String DOCTOR_CALENDAREVENTS = "doctor:calendarEvents";


}
