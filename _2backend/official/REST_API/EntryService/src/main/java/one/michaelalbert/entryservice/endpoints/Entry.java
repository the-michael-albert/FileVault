package one.michaelalbert.entryservice.endpoints;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class Entry {

    public static void main(String[] args) {
        SpringApplication.run(Entry.class, args);
    }

    @PutMapping("/entry/{resource_id}")
    public ResponseEntity<String> updateEntry(
            @PathVariable("resource_id") String resourceId,
            @RequestBody MyObject myObject,
            @RequestHeader("ACCESS_TOKEN") String accessToken) {

        // Your logic goes here

        return new ResponseEntity<>("Resource updated", HttpStatus.OK);
    }

    public static class MyObject {
        // Your object fields go here
    }
}
