package com.rajneesh304.textStore.service;

import com.rajneesh304.textStore.dao.TextRepo;
import com.rajneesh304.textStore.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class TextService {
    @Autowired
    private TextRepo textRepo;

    public List<Text> getTextsByCriteria(String exposure, String criteria, List<String> tags) {
        return textRepo.findTextsByCriteria(exposure, criteria, tags);
    }

    public Text createText(Text text, Long expireAfterMillis) {
        if (expireAfterMillis != null) {
            System.out.println("Current Date/time: " + Instant.now());
            Instant expireAt = Instant.now().plus(expireAfterMillis, ChronoUnit.MILLIS);
            text.setExpireAt(expireAt);
            text.setExpireAt(expireAt);
            System.out.println("modified Date/time: " + expireAt);
        } else {
            text.setExpireAt(null); // Set to null for non-expiring entries
        }
        return textRepo.save(text);
    }
}
