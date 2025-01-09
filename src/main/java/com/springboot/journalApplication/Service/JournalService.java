package com.springboot.journalApplication.Service;

import com.springboot.journalApplication.Entity.JournalEntry;
import com.springboot.journalApplication.Repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public List<JournalEntry> getAllJournal(){
         List<JournalEntry> journalEntries= journalRepository.findAll();
         return journalEntries;
    }

    public Optional<JournalEntry> getJournalById(ObjectId id){
        return journalRepository.findById(id);
    }

//    public JournalEntry getJournalByTitle(String title){
//        return journalRepository.find;
//
//    }
    public JournalEntry addJournal(JournalEntry entry){
//         journalRepository.insert(entry);
         journalRepository.save(entry);
         return entry;
    }

    public ObjectId deleteJournal(ObjectId id){
//       JournalEntry journalEntry= journalRepository.findById(id).get();
       journalRepository.deleteById(id);
        return id;
    }

    public JournalEntry updateJournal(ObjectId id, JournalEntry entry){
        JournalEntry journalEntry = journalRepository.findById(id).get();
        if(journalEntry != null){
            journalEntry.setTitle(entry.getTitle() != null && !entry.getTitle().equals("") ? entry.getTitle() : journalEntry.getTitle());
            journalEntry.setContent(entry.getContent() != null && !entry.getContent().equals("") ? entry.getContent() : journalEntry.getContent());
        }
//        journalRepository.save(entry);

        return entry;
    }
}
