package com.xyz.fch_sp.app.core.mail.impl;

import com.xyz.fch_sp.app.core.mail.EmailProperties;
import com.xyz.fch_sp.app.core.mail.MailService;
import org.beetl.core.*;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.rmi.MarshalledObject;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 用来发送模版邮件
     */
    @Autowired
    private Template template;



    @Bean
    @Primary
    public Template template() throws IOException {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("notification,${text}");

        return t;

    }

    @Value("${spring.mail.username}")
    private String from;


    @Value("${sender-email.ali.username}")
    private String username;

    @Value("${sender-email.ali.password}")
    private String password;

    @Value("${sender-email.ali.port}")
    private Integer port;

    @Value("${sender-email.ali.host}")
    private String host;



//    @Value("${spring.mail.ali.username}")
//    private String aliFrom;

    @Override
    public void send(String to, String subject, String text) {

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);

        template.binding("text", text);
        String emailContent = template.render();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    @Override
    public void snedAli(String to, String subject, String text) {

        MimeMessage message = null;

        try {

            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            JavaMailSenderImpl jmsi = (JavaMailSenderImpl) mailSender;
            jmsi.setHost(host);
            jmsi.setUsername(username);
            jmsi.setPassword(password);
            jmsi.setPort(port);

            jmsi.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
