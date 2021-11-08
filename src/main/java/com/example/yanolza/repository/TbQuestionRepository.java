package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbPay;
import com.example.yanolza.model.entity.TbQuestion;
import com.example.yanolza.model.network.response.TbQuestionApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TbQuestionRepository extends JpaRepository<TbQuestion, Integer> {
    Optional<TbQuestion> findById(Integer id);
    List<TbQuestion> findAllById(Integer id);
    List<TbQuestion> findAllByTbMemId(Integer tbMemId);
}
