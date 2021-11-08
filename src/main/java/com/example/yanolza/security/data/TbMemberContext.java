package com.example.yanolza.security.data;

import com.example.yanolza.model.entity.TbMem;
import com.example.yanolza.model.entity.TbMemRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;


@Getter
public class TbMemberContext extends User{
    private TbMem tbMem;

    public TbMemberContext(TbMem tbMem) {
        super(tbMem.getMemEmail(), tbMem.getMemPw(),getAuthorities(tbMem.getRole()));
        this.tbMem = tbMem;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(TbMemRole role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getKey()));
    }
    }

