package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbMem;
import com.example.yanolza.model.entity.TbMemC;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbMemApiRequest;
import com.example.yanolza.model.network.request.TbMemCRequest;
import com.example.yanolza.model.network.response.TbMemApiResponse;
import com.example.yanolza.model.network.response.TbMemCResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TbMemCApiService extends BaseService<TbMemCRequest, TbMemCResponse, TbMemC> {


    @Override
    public Header<TbMemCResponse> create(Header<TbMemCRequest> request) {
        TbMemCRequest tbMemCRequest = request.getData();
        TbMemC tbMemC = TbMemC.builder()
                .id(tbMemCRequest.getId())
                .memHp(tbMemCRequest.getMemHp())
                .memEmail(tbMemCRequest.getMemEmail())
                .memNkname(tbMemCRequest.getMemNkname())
                .reason(tbMemCRequest.getReason())
                .build();
        TbMemC newMemC = baseRepository.save(tbMemC);
        return Header.OK(response(newMemC));
    }

    public List<TbMemCRequest> List(){
        List<TbMemC> tbMemCS = baseRepository.findAll();
        List<TbMemCRequest> tbMemCRequestList = new ArrayList<>();

        for (TbMemC tbMemC : tbMemCS){
            TbMemCRequest tbMemCRequest = TbMemCRequest.builder()
                    .id(tbMemC.getId())
                    .memHp(tbMemC.getMemHp())
                    .memEmail(tbMemC.getMemEmail())
                    .memNkname(tbMemC.getMemNkname())
                    .reason(tbMemC.getReason())
                    .cRegdate(tbMemC.getCRegdate())
                    .build();
            tbMemCRequestList.add(tbMemCRequest);
        }
        return tbMemCRequestList;
    }

    public TbMemCResponse response(TbMemC tbMemC){
        TbMemCResponse tbMemCResponse = TbMemCResponse.builder()
                .id(tbMemC.getId())
                .memHp(tbMemC.getMemHp())
                .memEmail(tbMemC.getMemEmail())
                .memNkname(tbMemC.getMemNkname())
                .reason(tbMemC.getReason())
                .cRegdate(tbMemC.getCRegdate())
                .build();
        return tbMemCResponse;
    }


    @Override
    public Header<TbMemCResponse> read(Integer id) {
        return null;
    }
    @Override
    public Header<TbMemCResponse> update(Header<TbMemCRequest> request) {
        return null;
    }
    @Override
    public Header<TbMemCResponse> delete(Integer id) {
        return null;
    }
}
