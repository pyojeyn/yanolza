package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbHost;
import com.example.yanolza.model.entity.TbHostImg;
import com.example.yanolza.model.entity.TbPay;
import com.example.yanolza.model.entity.TbRoom;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbHostApiRequest;
import com.example.yanolza.model.network.response.*;
import com.example.yanolza.repository.TbHostImgRepository;
import com.example.yanolza.repository.TbHostRepository;
import com.example.yanolza.repository.TbMemRepository;
import com.example.yanolza.repository.TbPayRepository;
import com.example.yanolza.service.img.TbHostImgService;
import com.example.yanolza.service.img.TbRoomImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TbHostApiService extends BaseService<TbHostApiRequest, TbHostApiResponse, TbHost> {
    @Autowired
    private final TbHostRepository tbHostRepository;
    @Autowired
    private final TbMemRepository tbMemRepository;
    @Autowired
    private final TbRoomApiService tbRoomApiService;
    @Autowired
    private final TbPayApiService tbPayApiService;
    @Autowired
    private final TbReviewApiService tbReviewApiService;
    @Autowired
    private final TbHostImgService tbHostImgService;
    @Autowired
    private final TbRoomImgService tbRoomImgService;

    //호스트 등록
    public Header<TbHostApiResponse> registh(Header<TbHostApiRequest> request) {
        TbHostApiRequest tbHostApiRequest = request.getData();
        TbHost tbHost = TbHost.builder()
                .myHp(tbHostApiRequest.getMyHp())
                .almName(tbHostApiRequest.getAlmName())
                .almHp(tbHostApiRequest.getAlmHp())
                .almWeek(tbHostApiRequest.getAlmWeek())
                .almStime(tbHostApiRequest.getAlmStime())
                .almCtime(tbHostApiRequest.getAlmCtime())
                .myCorporate(tbHostApiRequest.getMyCorporate())
                .myConame(tbHostApiRequest.getMyConame())
                .myCondition(tbHostApiRequest.getMyCondition())
                .myType(tbHostApiRequest.getMyType())
                .myConum(tbHostApiRequest.getMyConum())
                .myAddr1(tbHostApiRequest.getMyAddr1())
                .myAddr2(tbHostApiRequest.getMyAddr2())
                .myEmail(tbHostApiRequest.getMyEmail())
// -----------------------------------------------------------------------
                .myIntcom(tbHostApiRequest.getMyIntcom())
                .myIntcomadd1(tbHostApiRequest.getMyIntcomadd1())
                .myIntcomadd2(tbHostApiRequest.getMyIntcomadd2())
                .latitude(tbHostApiRequest.getLatitude())
                .longitude(tbHostApiRequest.getLongitude())
                .myService(tbHostApiRequest.getMyService())
                .myIntro(tbHostApiRequest.getMyIntro())
                .myComeway(tbHostApiRequest.getMyComeway())
                .mySidein1(tbHostApiRequest.getMySidein1())
                .mySidein2(tbHostApiRequest.getMySidein2())
                .mySidein3(tbHostApiRequest.getMySidein3())
                .myRmrule(tbHostApiRequest.getMyRmrule())
                .myOneline(tbHostApiRequest.getMyOneline())
                .myPcash(tbHostApiRequest.getMyPcash())
                .myParking(tbHostApiRequest.getMyParking())
                .myKakunin(tbHostApiRequest.getMyKakunin())
// -----------------------------------------------------------------------
                .myRetime(tbHostApiRequest.getMyRetime())
                .pkSsdate(tbHostApiRequest.getPkSsdate())
                .pkCcdate(tbHostApiRequest.getPkCcdate())
                .pkSdate(tbHostApiRequest.getPkSdate())
                .pkCdate(tbHostApiRequest.getPkCdate())
                .pkAdmit("n")
                .tbMem(tbMemRepository.getById(tbHostApiRequest.getTbMemId()))
                .build();
        TbHost newHost = baseRepository.save(tbHost);
        return Header.OK(response(newHost));
    }

    // 호스트 리스트(admin)
    public List<TbHostApiRequest> gethlist(){
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmit("y");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();

        for (TbHost tbHost  : tbHosts){
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }

    //호스트 리스트 디테일(admin)
    public Header<TbHostApiResponse> gethostdetail(Integer id){
        return tbHostRepository.findByPkAdmitAndId("y", id)
                .map(tbHost -> response(tbHost))
                .map(Header::OK)
                .orElseGet(
                        ()-> Header.Error("데이터 없음!")
                );
    }

    public String getHP(Integer id){
        TbHost tbHost = tbHostRepository.getById(id);
        return tbHost.getAlmHp();

    }

    // 호스트 등록 글 수정 1
    public Header<TbHostApiResponse> updateh1(Integer id, Header<TbHostApiRequest> request){
        TbHostApiRequest tbHostApiRequest = request.getData();
        Optional<TbHost> optional = baseRepository.findById(id);
        return optional.map(tbHost -> {
            tbHost.setMyHp(tbHostApiRequest.getMyHp());
            tbHost.setAlmName(tbHostApiRequest.getAlmName());
            tbHost.setAlmHp(tbHostApiRequest.getAlmHp());
            tbHost.setAlmWeek(tbHostApiRequest.getAlmWeek());
            tbHost.setAlmStime(tbHostApiRequest.getAlmStime());
            tbHost.setAlmCtime(tbHostApiRequest.getAlmCtime());
            tbHost.setMyCorporate(tbHostApiRequest.getMyCorporate());
            tbHost.setMyConame(tbHostApiRequest.getMyConame());
            tbHost.setMyCondition(tbHostApiRequest.getMyCondition());
            tbHost.setMyType(tbHostApiRequest.getMyType());
            tbHost.setMyConum(tbHostApiRequest.getMyConum());
            tbHost.setMyAddr1(tbHostApiRequest.getMyAddr1());
            tbHost.setMyAddr2(tbHostApiRequest.getMyAddr2());
            tbHost.setMyEmail(tbHostApiRequest.getMyEmail());
            return tbHost;
        }).map(tbHost -> baseRepository.save(tbHost))
                .map(tbHost -> response(tbHost))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }

    // 호스트 등록 글 수정 2
    public Header<TbHostApiResponse> updateh2(Integer id, Header<TbHostApiRequest> request){
        TbHostApiRequest tbHostApiRequest = request.getData();
        Optional<TbHost> optional = baseRepository.findById(id);
        return optional.map(tbHost -> {
                    tbHost.setMyIntcom(tbHostApiRequest.getMyIntcom());
                    tbHost.setMyIntcomadd1(tbHostApiRequest.getMyIntcomadd1());
                    tbHost.setMyIntcomadd2(tbHostApiRequest.getMyIntcomadd2());
                    tbHost.setLatitude(tbHostApiRequest.getLatitude());
                    tbHost.setLongitude(tbHostApiRequest.getLongitude());
                    tbHost.setMyService(tbHostApiRequest.getMyService());
                    tbHost.setMyIntro(tbHostApiRequest.getMyIntro());
                    tbHost.setMyComeway(tbHostApiRequest.getMyComeway());
                    tbHost.setMySidein1(tbHostApiRequest.getMySidein1());
                    tbHost.setMySidein2(tbHostApiRequest.getMySidein2());
                    tbHost.setMySidein3(tbHostApiRequest.getMySidein3());
                    tbHost.setMyRmrule(tbHostApiRequest.getMyRmrule());
                    tbHost.setMyOneline(tbHostApiRequest.getMyOneline());
                    tbHost.setMyPcash(tbHostApiRequest.getMyPcash());
                    tbHost.setMyParking(tbHostApiRequest.getMyParking());
                    tbHost.setMyKakunin(tbHostApiRequest.getMyKakunin());
                    return tbHost;
                }).map(tbHost -> baseRepository.save(tbHost))
                .map(tbHost -> response(tbHost))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }
    // 호스트 등록 글 수정 3
    public Header<TbHostApiResponse> updateh3(Integer id, Header<TbHostApiRequest> request){
        TbHostApiRequest tbHostApiRequest = request.getData();
        Optional<TbHost> optional = baseRepository.findById(id);
        return optional.map(tbHost -> {
                    tbHost.setMyRetime(tbHostApiRequest.getMyRetime());
                    tbHost.setPkSsdate(tbHostApiRequest.getPkSsdate());
                    tbHost.setPkCcdate(tbHostApiRequest.getPkCcdate());
                    tbHost.setPkSdate(tbHostApiRequest.getPkSdate());
                    tbHost.setPkCdate(tbHostApiRequest.getPkCdate());
                    return tbHost;
                }).map(tbHost -> baseRepository.save(tbHost))
                .map(tbHost -> response(tbHost))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }

    //숙소신청허가
    public Header<TbHostApiResponse> admith(Integer id,Header<TbHostApiRequest> request) {
        TbHostApiRequest tbHostApiRequest = request.getData();
        Optional<TbHost> optional = baseRepository.findById(id);
        return optional.map(tbHost -> {
                    tbHost.setPkAdmit("y");
                    return tbHost;
                }).map(tbHost -> baseRepository.save(tbHost))
                .map(tbHost -> response(tbHost))
                .map(Header::OK)
                .orElseGet(() -> Header.Error("ERROR"));
    }

    // 숙소신청한 호스트 리스트
    public List<TbHostApiRequest> getrhlist(){
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmit("n");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
        for (TbHost tbHost  : tbHosts){
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSsdate(tbHost.getPkSsdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }
    //숙소신청한 리스트 디테일(admin)
    public Header<TbHostApiResponse> getrhostdetail( Integer id){
        return tbHostRepository.findByPkAdmitAndId("n", id)
                .map(tbHost -> response(tbHost))
                .map(Header::OK)
                .orElseGet(
                        ()-> Header.Error("ERROR")
                );
    }

    // 숙소신청한 호스트 삭제
    public Header rhostdel(Integer id){
        Optional<TbHost> optional = baseRepository.findById(id);
        return optional.map(tbHost -> {
            baseRepository.delete(tbHost);
            return Header.OK();
        }).orElseGet(()->Header.Error("ERROR"));
    }

    // 호스트의 객실정보
    public Header<TbHostTbRoomApiResponse> hostRoom(Integer id){
        TbHost tbHost = baseRepository.getById(id);
        TbHostApiResponse tbHostApiResponse = response(tbHost);

        List<TbRoom> tbRoomList = tbHost.getTbRoomList();
        List<TbRoomApiResponse> tbRoomApiResponseList = tbRoomList.stream()
                .map(tbRoom -> {
                    TbRoomApiResponse tbRoomApiResponse = tbRoomApiService.response(tbRoom);
                    return tbRoomApiResponse;
                })
                .collect(Collectors.toList());
        tbHostApiResponse.setTbRoomApiResponseList(tbRoomApiResponseList);
        TbHostTbRoomApiResponse tbHostTbRoomApiResponse = TbHostTbRoomApiResponse.builder()
                .tbHostApiResponse(tbHostApiResponse)
                .build();
        return Header.OK(tbHostTbRoomApiResponse);
    }

    // 호스트의 객실을 예약한 예약정보
    public Header<TbHostTbRoomApiResponse> hostRoompay(Integer id){
        TbHost tbHost = baseRepository.getById(id);
        TbHostApiResponse tbHostApiResponse = response(tbHost);

        List<TbRoom> tbRoomList = tbHost.getTbRoomList();
        List<TbRoomApiResponse> tbRoomApiResponseList = tbRoomList.stream()
                .map(tbRoom -> {
                    TbRoomApiResponse tbRoomApiResponse = tbRoomApiService.response(tbRoom);
                    List<TbPayApiResponse> tbPayApiResponseList = tbRoom.getTbPayList().stream()

                            .map(tbPay -> tbPayApiService.response(tbPay))
                            .collect(Collectors.toList());
                    tbRoomApiResponse.setTbPayApiResponseList(tbPayApiResponseList);
                    return tbRoomApiResponse;
                })
                .collect(Collectors.toList());

        tbHostApiResponse.setTbRoomApiResponseList(tbRoomApiResponseList);
        TbHostTbRoomApiResponse tbHostTbRoomApiResponse = TbHostTbRoomApiResponse.builder()
                .tbHostApiResponse(tbHostApiResponse)
                .build();
        return Header.OK(tbHostTbRoomApiResponse);
    }

    // 호스트의 객실의 리뷰
    public Header<TbHostTbRoomApiResponse> hostRoomreview(Integer id){
        TbHost tbHost = baseRepository.getById(id);
        TbHostApiResponse tbHostApiResponse = response(tbHost);

        List<TbHostImg> tbHostImgs = tbHost.getTbHostImgList();
        List<TbHostImgResponse> tbHostImgResponseList = tbHostImgs.stream()
                .map(tbHostImg -> {
                    TbHostImgResponse tbHostImgResponse = tbHostImgService.response(tbHostImg);
                    return tbHostImgResponse;
                })
                .collect(Collectors.toList());

        List<TbRoom> tbRoomList = tbHost.getTbRoomList();
        List<TbRoomApiResponse> tbRoomApiResponseList = tbRoomList.stream()

                .map(tbRoom -> {
                    TbRoomApiResponse tbRoomApiResponse = tbRoomApiService.response(tbRoom);
                    List<TbRoomImgResponse> tbRoomImgResponseList = tbRoom.getTbRoomImgList().stream()
                            .map(tbroomimg-> tbRoomImgService.response(tbroomimg))
                            .collect(Collectors.toList());
                    tbRoomApiResponse.setTbRoomImgResponseList(tbRoomImgResponseList);

                    List<TbReviewApiResponse> tbReviewApiResponseList = tbRoom.getTbReviewList().stream()

                            .map(tbReview -> tbReviewApiService.response(tbReview))
                            .collect(Collectors.toList());
                    tbRoomApiResponse.setTbReviewApiResponseList(tbReviewApiResponseList);
                    return tbRoomApiResponse;
                })
                .collect(Collectors.toList());
        tbHostApiResponse.setTbRoomApiResponseList(tbRoomApiResponseList);
        tbHostApiResponse.setTbHostImgResponseList(tbHostImgResponseList);
        TbHostTbRoomApiResponse tbHostTbRoomApiResponse = TbHostTbRoomApiResponse.builder()
                .tbHostApiResponse(tbHostApiResponse)
                .build();
        return Header.OK(tbHostTbRoomApiResponse);
    }

    //호스트 이미지
    public Header<TbHostImgUserApiResponse> HostImg (Integer id){
        TbHost tbHost = baseRepository.getById(id);
        TbHostApiResponse tbHostApiResponse = response(tbHost);

        List<TbHostImg> tbHostImgList = tbHost.getTbHostImgList();
        List<TbHostImgResponse> tbHostImgResponseList = tbHostImgList.stream()
                .map(tbHostImg -> {
                    TbHostImgResponse tbHostImgResponse = tbHostImgService.response(tbHostImg);
                    return tbHostImgResponse;
                })
                .collect(Collectors.toList());
        tbHostApiResponse.setTbHostImgResponseList(tbHostImgResponseList);
        TbHostImgUserApiResponse tbHostImgUserApiResponse = TbHostImgUserApiResponse.builder()
                .tbHostApiResponse(tbHostApiResponse)
                .build();
        return Header.OK(tbHostImgUserApiResponse);
    }

    //모텔리스트 (user)
    public List<TbHostApiRequest> motelList() {
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmitAndMyType("y","모텔");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
        for (TbHost tbHost : tbHosts) {
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSsdate(tbHost.getPkSsdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }

    // 호텔리스트 (user)
    public List<TbHostApiRequest> hotelList() {
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmitAndMyType("y","호텔");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
        for (TbHost tbHost : tbHosts) {
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSsdate(tbHost.getPkSsdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }
    // 펜션리스트 (user)
    public List<TbHostApiRequest> panList() {
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmitAndMyType("y","펜션");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
        for (TbHost tbHost : tbHosts) {
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSsdate(tbHost.getPkSsdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }

    // 리조트리스트 (user)
    public List<TbHostApiRequest> reList() {
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmitAndMyType("y","리조트");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
        for (TbHost tbHost : tbHosts) {
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSsdate(tbHost.getPkSsdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }

    // 게스트하우스 리스트 (user)
    public List<TbHostApiRequest> guesthouselList() {
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmitAndMyType("y", "게스트하우스");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
        for (TbHost tbHost : tbHosts) {
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSsdate(tbHost.getPkSsdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }
        //캠핑하우스 리스트 (user)
        public List<TbHostApiRequest> camList() {
            List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmitAndMyType("y","캠핑");
            List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
            for (TbHost tbHost : tbHosts) {
                TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                        .id(tbHost.getId())
                        .myHp(tbHost.getMyHp())
                        .almName(tbHost.getAlmName())
                        .almHp(tbHost.getAlmHp())
                        .almWeek(tbHost.getAlmWeek())
                        .almStime(tbHost.getAlmStime())
                        .almCtime(tbHost.getAlmCtime())
                        .myCorporate(tbHost.getMyCorporate())
                        .myConame(tbHost.getMyConame())
                        .myCondition(tbHost.getMyCondition())
                        .myType(tbHost.getMyType())
                        .myConum(tbHost.getMyConum())
                        .myAddr1(tbHost.getMyAddr1())
                        .myAddr2(tbHost.getMyAddr2())
                        .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                        .myIntcom(tbHost.getMyIntcom())
                        .myIntcomadd1(tbHost.getMyIntcomadd1())
                        .myIntcomadd2(tbHost.getMyIntcomadd2())
                        .latitude(tbHost.getLatitude())
                        .longitude(tbHost.getLongitude())
                        .myService(tbHost.getMyService())
                        .myIntro(tbHost.getMyIntro())
                        .myComeway(tbHost.getMyComeway())
                        .mySidein1(tbHost.getMySidein1())
                        .mySidein2(tbHost.getMySidein2())
                        .mySidein3(tbHost.getMySidein3())
                        .myRmrule(tbHost.getMyRmrule())
                        .myOneline(tbHost.getMyOneline())
                        .myPcash(tbHost.getMyPcash())
                        .myParking(tbHost.getMyParking())
                        .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                        .myRetime(tbHost.getMyRetime())
                        .pkSsdate(tbHost.getPkSsdate())
                        .pkCcdate(tbHost.getPkCcdate())
                        .pkSdate(tbHost.getPkSdate())
                        .pkCdate(tbHost.getPkCdate())
                        .pkAdmit(tbHost.getPkAdmit())
                        .tbMemId(tbHost.getTbMem().getId())
                        .build();
                tbHostApiRequestList.add(tbHostApiRequest);
            }
        return tbHostApiRequestList;
    }

    //한옥하우스 리스트 (user)
    public List<TbHostApiRequest> hanList() {
        List<TbHost> tbHosts = tbHostRepository.findAllByPkAdmitAndMyType("y","한옥");
        List<TbHostApiRequest> tbHostApiRequestList = new ArrayList<>();
        for (TbHost tbHost : tbHosts) {
            TbHostApiRequest tbHostApiRequest = TbHostApiRequest.builder()
                    .id(tbHost.getId())
                    .myHp(tbHost.getMyHp())
                    .almName(tbHost.getAlmName())
                    .almHp(tbHost.getAlmHp())
                    .almWeek(tbHost.getAlmWeek())
                    .almStime(tbHost.getAlmStime())
                    .almCtime(tbHost.getAlmCtime())
                    .myCorporate(tbHost.getMyCorporate())
                    .myConame(tbHost.getMyConame())
                    .myCondition(tbHost.getMyCondition())
                    .myType(tbHost.getMyType())
                    .myConum(tbHost.getMyConum())
                    .myAddr1(tbHost.getMyAddr1())
                    .myAddr2(tbHost.getMyAddr2())
                    .myEmail(tbHost.getMyEmail())
// -----------------------------------------------------------------------
                    .myIntcom(tbHost.getMyIntcom())
                    .myIntcomadd1(tbHost.getMyIntcomadd1())
                    .myIntcomadd2(tbHost.getMyIntcomadd2())
                    .latitude(tbHost.getLatitude())
                    .longitude(tbHost.getLongitude())
                    .myService(tbHost.getMyService())
                    .myIntro(tbHost.getMyIntro())
                    .myComeway(tbHost.getMyComeway())
                    .mySidein1(tbHost.getMySidein1())
                    .mySidein2(tbHost.getMySidein2())
                    .mySidein3(tbHost.getMySidein3())
                    .myRmrule(tbHost.getMyRmrule())
                    .myOneline(tbHost.getMyOneline())
                    .myPcash(tbHost.getMyPcash())
                    .myParking(tbHost.getMyParking())
                    .myKakunin(tbHost.getMyKakunin())
// -----------------------------------------------------------------------
                    .myRetime(tbHost.getMyRetime())
                    .pkSsdate(tbHost.getPkSsdate())
                    .pkCcdate(tbHost.getPkCcdate())
                    .pkSdate(tbHost.getPkSdate())
                    .pkCdate(tbHost.getPkCdate())
                    .pkAdmit(tbHost.getPkAdmit())
                    .tbMemId(tbHost.getTbMem().getId())
                    .build();
            tbHostApiRequestList.add(tbHostApiRequest);
        }
        return tbHostApiRequestList;
    }

    public TbHostApiResponse response(TbHost tbHost) {
        TbHostApiResponse tbHostApiResponse = TbHostApiResponse.builder()
                .id(tbHost.getId())
                .myHp(tbHost.getMyHp())
                .almName(tbHost.getAlmName())
                .almHp(tbHost.getAlmHp())
                .almWeek(tbHost.getAlmWeek())
                .almStime(tbHost.getAlmStime())
                .almCtime(tbHost.getAlmCtime())
                .myCorporate(tbHost.getMyCorporate())
                .myConame(tbHost.getMyConame())
                .myCondition(tbHost.getMyCondition())
                .myType(tbHost.getMyType())
                .myConum(tbHost.getMyConum())
                .myAddr1(tbHost.getMyAddr1())
                .myAddr2(tbHost.getMyAddr2())
                .myEmail(tbHost.getMyEmail())
                .myIntcom(tbHost.getMyIntcom())
                .myIntcomadd1(tbHost.getMyIntcomadd1())
                .myIntcomadd2(tbHost.getMyIntcomadd2())
                .latitude(tbHost.getLatitude())
                .longitude(tbHost.getLongitude())
                .myService(tbHost.getMyService())
                .myIntro(tbHost.getMyIntro())
                .myComeway(tbHost.getMyComeway())
                .mySidein1(tbHost.getMySidein1())
                .mySidein2(tbHost.getMySidein2())
                .mySidein3(tbHost.getMySidein3())
                .myRmrule(tbHost.getMyRmrule())
                .myOneline(tbHost.getMyOneline())
                .myPcash(tbHost.getMyPcash())
                .myParking(tbHost.getMyParking())
                .myKakunin(tbHost.getMyKakunin())
                .myRetime(tbHost.getMyRetime())
                .pkSsdate(tbHost.getPkSdate())
                .pkCcdate(tbHost.getPkCcdate())
                .pkSdate(tbHost.getPkSdate())
                .pkCdate(tbHost.getPkCdate())
                .pkAdmit(tbHost.getPkAdmit())
                .tbMemId(tbHost.getTbMem().getId())
                .build();
        return tbHostApiResponse;
    }

    @Override
    public Header<TbHostApiResponse> create(Header<TbHostApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbHostApiResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbHostApiResponse> update(Header<TbHostApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbHostApiResponse> delete(Integer id) {
        return null;
    }
}

