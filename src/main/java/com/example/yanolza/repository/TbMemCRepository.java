package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbMemC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbMemCRepository extends JpaRepository<TbMemC, Integer> {
}
