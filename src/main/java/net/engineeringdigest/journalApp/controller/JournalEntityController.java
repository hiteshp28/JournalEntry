package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {


    @Autowired
    private JournalEntryService journalentryservice;
    @Autowired
    private UserService userService;
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllGeneralEntriesOfUser(@PathVariable String userName){
        UserEntity user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> postEntry(@RequestBody JournalEntry abc,@PathVariable String userName)
    {
        try{
                journalentryservice.SaveEntry(abc,userName);
                return new ResponseEntity<JournalEntry>(abc, HttpStatus.OK);
            }catch(Exception e) {

            return new ResponseEntity<JournalEntry>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{ID}")
    public ResponseEntity<?> Hello(@PathVariable ObjectId ID){
        Optional<JournalEntry> byId = journalentryservice.findById(ID);
        if(byId.isPresent())
        {
            return new ResponseEntity<JournalEntry>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{userName}/{ID}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId ID,@PathVariable String userName){
            journalentryservice.DeleteById(ID,userName);
            return new ResponseEntity<JournalEntry>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myid}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId myid,
                                                          @PathVariable String userName,
                                                          @RequestBody JournalEntry entry)
    {
        JournalEntry jn=journalentryservice.findById(myid).orElse(null);
        if(jn!=null)
        {
            jn.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")?entry.getTitle(): jn.getTitle());
            jn.setContent(entry.getContent()!=null && !entry.getContent().equals("")?entry.getContent(): jn.getContent());
            journalentryservice.SaveEntry(jn);
            return new ResponseEntity<JournalEntry>( jn,HttpStatus.OK);
        }

        return new ResponseEntity<JournalEntry>( HttpStatus.NO_CONTENT);
    }
}
