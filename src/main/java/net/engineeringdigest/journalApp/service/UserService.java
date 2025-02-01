package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserEntryRepo userRepo;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();



    public void saveNewUser(UserEntity user)
    {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User"));
            userRepo.save(user);
        }catch (Exception e)
        {

            log.info("Duplicate User Added");
            log.warn("Duplicate User Added");
            log.error("Duplicate User Added");
            log.trace("Duplicate User Added");
            log.debug("Duplicate User Added");
        }

    }
    public void saveAdminUser(UserEntity user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN","USER"));
        userRepo.save(user);
    }

    public void saveUser(UserEntity user)
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
