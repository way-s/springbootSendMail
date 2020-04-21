package cn.huanhu.service;

/**
 * @author m
 * @className MailService
 * @description MailService
 */
public interface MailService {
    /**
     * 发送邮件demo
     * @param theme 主题
     * @param content 内容
     * @param recipient 接收者
     */
    void sendMail(String theme,String content,String recipient);


    /**
     * 发送图片邮件
     * @param theme 主题
     * @return String
     */
    String sendImageMail(String theme);



}
