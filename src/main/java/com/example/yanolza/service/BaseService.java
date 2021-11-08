package com.example.yanolza.service;

import com.example.yanolza.Ifs.CrudInterface;
import com.example.yanolza.model.entity.TbHost;
import com.example.yanolza.model.entity.TbHostImg;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.response.TbHostApiResponse;
import com.example.yanolza.model.network.response.TbHostImgResponse;
import com.example.yanolza.model.network.response.TbHostImgUserApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected JpaRepository<Entity, Integer> baseRepository;
}
