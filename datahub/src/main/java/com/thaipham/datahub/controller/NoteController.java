package com.thaipham.datahub.controller;

import com.thaipham.datahub.model.Note;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final List<Note> notes = new ArrayList<>();

    @GetMapping
    public List<Note> getNotes() {
        return notes;
    }

    @PostMapping
    public Map<String, String> addNote(@RequestBody Note note) {
        notes.add(note);
        return Map.of(
            "status", "Note saved",
            "title", note.getTitle()
        );
    }
}
