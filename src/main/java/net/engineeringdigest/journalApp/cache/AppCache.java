package net.engineeringdigest.journalApp.cache;


import net.engineeringdigest.journalApp.entity.ConfigJournalEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo;
    public Map<String,String>appCache;
    public enum keys{
        WEATHER_API;
    }
    @PostConstruct
    public void init(){
        appCache= new HashMap<>();
        List<ConfigJournalEntity> all = configJournalAppRepo.findAll();
        for (ConfigJournalEntity config:all
             ) {
            appCache.put(config.getKey(),config.getValue());
        }
    }
}
