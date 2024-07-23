package com.rajneesh304.textStore.dao;

import com.rajneesh304.textStore.model.Text;

import java.util.List;

public interface CustomTextRepo {
    List<Text> findTextsByCriteria(String exposure, String category, List<String> tags);
}
