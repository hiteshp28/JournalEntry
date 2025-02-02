package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void send()
    {
        emailService.sendMail("sahilamrutkar1808@gmail.com","To test Java Mail Sender","Hello, How are you? ");
    }
}
