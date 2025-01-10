package com.springboot.journalApplication.Service;

import com.springboot.journalApplication.Entity.JournalEntry;
import com.springboot.journalApplication.Entity.User;
import com.springboot.journalApplication.Repository.JournalRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired UserService userService;

    public List<JournalEntry> getAllJournal() {
        return journalRepository.findAll();
    }

    public List<JournalEntry> getAllJournalByUsername(String username){
        User user = userService.getUserByName(username);
        List<JournalEntry> allEntries = user.getJournalEntries();
        return allEntries;
    }
    public Optional<JournalEntry> getJournalById(ObjectId id) {
        return journalRepository.findById(id);
    }

    public JournalEntry addJournal(JournalEntry entry, String username) {
//          journalRepository.insert(entry);
//            User user = userService.getUserByName(username);
//            user.getJournalEntries().add(entry);
//            userService.addUser(user);
        try {
            User user = userService.getUserByName(username);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalRepository.save(entry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
            return entry;
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return null;
    }

    public ObjectId deleteJournal(ObjectId id, String username) {
        User user = userService.getUserByName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        journalRepository.deleteById(id);
        userService.saveUser(user);
        return id;
    }

    public JournalEntry updateJournal(ObjectId id, JournalEntry entry, String usernname) {
        try {
            User user = userService.getUserByName(usernname);
            JournalEntry journalEntryOld = journalRepository.findById(id).get();
            journalEntryOld.setTitle(entry.getTitle());
            journalEntryOld.setContent(entry.getContent());
            journalRepository.save(journalEntryOld);
            return entry;
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return entry;
    }
}


//        if(journalEntryOld != null){
//                journalEntryOld.setTitle(entry.getTitle());
//                journalEntryOld.setContent(entry.getContent());
////            journalEntryOld.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : journalEntryOld.getTitle());
////            journalEntryOld.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : journalEntryOld.getContent());
//                }
////        journalRepository.save(entry);



//    public JournalEntry getJournalByTitle(String title){
//        return journalRepository.find;
//
//    }