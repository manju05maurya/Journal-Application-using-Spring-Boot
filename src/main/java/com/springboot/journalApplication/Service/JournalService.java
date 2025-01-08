package com.springboot.journalApplication.Service;

import com.springboot.journalApplication.Entity.JournalEntry;
import com.springboot.journalApplication.Repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public List<JournalEntry> getJournal(){
         List<JournalEntry> journalEntries= journalRepository.findAll();
         return journalEntries;
    }

    public JournalEntry addJournal(JournalEntry entry){
         journalRepository.insert(entry);
         return entry;
    }

    public String deleteJournal(String id){
        journalRepository.deleteById(id);
        return id;
    }

    public JournalEntry updateJournal(String id, JournalEntry entry){
        journalRepository.save(entry);

        return entry;
    }
}
