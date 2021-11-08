package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbPay;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbPayApiRequest;
import com.example.yanolza.model.network.response.TbPayApiResponse;
import com.example.yanolza.repository.TbHostRepository;
import com.example.yanolza.repository.TbMemRepository;
import com.example.yanolza.repository.TbPayRepository;
import com.example.yanolza.repository.TbRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TbPayApiService extends BaseService<TbPayApiRequest, TbPayApiResponse, TbPay>{
    @Autowired
    private final TbRoomRepository tbRoomRepository;
    @Autowired
    private final TbMemRepository tbMemRepository;
    @Autowired
    private final TbPayRepository tbPayRepository;
    @Autowired
    private final TbHostRepository tbHostRepository;

    //예약
    public Header<TbPayApiResponse> book(Header<TbPayApiRequest> request){
        TbPayApiRequest tbPayApiRequest = request.getData();
        TbPay tbPay = TbPay.builder()
                .payName(tbPayApiRequest.getPayName())
                .payHp(tbPayApiRequest.getPayHp())
                .payDate(tbPayApiRequest.getPayDate())
                .payRoomName(tbPayApiRequest.getPayRoomName())  // 해당 객실이름
                .payHostName(tbPayApiRequest.getPayHostName())  // 해당 숙소이름
                .payCheckIn(tbPayApiRequest.getPayCheckIn())
                .payCheckOut(tbPayApiRequest.getPayCheckOut())
                .payMoney(tbPayApiRequest.getPayMoney())
                .payHowpay(tbPayApiRequest.getPayHowpay())
                .payIscar(tbPayApiRequest.getPayIscar())
                .payCancel("n")
                .tbHost(tbHostRepository.getById(tbPayApiRequest.getTbHostId()))
                .tbRoom(tbRoomRepository.getById(tbPayApiRequest.getTbRoomId()))
                .tbMem(tbMemRepository.getById(tbPayApiRequest.getTbMemId()))
                .build();
        TbPay newPay = baseRepository.save(tbPay);
        return Header.OK(response(newPay));
    }

    // 예약리스트(관리자)
    public List<TbPayApiRequest> getPayList(){
        List<TbPay> tbPays = tbPayRepository.findAllByPayCancel(Sort.by("id").descending(), "n");
        List<TbPayApiRequest> tbPayApiRequestList = new ArrayList<>();
        for(TbPay tbPay : tbPays){
            TbPayApiRequest tbPayApiRequest = TbPayApiRequest.builder()
                    .id(tbPay.getId())
                    .payName(tbPay.getPayName())
                    .payHp(tbPay.getPayHp())
                    .payIscar(tbPay.getPayIscar())
                    .payHowpay(tbPay.getPayHowpay())
                    .payDate(tbPay.getPayDate())
                    .payMoney(tbPay.getPayMoney())
                    .payHostName(tbPay.getPayHostName())
                    .payRoomName(tbPay.getPayRoomName())
                    .payCheckIn(tbPay.getPayCheckIn())
                    .payCheckOut(tbPay.getPayCheckOut())
                    .payCdate(tbPay.getPayCdate())
                    .payCancel(tbPay.getPayCancel())
                    .tbHostId(tbPay.getTbHost().getId())
                    .tbMemId(tbPay.getTbMem().getId())
                    .tbRoomId(tbPay.getTbRoom().getId())
                    .build();
            tbPayApiRequestList.add(tbPayApiRequest);
        }
        return tbPayApiRequestList;
    }

    // 예약 취소리스트(관리자)
    public List<TbPayApiRequest> getPayCList(){
        List<TbPay> tbPays = tbPayRepository.findAllByPayCancel(Sort.by("id").descending(),"y");
        List<TbPayApiRequest> tbPayApiRequestList = new ArrayList<>();
        for(TbPay tbPay : tbPays){
            TbPayApiRequest tbPayApiRequest = TbPayApiRequest.builder()
                    .id(tbPay.getId())
                    .payName(tbPay.getPayName())
                    .payHp(tbPay.getPayHp())
                    .payIscar(tbPay.getPayIscar())
                    .payHowpay(tbPay.getPayHowpay())
                    .payDate(tbPay.getPayDate())
                    .payMoney(tbPay.getPayMoney())
                    .payHostName(tbPay.getPayHostName())
                    .payRoomName(tbPay.getPayRoomName())
                    .payCheckIn(tbPay.getPayCheckIn())
                    .payCheckOut(tbPay.getPayCheckOut())
                    .payCancel(tbPay.getPayCancel())
                    .payCdate(tbPay.getPayCdate())
                    .tbHostId(tbPay.getTbHost().getId())
                    .tbMemId(tbPay.getTbMem().getId())
                    .tbRoomId(tbPay.getTbRoom().getId())
                    .build();
            tbPayApiRequestList.add(tbPayApiRequest);
        }
        return tbPayApiRequestList;
    }
    public List<TbPayApiRequest> full(Integer tbHostId, LocalDate payDate){
        List<TbPay> tbPayList = tbPayRepository.findAllByTbHostIdAndPayDate(tbHostId, payDate);
        List<TbPayApiRequest> tbPayApiRequestList = new ArrayList<>();

        for(TbPay tbPay : tbPayList){
            TbPayApiRequest tbPayApiRequest = TbPayApiRequest.builder()
                    .id(tbPay.getId())
                    .payName(tbPay.getPayName())
                    .payHp(tbPay.getPayHp())
                    .payIscar(tbPay.getPayIscar())
                    .payHowpay(tbPay.getPayHowpay())
                    .payDate(tbPay.getPayDate())
                    .payMoney(tbPay.getPayMoney())
                    .payHostName(tbPay.getPayHostName())
                    .payRoomName(tbPay.getPayRoomName())
                    .payCheckIn(tbPay.getPayCheckIn())
                    .payCheckOut(tbPay.getPayCheckOut())
                    .payCancel(tbPay.getPayCancel())
                    .payCdate(tbPay.getPayCdate())
                    .tbHostId(tbPay.getTbHost().getId())
                    .tbMemId(tbPay.getTbMem().getId())
                    .tbRoomId(tbPay.getTbRoom().getId())
                    .build();
            tbPayApiRequestList.add(tbPayApiRequest);
        }
        return tbPayApiRequestList;
    }

    // 예약리스트(user)
    public List<TbPayApiRequest> getPayUList(Integer id){
        List<TbPay> tbPays = tbPayRepository.findAllById(id);
        List<TbPayApiRequest> tbPayApiRequestList = new ArrayList<>();

        for(TbPay tbPay : tbPays){
            TbPayApiRequest tbPayApiRequest = TbPayApiRequest.builder()
                    .id(tbPay.getId())
                    .payName(tbPay.getPayName())
                    .payHp(tbPay.getPayHp())
                    .payIscar(tbPay.getPayIscar())
                    .payHowpay(tbPay.getPayHowpay())
                    .payDate(tbPay.getPayDate())
                    .payMoney(tbPay.getPayMoney())
                    .payHostName(tbPay.getPayHostName())
                    .payRoomName(tbPay.getPayRoomName())
                    .payCheckIn(tbPay.getPayCheckIn())
                    .payCheckOut(tbPay.getPayCheckOut())
                    .payCancel(tbPay.getPayCancel())
                    .payCdate(tbPay.getPayCdate())
                    .tbHostId(tbPay.getTbHost().getId())
                    .tbMemId(tbPay.getTbMem().getId())
                    .tbRoomId(tbPay.getTbRoom().getId())
                    .build();
            tbPayApiRequestList.add(tbPayApiRequest);
        }
        return tbPayApiRequestList;
    }
    //(host) 예약리스트 한개
    public List<TbPayApiResponse> mango(Integer tbHostId){
        List<TbPay> tbPays = tbPayRepository.findTopByTbHostId(tbHostId);
        List<TbPayApiResponse> tbPayApiResponseList = new ArrayList<>();

        for (TbPay tbPay : tbPays){
            TbPayApiResponse tbPayApiResponse = TbPayApiResponse.builder()
                    .id(tbPay.getId())
                    .payName(tbPay.getPayName())
                    .payHp(tbPay.getPayHp())
                    .payIscar(tbPay.getPayIscar())
                    .payHowpay(tbPay.getPayHowpay())
                    .payDate(tbPay.getPayDate())
                    .payMoney(tbPay.getPayMoney())
                    .payHostName(tbPay.getPayHostName())
                    .payRoomName(tbPay.getPayRoomName())
                    .payCheckIn(tbPay.getPayCheckIn())
                    .payCheckOut(tbPay.getPayCheckOut())
                    .payCancel(tbPay.getPayCancel())
                    .payCdate(tbPay.getPayCdate())
                    .tbHostId(tbPay.getTbHost().getId())
                    .tbMemId(tbPay.getTbMem().getId())
                    .tbRoomId(tbPay.getTbRoom().getId())
                    .build();
            tbPayApiResponseList.add(tbPayApiResponse);
        }
        return tbPayApiResponseList;
    }

    //예약취소
    public Header<TbPayApiResponse> cancel(Integer id, Header<TbPayApiRequest> request){
        TbPayApiRequest tbPayApiRequest = request.getData();
        Optional<TbPay> optional = baseRepository.findById(id);
        return optional.map(tbPay -> {
                    tbPay.setPayCancel("y");
                    tbPay.setPayCdate(LocalDate.now());
                    return tbPay;
                }).map(tbPay -> baseRepository.save(tbPay))
                .map(tbPay -> response(tbPay))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }

    //예약결제 디테일
    public Header<TbPayApiResponse> getPaydetail(Integer id){
        return tbPayRepository.findById(id)
                .map(tbPay -> response(tbPay))
                .map(Header::OK)
                .orElseGet(
                        ()-> Header.Error("ERROR")
                );
    }
    //예약결제 삭제
    public Header delpay(Integer id){
        Optional<TbPay> optional = baseRepository.findById(id);
        return optional.map(tbPay -> {
            baseRepository.delete(tbPay);
            return Header.OK();
        }).orElseGet(()->Header.Error("ERROR"));
    }

    //예약자 이름 수정
    public Header<TbPayApiResponse> upname(Header<TbPayApiRequest>request){
        TbPayApiRequest tbPayApiRequest = request.getData();
        Optional<TbPay> optional = baseRepository.findById(tbPayApiRequest.getId());
        return optional.map(tbPay -> {
                    tbPay.setPayName(tbPayApiRequest.getPayName());
                    return tbPay;
                }).map(tbPay -> baseRepository.save(tbPay))
                .map(tbPay -> response(tbPay))
                .map(Header::OK)
                .orElseGet(() -> Header.Error("ERROR"));
    }
    //예약자 번호변경
    public Header<TbPayApiResponse> uphp(Header<TbPayApiRequest>request){
        TbPayApiRequest tbPayApiRequest = request.getData();
        Optional<TbPay> optional = baseRepository.findById(tbPayApiRequest.getId());
        return optional.map(tbPay -> {
                    tbPay.setPayHp(tbPayApiRequest.getPayHp());
                    return tbPay;
                }).map(tbPay -> baseRepository.save(tbPay))
                .map(tbPay -> response(tbPay))
                .map(Header::OK)
                .orElseGet(() -> Header.Error("ERROR"));
    }

    public TbPayApiResponse response(TbPay tbPay){
        TbPayApiResponse tbPayApiResponse = TbPayApiResponse.builder()
                .id(tbPay.getId())
                .payName(tbPay.getPayName())
                .payHp(tbPay.getPayHp())
                .payIscar(tbPay.getPayIscar())
                .payHowpay(tbPay.getPayHowpay())
                .payDate(tbPay.getPayDate())
                .payMoney(tbPay.getPayMoney())
                .payHostName(tbPay.getPayHostName())
                .payRoomName(tbPay.getPayRoomName())
                .payCheckIn(tbPay.getPayCheckIn())
                .payCheckOut(tbPay.getPayCheckOut())
                .payCancel(tbPay.getPayCancel())
                .payCdate(tbPay.getPayCdate())
                .tbHostId(tbPay.getTbHost().getId())
                .tbRoomId(tbPay.getTbRoom().getId())
                .tbMemId(tbPay.getTbMem().getId())
                .build();
        return tbPayApiResponse;
    }

    @Override
    public Header<TbPayApiResponse> create(Header<TbPayApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbPayApiResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbPayApiResponse> update(Header<TbPayApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbPayApiResponse> delete(Integer id) {
        return null;
    }
}