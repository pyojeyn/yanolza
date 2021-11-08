package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbHostImg;
import com.example.yanolza.model.entity.TbRoomImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbRoomImgRepository extends JpaRepository<TbRoomImg, Integer> {
    List<TbRoomImg> findAllByTbRoomId(Integer tbRoomId);
}
