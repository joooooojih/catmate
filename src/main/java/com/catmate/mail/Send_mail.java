package com.catmate.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Send_mail {

    public void send_mail(String email, String content) throws AddressException, MessagingException {

        Properties p = System.getProperties();

        p.put("mail.smtp.host","smtp.naver.com");
        p.put("mail.smtp.port", "465");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.debug", "true");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator auth = new SMTPAuthenticatior();
        Session ses = Session.getInstance(p, auth);
        ses.setDebug(true);

        Message mimeMessage = new MimeMessage(ses);
        mimeMessage.setFrom(new InternetAddress("jooojih@naver.com"));
        InternetAddress toAddr = new InternetAddress(email);

        mimeMessage.setRecipient(Message.RecipientType.TO, toAddr);
        mimeMessage.setSubject("CATMATE 회원가입 인증번호"); // 제목
        
        
        mimeMessage.setText(content);
        Transport.send(mimeMessage);
    }
}
