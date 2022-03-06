package paf.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import paf.finalproject.models.ContactSupport;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(ContactSupport contactus) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("shawntfipapp@gmail.com");
        mail.setSubject(contactus.getSubject());
        mail.setText("sender name>> "+contactus.getName()+"\n"+"sender email>> "+contactus.getEmail() +"\n" + "sender enquiry>> "+contactus.getEnquiry());

        javaMailSender.send(mail);
    }

    
}
