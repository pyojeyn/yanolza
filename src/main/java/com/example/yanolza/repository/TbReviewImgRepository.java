package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbHostImg;
import com.example.yanolza.model.entity.TbReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbReviewImgRepository extends JpaRepository<TbReviewImg , Integer> {
    List<TbReviewImg> findAllByTbReviewId(Integer tbReviewId);
}
