package com.wdc.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class MailBoot implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${mail.to}")
    private String to;

    public static void main(String[] args) {
        SpringApplication.run(MailBoot.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sendSimpleMail();
        sendHtmlMail();
        sendAttachMail();
        sendImgMail();
    }

    private void sendSimpleMail() {
        String subject = "simple test";
        String content = "Hello World";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom(from);
        message.setTo(to);
        message.setText(content);
        javaMailSender.send(message);
        logger.info("-----------------simple success---------------");
    }

    private void sendHtmlMail() throws MessagingException {
        String subject = "html test";
        String content = "<html><body><h1>Hello World</h1></body></html>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setSubject(subject);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setText(content, true);
        javaMailSender.send(message);
        logger.info("-----------------html success---------------");
    }

    private void sendAttachMail() throws MessagingException, FileNotFoundException {
        String subject = "attach test";
        String content = "请查收附件！";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setSubject(subject);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setText(content, true);
        File file = ResourceUtils.getFile("classpath:application.properties");
        messageHelper.addAttachment(file.getName(), new FileSystemResource(file));
        javaMailSender.send(message);
        logger.info("-----------------attach success---------------");
    }



    private void sendImgMail() throws MessagingException, FileNotFoundException {
        String subject = "img test";
        String rscId = "001";
        String content = "<html><body>图片：<img src=\"cid:"+ rscId +"\" /></body></html>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setSubject(subject);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setText(content, true);
        File file = ResourceUtils.getFile("classpath:huge.jpeg");
        messageHelper.addInline(rscId, new FileSystemResource(file));
        javaMailSender.send(message);
        logger.info("-----------------img success---------------");
    }
}
