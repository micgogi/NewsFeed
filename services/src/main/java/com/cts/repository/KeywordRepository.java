package com.cts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.entity.KeywordSearch;

public interface KeywordRepository extends CrudRepository<KeywordSearch,Integer> {
    List<KeywordSearch> findKeywordByUsername(String username);
}
