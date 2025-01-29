package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserEntryRepo userRepo;

    public void SaveEntry(UserEntity user)
    {

        userRepo.save(user);
    }
    public List<UserEntity>getAll(){
        return userRepo.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id)
    {
        return userRepo.findById(String.valueOf(id));
    }

    public void DeleteById(ObjectId id)
    {
        userRepo.deleteById(String.valueOf(id));
    }
    public UserEntity findByUserName(String userName)
    {
        return userRepo.findByUserName(userName);
    }
}
