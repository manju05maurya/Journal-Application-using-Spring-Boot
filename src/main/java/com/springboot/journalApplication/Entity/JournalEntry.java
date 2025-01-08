package com.springboot.journalApplication.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class JournalEntry {

    private long id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "JournalEntry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public JournalEntry(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


}
