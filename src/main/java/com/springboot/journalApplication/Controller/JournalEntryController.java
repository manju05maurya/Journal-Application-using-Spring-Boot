package com.springboot.journalApplication.Controller;

import com.springboot.journalApplication.Entity.JournalEntry;
import com.springboot.journalApplication.Service.JournalService;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal-entry") //add mapping to class so every mapping path will be start using /journal/others
public class JournalEntryController {

    @Autowired
    private JournalService journalService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            List<JournalEntry> allEntries= journalService.getAllJournal();
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    ResponseEntity<?>
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id){
//        return journalService.getJournalById(id).get();
        try{
            Optional<JournalEntry> journalEntry= journalService.getJournalById(id);
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/{title}")
//    public JournalEntry getJournalByTitle(@PathVariable String title){
//        return journalService.getJournalByTitle(title);
//    }


    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@NotNull @RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalService.addJournal(entry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ObjectId>  deleteEntry(@PathVariable ObjectId id){
        try {
            ObjectId oId= journalService.deleteJournal(id);
            return new ResponseEntity<>(oId, HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry){
           try{
               JournalEntry newEntry= journalService.updateJournal(id,entry);
               return new ResponseEntity<>(newEntry,HttpStatus.CREATED);
           }
           catch (Exception e){
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
    }

}
