package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbReviewRepository extends JpaRepository<TbReview, Integer> {
    Optional<TbReview> findById(Integer id);
    List<TbReview> findAllByIsche(String ische);
    List<TbReview> findAllByTbHostIdAndIsche(Integer tbHostId, String ische);
}
