package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbPay;
import com.example.yanolza.model.entity.TbRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TbRoomRepository extends JpaRepository<TbRoom, Integer> {
    Optional<TbRoom> findByid(Integer id);
    List<TbRoom> findAll();
    List<TbRoom> findAllById(Integer id);
}
