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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<UserEntity>getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody UserEntity user){
        userService.SaveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> editUser(@RequestBody UserEntity user, @PathVariable String userName){
        UserEntity userInDb = userService.findByUserName(userName);
        if(userInDb!=null)
        {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.SaveEntry(userInDb);
        }
        return new ResponseEntity<UserEntity>(userInDb,HttpStatus.OK);
    }


}
