package com.bmc.controller;

import com.bmc.mapper.request.TheaterEntryDto;
import com.bmc.mapper.request.TheaterSeatsEntryDto;
import com.bmc.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;
    // Create a new theater
    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody TheaterEntryDto theaterEntryDto) {
        Theater createdTheater = theaterService.createTheater(theaterEntryDto);
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }

    // Get all theaters
    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        List<Theater> theaters = theaterService.getAllTheaters();
        return new ResponseEntity<>(theaters, HttpStatus.OK);
    }

    // Get theater by ID
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        Theater theater = theaterService.getTheaterById(id);
        return new ResponseEntity<>(theater, HttpStatus.OK);
    }

    // Update a theater by ID
    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody TheaterEntryDto  theaterDetails) {
        Theater updatedTheater = theaterService.updateTheater(id, theaterDetails);
        return new ResponseEntity<>(updatedTheater, HttpStatus.OK);
    }

    // Delete a theater by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addTheaterSeats")
    public ResponseEntity<String> addTheaterSeats(@RequestBody TheaterSeatsEntryDto entryDto) {

        try {
            String result = theaterService.addTheaterSeats(entryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}