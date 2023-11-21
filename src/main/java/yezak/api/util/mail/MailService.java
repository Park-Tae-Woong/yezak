package yezak.api.util.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(MailRequest mailRequest) throws MessagingException {
        String toEmail = mailRequest.getToEmail();
        String subject = mailRequest.getSubject();
        String message = mailRequest.getMessage();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(message, true); // true: html을 포함하는 경우

        javaMailSender.send(mimeMessage);
    }
}
