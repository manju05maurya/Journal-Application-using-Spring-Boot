package com.springboot.journalApplication.Controller;

import com.springboot.journalApplication.Entity.JournalEntry;
import com.springboot.journalApplication.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal-entry") //add mapping to class so every mapping path will be start using /journal/others
public class JournalEntryController {

    @Autowired
    private JournalService journalService;


    @GetMapping
    public List<JournalEntry> getAll(){

        return journalService.getJournal();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry){

        return journalService.addJournal(entry);
    }

    @DeleteMapping("/id/{id}")
    public String  deleteEntry(@PathVariable String id){

        return journalService.deleteJournal(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable String id, @RequestBody JournalEntry entry){

        return journalService.updateJournal(id,entry);
    }

}
