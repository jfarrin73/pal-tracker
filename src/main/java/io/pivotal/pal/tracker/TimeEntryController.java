package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = repository.create(timeEntryToCreate);
        return new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = repository.find((id));
        return timeEntry == null
                ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : new ResponseEntity(timeEntry, HttpStatus.OK);
    }

    @RequestMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(repository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = repository.update(id, expected);
        return timeEntry == null
                ? new ResponseEntity(HttpStatus.NOT_FOUND)
                : new ResponseEntity(timeEntry, HttpStatus.OK);
    }

    @RequestMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        repository.delete(id);
        return new ResponseEntity(new TimeEntry(), HttpStatus.NO_CONTENT);
    }
}
