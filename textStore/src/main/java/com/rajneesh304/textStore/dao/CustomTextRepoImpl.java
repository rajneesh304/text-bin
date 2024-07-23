package com.rajneesh304.textStore.dao;

import com.rajneesh304.textStore.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CustomTextRepoImpl implements CustomTextRepo{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Text> findTextsByCriteria(String exposure, String category, List<String> tags) {
        Query query = new Query();

        if (exposure != null) {
            query.addCriteria(Criteria.where("exposure").is(exposure));
        }
        if (category != null) {
            query.addCriteria(Criteria.where("category").is(category));
        }
        if (tags != null && !tags.isEmpty()) {
            query.addCriteria(Criteria.where("tags").all(tags));
        }
        return mongoTemplate.find(query, Text.class);
    }
}
