package com.example.yanolza.service;

import com.example.yanolza.model.entity.*;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbMemApiRequest;
import com.example.yanolza.model.network.response.*;
import com.example.yanolza.repository.TbMemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TbMemApiService extends BaseService<TbMemApiRequest, TbMemApiResponse, TbMem> {

    @Autowired
    private TbReviewApiService tbReviewApiService;
    @Autowired
    private TbPayApiService tbPayApiService;
    @Autowired
    private TbZzimApiService tbZzimApiService;
    @Autowired
    private TbHostApiService tbHostApiService;
    @Autowired
    private TbMemRepository tbMemRepository;

    //회원가입
    public Header<TbMemApiResponse> regist(Header<TbMemApiRequest> request){
        TbMemApiRequest tbMemApiRequest = request.getData();
        TbMem tbMem = TbMem.builder()
                .memHp(tbMemApiRequest.getMemHp())
                .memEmail(tbMemApiRequest.getMemEmail())
                .memPw(tbMemApiRequest.getMemPw())
                .memNkname(tbMemApiRequest.getMemNkname())
                .memPoint(tbMemApiRequest.getMemPoint())
                .memRegdate(tbMemApiRequest.getMemRegdate())
                .build();
        TbMem newMem = baseRepository.save(tbMem);
        return Header.OK(response(newMem));
    }
    //회원 리스트(admin)
    public List<TbMemApiRequest> getMemList(){
        List<TbMem> tbMems = baseRepository.findAll();
        List<TbMemApiRequest> tbMemApiRequestList = new ArrayList<>();

        for(TbMem tbMem : tbMems){
            TbMemApiRequest tbMemApiRequest = TbMemApiRequest.builder()
                    .id(tbMem.getId())
                    .memHp(tbMem.getMemHp())
                    .memEmail(tbMem.getMemEmail())
                    .memPw(tbMem.getMemPw())
                    .memNkname(tbMem.getMemNkname())
                    .memPoint(tbMem.getMemPoint())
                    .memRegdate(tbMem.getMemRegdate())
                    .build();
            tbMemApiRequestList.add(tbMemApiRequest);
        }
        return tbMemApiRequestList;
    }

    //세션
    public Integer session(String memEmail){
        TbMem tbMem = tbMemRepository.getByMemEmail(memEmail);
        return tbMem.getId();
    }

    // 닉네임 불러오기
    public String nik(Integer id){
        TbMem tbMem = tbMemRepository.getById(id);
        return tbMem.getMemNkname();
    }

    // 전화번호 불러오기
    public String getHp(Integer id){
        TbMem tbMem = tbMemRepository.getById(id);
        return tbMem.getMemHp();
    }

    //회원 리스트 디테일(admin)
    public Header<TbMemApiResponse> mdetail(Integer id){
        return baseRepository.findById(id)
                .map(tbMem -> response(tbMem))
                .map(Header::OK)
                .orElseGet(
                        ()-> Header.Error("데이터 없음!")
                );
    }

    // 회원 정보삭제
    public Header memdel(Integer id){
        Optional<TbMem> optional = baseRepository.findById(id);
        return optional.map(tbMem -> {
            baseRepository.delete(tbMem);
            return Header.OK();
        }).orElseGet(()-> Header.Error("데이터 없음!"));
    }

    // 닉네임 수정(mypage (user))
    public Header<TbMemApiResponse> nkupdate(Integer id, Header<TbMemApiRequest> request){
        TbMemApiRequest tbMemApiRequest = request.getData();
        Optional<TbMem> optional = baseRepository.findById(id);
        return optional.map(tbMem -> {
            tbMem.setMemNkname(tbMemApiRequest.getMemNkname());
            return tbMem;
        }).map(tbMem -> baseRepository.save(tbMem))
                .map(tbMem -> response(tbMem))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }

    // 휴대폰 번호 수정(mypage (user))
    public Header<TbMemApiResponse> hpupdate(Integer id, Header<TbMemApiRequest> request){
        TbMemApiRequest tbMemApiRequest = request.getData();
        Optional<TbMem> optional = baseRepository.findById(id);
        return optional.map(tbMem -> {
                    tbMem.setMemHp(tbMemApiRequest.getMemHp());
                    return tbMem;
                }).map(tbMem -> baseRepository.save(tbMem))
                .map(tbMem -> response(tbMem))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }

    //개인 예약 현황
    public Header<TbmemTbpayApiResoponse> getreser(Integer id){
        TbMem tbMem = baseRepository.getById(id);
        TbMemApiResponse tbMemApiResponse = response(tbMem);

        List<TbPay> tbPayList = tbMem.getTbPayList();
        List<TbPayApiResponse> tbPayApiResponseList = tbPayList.stream()
                .map(tbPay -> {
                    TbPayApiResponse tbPayApiResponse = tbPayApiService.response(tbPay);
                    return tbPayApiResponse;
                })
                .collect(Collectors.toList());
        tbMemApiResponse.setTbPayApiResponseList(tbPayApiResponseList);
        TbmemTbpayApiResoponse tbmemTbpayApiResoponse = TbmemTbpayApiResoponse.builder()
                .tbMemApiResponse(tbMemApiResponse)
                .build();
        return Header.OK(tbmemTbpayApiResoponse);
    }


    // 개인 찜 리스트
    public Header<TbMemTbZzimApiResponse> getZzimu(Integer id){
        TbMem tbMem = baseRepository.getById(id);
        TbMemApiResponse tbMemApiResponse = response(tbMem);

        List<TbZzim> tbZzimList = tbMem.getTbZzimList();
        List<TbZzimApiResponse> tbZzimApiResponseList = tbZzimList.stream()
                .map(tbZzim -> {
                    TbZzimApiResponse tbZzimApiResponse = tbZzimApiService.response(tbZzim);
                    return tbZzimApiResponse;
                })
                .collect(Collectors.toList());
        tbMemApiResponse.setTbZzimApiResponseList(tbZzimApiResponseList);
        TbMemTbZzimApiResponse tbMemTbZzimApiResponse = TbMemTbZzimApiResponse.builder()
                .tbMemApiResponse(tbMemApiResponse)
                .build();
        return Header.OK(tbMemTbZzimApiResponse);
    }

    // 호스트 입장시 필요
    public Header<TbMemTbHostApiResponse> getHhost(Integer id){
        TbMem tbMem = baseRepository.getById(id);
        TbMemApiResponse tbMemApiResponse = response(tbMem);

        List<TbHost> tbHostList = tbMem.getTbHostList();
        List<TbHostApiResponse> tbHostApiResponseList = tbHostList.stream()
                .map(tbHost -> {
                    TbHostApiResponse tbHostApiResponse = tbHostApiService.response(tbHost);
                    return tbHostApiResponse;
                })
                .collect(Collectors.toList());
        tbMemApiResponse.setTbHostApiResponseList(tbHostApiResponseList);
        TbMemTbHostApiResponse tbMemTbHostApiResponse = TbMemTbHostApiResponse.builder()
                .tbMemApiResponse(tbMemApiResponse)
                .build();
        return Header.OK(tbMemTbHostApiResponse);
    }

    public TbMemApiResponse response(TbMem tbMem){
        TbMemApiResponse tbMemApiResponse = TbMemApiResponse.builder()
                .id(tbMem.getId())
                .memHp(tbMem.getMemHp())
                .memEmail(tbMem.getMemEmail())
                .memPw(tbMem.getMemPw())
                .memPw(tbMem.getMemPw())
                .memNkname(tbMem.getMemNkname())
                .memRegdate(tbMem.getMemRegdate())
                .memPoint(tbMem.getMemPoint())
                .build();
        return tbMemApiResponse;
    }

    // 비밀번호 재설정 관련 로직
    public List<TbMemApiRequest> getph(String memEmail){
        List<TbMem> tbMems =  tbMemRepository.getAllByMemEmail(memEmail);
        List<TbMemApiRequest> tbMemApiRequestList = new ArrayList<>();

        for (TbMem tbMem : tbMems){
            TbMemApiRequest tbMemApiRequest = TbMemApiRequest.builder()
                    .memHp(tbMem.getMemHp())
                    .build();
            tbMemApiRequestList.add(tbMemApiRequest);
        }
        return  tbMemApiRequestList;
    }

    //비밀번호 재설정 토큰값 세팅
    public void updateResetPasswordToken(String token, String memEmail) {
        TbMem tbMem = tbMemRepository.getByMemEmail(memEmail);
        if(tbMem != null){  // 해당 이메일인 tbMem 회원이 있으면
            tbMem.setResetPasswordToken(token); // 토큰 값을 넣어준다.
            tbMemRepository.save(tbMem); // 그리고 저장
        } else {
            try { //예외 발생시키기 msg랑.
                throw new TbMemNotFoundException("해당 이메일과 일치하는 회원 없음");
            } catch (TbMemNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //비밀전호 재설정
    public TbMem getByResetPasswordToken(String token) {
        return tbMemRepository.getByResetPasswordToken(token); //토큰 가져오기
    }

    //비밀번호 reset 로직
    public void updatePassword(TbMem tbMem, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        tbMem.setMemPw(encodedPassword);

        tbMem.setResetPasswordToken(null);
        tbMemRepository.save(tbMem);
    }

    @Override
    public Header<TbMemApiResponse> create(Header<TbMemApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbMemApiResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbMemApiResponse> update(Header<TbMemApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbMemApiResponse> delete(Integer id) {
        return null;
    }
}




































//        TbMem tbMem = TbMem.builder()
//                .memHp(tbMemApiRequest.getMemHp())
//                .memEmail(tbMemApiRequest.getMemEmail())
//                .memPw(tbMemApiRequest.getMemPw())
//                .memNkname(tbMemApiRequest.getMemNkname())
//                .memZzim(tbMemApiRequest.getMemZzim())
//                .memPoint(tbMemApiRequest.getMemPoint())
//                .memSns(tbMemApiRequest.getMemSns())
//                .memMemo(tbMemApiRequest.getMemMemo())
//                .memIshost(tbMemApiRequest.getMemIshost())
//                .memIsout(tbMemApiRequest.getMemIsout())
//                .memReason(tbMemApiRequest.getMemReason())
//                .build();
//        TbMem newMem = baseRepository.save(tbMem);
//        return Header.OK(response(newMem));
//    }
//
//    @Override
//    public Header<TbMemApiResponse> read(Integer id) {
//        return baseRepository.findById(id)
//                .map(tbMem -> response(tbMem))
//                .map(Header::OK)
//                .orElseGet(
//                        ()-> Header.Error("데이터 못찾음;;")
//                );
//    }
//
//    @Override
//    public Header<TbMemApiResponse> update(Header<TbMemApiRequest> request) {
//        TbMemApiRequest tbMemApiRequest = request.getData();
//        System.out.println(tbMemApiRequest.getId());
//        Optional<TbMem> optional = baseRepository.findById(tbMemApiRequest.getId());
//        return optional.map(tbMem -> {
//            tbMem.setMemHp(tbMemApiRequest.getMemHp());
//            tbMem.setMemEmail(tbMemApiRequest.getMemEmail());
//            tbMem.setMemPw(tbMemApiRequest.getMemPw());
//            tbMem.setMemNkname(tbMemApiRequest.getMemNkname());
//            tbMem.setMemZzim(tbMemApiRequest.getMemZzim());
//            tbMem.setMemPoint(tbMemApiRequest.getMemPoint());
//            tbMem.setMemSns(tbMemApiRequest.getMemSns());
//            tbMem.setMemIshost(tbMemApiRequest.getMemIshost());
//            tbMem.setMemIsout(tbMemApiRequest.getMemIsout());
//            tbMem.setMemReason(tbMemApiRequest.getMemReason());
//            return tbMem;
//        }).map(tbMem -> baseRepository.save(tbMem))
//                .map(tbMem -> response(tbMem))
//                .map(Header::OK)
//                .orElseGet(()->Header.Error("놉"));
//    }
//
//    @Override
//    public Header delete(Integer id) {
//        Optional<TbMem> optional = baseRepository.findById(id);
//        return optional.map(tbMem -> {
//            baseRepository.delete(tbMem);
//            return Header.OK();
//        }).orElseGet(() -> Header.Error("데이터없음"));
//    }
//
//    private TbMemApiResponse response(TbMem tbMem){
//        TbMemApiResponse tbMemApiResponse = TbMemApiResponse.builder()
//                .memHp(tbMem.getMemHp())
//                .memEmail(tbMem.getMemEmail())
//                .memPw(tbMem.getMemPw())
//                .memNkname(tbMem.getMemNkname())
//                .memZzim(tbMem.getMemZzim())
//                .memPoint(tbMem.getMemPoint())
//                .memSns(tbMem.getMemSns())
//                .memMemo(tbMem.getMemMemo())
//                .memIshost(tbMem.getMemIshost())
//                .memIsout(tbMem.getMemIsout())
//                .memReason(tbMem.getMemReason())
//                .build();
//        return tbMemApiResponse;
//    }