package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbHostImg;
import com.example.yanolza.model.entity.TbMem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbHostImgRepository extends JpaRepository<TbHostImg, Integer> {
    List<TbHostImg> findAllByTbHostId(Integer tbHostId);
    Optional<TbHostImg> findById(Integer id);
}
