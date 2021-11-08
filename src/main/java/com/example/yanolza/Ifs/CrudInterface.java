package com.example.yanolza.Ifs;

import com.example.yanolza.model.network.Header;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request);

    Header<Res> read(Integer id);

    Header<Res> update(Header<Req> request);

    Header<Res> delete(Integer id);
}
