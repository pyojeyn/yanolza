package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbHost;
import com.example.yanolza.model.entity.TbPay;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbHostApiRequest;
import com.example.yanolza.model.network.response.TbHostForPayResponse;
import com.example.yanolza.model.network.response.TbHostPayResponse;
import com.example.yanolza.model.network.response.TbPayApiResponse;
import com.example.yanolza.repository.TbHostRepository;
import com.example.yanolza.repository.TbPayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TbHostServiceTest extends BaseService<TbHostApiRequest, TbHostForPayResponse, TbHost> {

    @Autowired
    private  TbPayApiService tbPayApiService;
    @Autowired
    private TbHostRepository tbHostRepository;

    // 호스트가 가지고있는 제일 최근 예약정보 1(scheduleManagement)
    public Header<TbHostPayResponse> hostPay(Integer id){
        TbHost tbHost = tbHostRepository.getById(id);
        TbHostForPayResponse tbHostForPayResponse = response(tbHost);

        List<TbPay> tbPayList = tbHost.getTbPayList();
        List<TbPayApiResponse> tbPayApiResponseList = tbPayList.stream()

                .map(tbPay -> {
                    TbPayApiResponse tbPayApiResponse = tbPayApiService.response(tbPay);
                    return tbPayApiResponse;
                })
                .collect(Collectors.toList());
        tbHostForPayResponse.setTbPayApiResponseList(tbPayApiResponseList);
        TbHostPayResponse tbHostPayResponse = TbHostPayResponse.builder()
                .tbHostForPayResponse(tbHostForPayResponse)
                .build();
        return Header.OK(tbHostPayResponse);

    }

    public TbHostForPayResponse response(TbHost tbHost) {
        TbHostForPayResponse tbHostForPayResponse = TbHostForPayResponse.builder()
                .tbHostid(tbHost.getId())
                .build();
        return tbHostForPayResponse;
    }

    @Override
    public Header<TbHostForPayResponse> create(Header<TbHostApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbHostForPayResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbHostForPayResponse> update(Header<TbHostApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbHostForPayResponse> delete(Integer id) {
        return null;
    }
}
