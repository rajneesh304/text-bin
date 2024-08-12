package com.rajneesh304.textStore.controller;

import com.rajneesh304.textStore.dao.TextRepo;
import com.rajneesh304.textStore.model.Text;
import com.rajneesh304.textStore.service.TextService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TextController {
    @Autowired
    TextRepo repo;

    @Autowired
    TextService textService;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(TextController.class);

    @GetMapping(path = "/texts")
    public ResponseEntity<List<Text>> getText(@RequestParam(required = false) String exposure,
                                              @RequestParam(required = false) String criteria,
                                              @RequestParam(required = false) List<String> tags){
        logger.info("Received request with exposure: {}, criteria: {}, tags: {}", exposure, criteria, tags);
        List<Text> texts = textService.getTextsByCriteria(exposure, criteria, tags);
        logger.info("Returning {} texts", texts.size());
        if(texts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body(texts);
    }

    @GetMapping(path = "/texts/{id}")
    public ResponseEntity<Text> getText(@PathVariable("id") String id){
        Text text = repo.findById(id).orElse(null);
        if(text == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(text);
    }

    @PutMapping(path = "/texts/{id}")
    public void putText(@RequestBody Text textBody, @PathVariable("id") String id){
        Text text = repo.findById(id).orElse(null);
        if(text == null)
        {
            textBody.setId(id);
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            if(textBody.getText()!=null){
                text.setText(textBody.getText());
                repo.save(text);
                ResponseEntity.status(HttpStatus.ACCEPTED).body(text);
            }
            ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(text);
        }
    }

    @PostMapping(path = "/texts")
    public ResponseEntity<Text> createText(@RequestBody @Valid Text text, @RequestParam(required = false) Long expireAfterMillis){
        logger.error(text.toString());
        if(textService.createText(text, expireAfterMillis) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(text);
    }

    @DeleteMapping(path = "/texts/{id}")
    public ResponseEntity<Text> deleteText(@PathVariable("id") String id){
        Text text = repo.findById(id).orElse(null);

        if(text != null){
            repo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
