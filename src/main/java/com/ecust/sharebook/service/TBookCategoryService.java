package com.ecust.sharebook.service;

import com.ecust.sharebook.pojo.rBookCategory;

import java.util.List;
import java.util.Map;

public interface TBookCategoryService {
    rBookCategory findCatgbyIsbn(Map<String, Object> map);
    List<rBookCategory> findbyIsbn(Map<String, Object> map);
}
