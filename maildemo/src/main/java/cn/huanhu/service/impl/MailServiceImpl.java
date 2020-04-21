package cn.huanhu.service.impl;

import cn.huanhu.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @author m
 * @className MailServiceImpl
 * @description MailServiceImpl
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String form;

    @Override
    public void sendMail(String theme, String content, String recipient) {
        System.out.println("接受者：" + recipient + "\t" + "主题：" + theme + "\t" + "内容：" + content);
        SimpleMailMessage message = new SimpleMailMessage();
        Date date = new Date();
        //发件人
        message.setFrom(form);
        //收件人
        message.setTo(recipient);
        //主题
        message.setSubject(theme);
        //内容
        message.setText(content);
        //设置时间
        message.setSentDate(date);
        System.out.println(message);
        try {
            mailSender.send(message);
            System.out.println("发送成功！");
        } catch (Exception e) {
            System.out.println("发送失败！");
            e.printStackTrace();
        }
    }


    @Override
    public String sendImageMail(String theme)  {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        String filePath = "xxxx\\Desktop\\14325.jpg";
        //图片id码
        String contentId = UUID.randomUUID().toString().replace("-","");
        String contents="<html><body> 有些失去是注定的，有些缘分是永远不会有结果的，爱一个不一定会拥有，拥有一个人就一定要好好去爱他!<br/>" +
                "<img src='cid:"+contentId+"' width=\"300px\" height=\"200px\"/></body></html>";
        Date date = new Date();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
            helper.setFrom(form);
            //收件人
            helper.setTo("xxxx@qq.com");
            //主题
            helper.setSubject(theme);
            //发送html
            helper.setText(contents,true);
            //创建时间
            helper.setSentDate(date);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            //添加文件
            helper.addInline(contentId, file);
            mailSender.send(mailMessage);
            return "发送成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败！";
        }
    }
}
