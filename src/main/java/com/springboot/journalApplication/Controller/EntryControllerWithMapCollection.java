package com.springboot.journalApplication.Controller;

import com.springboot.journalApplication.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal") //add mapping to class so every mapping path will be start using /journal/others
public class EntryControllerWithMapCollection {

    private Map<ObjectId, JournalEntry> journalEntries=new HashMap<>(); //we are not connecting to DB now so consider it as a table for now

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public Boolean createEntry(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(),entry);
        return true;
    }

    @DeleteMapping("/id/{id}")
    public ObjectId  deleteEntry(@PathVariable ObjectId id){
        journalEntries.remove(id);
        return id;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry){
        journalEntries.put(id,entry);
        return journalEntries.get(id);
    }

}
