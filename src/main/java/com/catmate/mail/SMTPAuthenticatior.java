package com.catmate.mail;
 
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
 
public class SMTPAuthenticatior extends Authenticator{
 
    @Override
    protected PasswordAuthentication getPasswordAuthentication() { // 네이버 SMTP를 이용하기 위한 아이디
        return new PasswordAuthentication("jooojih@naver.com", "jojijih21!");
    }
    
}
