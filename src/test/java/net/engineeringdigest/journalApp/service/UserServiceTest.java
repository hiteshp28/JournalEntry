package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserEntryRepo userEntryRepo;
    @Disabled
    @Test
    public void testFindByUserName(){
        assertNotNull(userEntryRepo.findByUserName("ABC"));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Yash",
            "Kunal",
            "Sahil"
    })
    public void find(String name)
    {
        assertNotNull(userEntryRepo.findByUserName(name));
    }
}
