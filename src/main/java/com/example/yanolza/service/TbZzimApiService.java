package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbZzim;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbZzimApiRequest;
import com.example.yanolza.model.network.response.TbZzimApiResponse;
import com.example.yanolza.repository.TbHostRepository;
import com.example.yanolza.repository.TbMemRepository;
import com.example.yanolza.repository.TbZzimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TbZzimApiService extends BaseService<TbZzimApiRequest, TbZzimApiResponse, TbZzim>{
    @Autowired
    private final TbHostRepository tbHostRepository;
    @Autowired
    private final TbMemRepository tbMemRepository;
    @Autowired
    private final TbZzimRepository tbZzimRepository;

    // 찜 등록
    public Header<TbZzimApiResponse> regz(Header<TbZzimApiRequest> request){
        TbZzimApiRequest tbZzimApiRequest = request.getData();
        TbZzim tbZzim =TbZzim.builder()
                .zzName(tbZzimApiRequest.getZzName())
                .zzPic(tbZzimApiRequest.getZzPic())
                .tbHost(tbHostRepository.getById(tbZzimApiRequest.getTbHostId()))
                .tbMem(tbMemRepository.getById(tbZzimApiRequest.getTbMemId()))
                .build();
        TbZzim newZzim = baseRepository.save(tbZzim);
        return Header.OK(response(newZzim));

    }

    //개인 찜 리스트
    public List<TbZzimApiRequest> getzzim(Integer id){
        List<TbZzim> tbZzims = tbZzimRepository.findAllById(id);
        List<TbZzimApiRequest> tbZzimApiRequestList = new ArrayList<>();
        for (TbZzim tbZzim : tbZzims){
            TbZzimApiRequest tbZzimApiRequest = TbZzimApiRequest.builder()
                    .id(tbZzim.getId())
                    .zzName(tbZzim.getZzName())
                    .zzPic(tbZzim.getZzPic())
                    .tbHostId(tbZzim.getTbHost().getId())
                    .tbMemId(tbZzim.getTbMem().getId())
                    .build();
            tbZzimApiRequestList.add(tbZzimApiRequest);
        }
        return tbZzimApiRequestList;
    }
    // 찜 취소
    public Header zzimdel(Integer id){
        Optional<TbZzim> optional = baseRepository.findById(id);
        return optional.map(tbZzim -> {
            baseRepository.delete(tbZzim);
            return Header.OK();
        }).orElseGet(()->Header.Error("ERROR"));
    }

    //
    //response
    public TbZzimApiResponse response(TbZzim tbZzim){
        TbZzimApiResponse tbZzimapiResponse = TbZzimApiResponse.builder()
                .id(tbZzim.getId())
                .zzName(tbZzim.getZzName())
                .zzPic(tbZzim.getZzPic())
                .tbHostId(tbZzim.getTbHost().getId())
                .tbMemId(tbZzim.getTbMem().getId())
                .build();
        return tbZzimapiResponse;
    }

    @Override
    public Header<TbZzimApiResponse> create(Header<TbZzimApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbZzimApiResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbZzimApiResponse> update(Header<TbZzimApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbZzimApiResponse> delete(Integer id) {
        return null;
    }
}
