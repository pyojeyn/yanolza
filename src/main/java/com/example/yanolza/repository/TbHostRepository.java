package com.example.yanolza.repository;

import com.example.yanolza.model.entity.TbHost;
import com.example.yanolza.model.entity.TbPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface TbHostRepository extends JpaRepository< TbHost,Integer> {
    List<TbHost> findAllByPkAdmit(String pkAdmit);
    Optional<TbHost> findByPkAdmitAndId(String  pkAdmit , Integer id);
    Optional<TbHost> findById(Integer id);
    List<TbHost> findAllByPkAdmitAndMyType(String pkAdmit, String myType);
}
