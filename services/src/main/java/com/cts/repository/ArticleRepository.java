package com.cts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Articles;
@Repository
public interface ArticleRepository extends CrudRepository<Articles,Integer> {

}
