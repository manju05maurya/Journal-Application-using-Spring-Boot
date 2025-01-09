package com.springboot.journalApplication.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)//indexing-searching will be fast for username---indexing will not be created auto, we have to set an property in application for it.
    @NonNull
    private String username; //we want that a user provide either a username or an email id
    @NonNull
    private String password;
    @DBRef  //user collection will contain a reference of JournalEntry(like foreign key), it will create a link bw two collections.
    private List<JournalEntry> journalEntries =  new ArrayList<>();
}
