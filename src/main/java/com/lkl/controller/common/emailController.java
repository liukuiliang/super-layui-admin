package com.lkl.controller.common;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author 刘奎亮
 * @date 2020/11/4
 */
@RestController
@RequestMapping(value = "/email")
public class emailController {

    @RequestMapping(value = "/email",method = {RequestMethod.POST,RequestMethod.GET})
    public String emailMethod() throws GeneralSecurityException, MessagingException {
        // 创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.auth","true");

        // 设置SSL密钥（qq特性）
        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
        mailSSLSocketFactory.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2721881693@qq.com","uprxlqofioxfdggd");
            }
        });

        //开启debug模式
        session.setDebug(true);

        // 获取连接对象
        Transport transport = session.getTransport();
        //连接服务器
        transport.connect("smtp.qq.com","2721881693@qq.com","uprxlqofioxfdggd");
        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("2721881693@qq.com"));
        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress("16619844489@163.com"));
        //邮件标题
        mimeMessage.setSubject("Hello Mail");
        //邮件内容
        mimeMessage.setContent("对酒当歌,人生几何譬","text/html;charset=UTF-8");
        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        //关闭连接
        transport.close();
        return "发送成功!!!";
    }

}
