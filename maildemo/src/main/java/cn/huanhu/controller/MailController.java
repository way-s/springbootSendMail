package cn.huanhu.controller;

import cn.huanhu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author m
 * @className MailController
 * @description MailController
 */
@Controller
public class MailController {

    @Value("${mail.fromMail.addr}")
    private String form;

    @Autowired
    private MailService mailService;

    @GetMapping("/sendDemo")
    public @ResponseBody String sendMailDemo(){
        String contents="sendMailDemo,简单测试的邮件发送！";
        mailService.sendMail("demo","sendMailDemo,简单测试的邮件发送！","6062412716@qq.com");
        return "已发送！";
    }

    @GetMapping("/sendImage")
    public @ResponseBody String sendImageMail(){
        return mailService.sendImageMail("四季恋人");
    }
}
