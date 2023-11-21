package yezak.api.util.mail;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/mail")
@RestController
@Slf4j
@Tag(name = "Mail", description = "회원가입 메일 전송")

public class MailController {

    @Autowired
    private MailService mailService;

//    @PostMapping("/send-mail")
//    public void sendMail(@RequestBody MailRequest mailRequest) throws MessagingException {
//        mailService.sendMail(mailRequest);
//    }
}

