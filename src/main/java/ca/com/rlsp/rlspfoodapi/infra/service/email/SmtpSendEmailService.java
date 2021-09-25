package ca.com.rlsp.rlspfoodapi.infra.service.email;

import ca.com.rlsp.rlspfoodapi.core.email.EmailProperties;
import ca.com.rlsp.rlspfoodapi.domain.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpSendEmailService implements SendEmailService {

    public static final String MSG_EMAIL_NOT_SEND = "System cannot send the email";
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public void send(Message message) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            mimeMessageHelper.setFrom(emailProperties.getSender());
            mimeMessageHelper.setTo(message.getDestination().toArray(new String[0]));
            mimeMessageHelper.setSubject(message.getSubject());
            mimeMessageHelper.setText(message.getBody(), true); // true, pois sera em HTML

            javaMailSender.send(mimeMessage);
        } catch (Exception e){
            throw new EmailException(MSG_EMAIL_NOT_SEND, e);
        }

    }
}
