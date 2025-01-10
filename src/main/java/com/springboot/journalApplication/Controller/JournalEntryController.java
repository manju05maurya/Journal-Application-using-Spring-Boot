package com.springboot.journalApplication.Controller;

import com.springboot.journalApplication.Entity.JournalEntry;
import com.springboot.journalApplication.Entity.User;
import com.springboot.journalApplication.Repository.UserRepository;
import com.springboot.journalApplication.Service.JournalService;
import com.springboot.journalApplication.Service.UserService;
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
    public ResponseEntity<?> getAllJournal(){
        List<JournalEntry> allEntries = journalService.getAllJournal();
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalByUsername(@PathVariable String username){
        try {
            List<JournalEntry> allEntries = journalService.getAllJournalByUsername(username);
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@NotNull @RequestBody JournalEntry entry, @PathVariable String username){
        try {
            journalService.addJournal(entry, username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id/{id}/{username}")
    public ResponseEntity<ObjectId>  deleteEntry(@PathVariable ObjectId id,@PathVariable String username){
        try {
            ObjectId oId= journalService.deleteJournal(id, username);
            return new ResponseEntity<>(oId, HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/{id}/{username}")
    public ResponseEntity<JournalEntry> updateEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry entry,
            @PathVariable String username){
           try{
               System.out.println("try block");
               JournalEntry newEntry= journalService.updateJournal(id,entry,username);
               return new ResponseEntity<>(newEntry,HttpStatus.CREATED);
           }
           catch (Exception e){
               System.out.println("catch block");
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
    }

}

//    @GetMapping("/{title}")
//    public JournalEntry getJournalByTitle(@PathVariable String title){
//        return journalService.getJournalByTitle(title);
//    }
