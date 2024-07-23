package com.rajneesh304.textStore.dao;

import com.rajneesh304.textStore.model.Text;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TextRepo extends MongoRepository <Text, String>, CustomTextRepo { }
