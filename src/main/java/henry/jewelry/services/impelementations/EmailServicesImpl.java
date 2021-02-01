package henry.jewelry.services.impelementations;

import henry.jewelry.services.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServicesImpl implements EmailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String name , String email, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email);
        mailMessage.setReplyTo(name);
        mailMessage.setTo("mailerhenry7@gmail.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(message + "\n" + name + "\n" + email);

        javaMailSender.send(mailMessage);
    }
}
