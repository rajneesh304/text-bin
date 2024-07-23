package com.rajneesh304.textStore.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Document(collection = "Text")
public class Text {

    @Id
    private String id;
    private String text;
    private String userId;
    @Indexed(name = "expire_at_index", expireAfterSeconds = 0)
    private Instant expireAt; // Field to store expiration date
    @ElementCollection
    private List<String> tags;
    private String exposure; // can take 3 values - private/unlisted/public
    private String category; // Enum of a list of values it can take. Need separate endpoint for it


//    Constructors
    public Text() { }

    public Text(String text, String userId, List<String> tags, String exposure, String category) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.text = text;
        this.userId = userId;
        this.tags = tags;
        this.exposure = exposure;
        this.category = category;
    }

//    Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTags() { return tags; }

    public void setTags(List<String> tags) { this.tags = tags; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getExposure() { return exposure; }

    public void setExposure(String exposure) { this.exposure = exposure; }

    public Instant getExpireAt() { return expireAt; }

    public void setExpireAt(Instant expireAt) { this.expireAt = expireAt; }
}
