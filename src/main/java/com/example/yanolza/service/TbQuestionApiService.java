package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbQuestion;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbQuestionApiRequest;
import com.example.yanolza.model.network.response.TbQuestionApiResponse;
import com.example.yanolza.repository.TbMemRepository;
import com.example.yanolza.repository.TbQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TbQuestionApiService extends BaseService<TbQuestionApiRequest, TbQuestionApiResponse, TbQuestion>{
    @Autowired
    private final TbQuestionRepository tbQuestionRepository;
    @Autowired
    private final TbMemRepository tbMemRepository;
    //문의등록
    public Header<TbQuestionApiResponse> regi(Header<TbQuestionApiRequest> request){
        TbQuestionApiRequest tbQuestionApiRequest = request.getData();
        TbQuestion tbQuestion = TbQuestion.builder()
                .qCategory(tbQuestionApiRequest.getQCategory())
                .qType(tbQuestionApiRequest.getQType())
                .qName(tbQuestionApiRequest.getQName())
                .qHp(tbQuestionApiRequest.getQHp())
                .qEmail(tbQuestionApiRequest.getQEmail())
                .qContent(tbQuestionApiRequest.getQContent())
                .qAnswer(tbQuestionApiRequest.getQAnswer())
                .qIscheck("n")
                .tbMem(tbMemRepository.getById(tbQuestionApiRequest.getTbMemId()))
                .build();
        TbQuestion newQuestion = baseRepository.save(tbQuestion);
        return Header.OK(response(newQuestion));
    }

    //문의 리스트
    public List<TbQuestionApiRequest> getqlist(){
        List<TbQuestion> tbQuestions = tbQuestionRepository.findAll();
        List<TbQuestionApiRequest> tbQuestionApiRequestList = new ArrayList<>();

        for (TbQuestion tbQuestion : tbQuestions){
            TbQuestionApiRequest tbQuestionApiRequest = TbQuestionApiRequest.builder()
                    .id(tbQuestion.getId())
                    .qCategory(tbQuestion.getQCategory())
                    .qType(tbQuestion.getQType())
                    .qName(tbQuestion.getQName())
                    .qHp(tbQuestion.getQHp())
                    .qEmail(tbQuestion.getQEmail())
                    .qContent(tbQuestion.getQContent())
                    .qAnswer(tbQuestion.getQAnswer())
                    .qIscheck(tbQuestion.getQIscheck())
                    .qRegdate(tbQuestion.getQRegdate())
                    .qAdate(tbQuestion.getQAdate())
                    .tbMemId(tbQuestion.getTbMem().getId())
                    .build();
            tbQuestionApiRequestList.add(tbQuestionApiRequest);
        }
        return tbQuestionApiRequestList;
    }

    //문의 리스트
    public List<TbQuestionApiRequest> getlistu(Integer tbMemId){
        List<TbQuestion> tbQuestions = tbQuestionRepository.findAllByTbMemId(tbMemId);
        List<TbQuestionApiRequest> tbQuestionApiRequestList = new ArrayList<>();

        for (TbQuestion tbQuestion : tbQuestions){
            TbQuestionApiRequest tbQuestionApiRequest = TbQuestionApiRequest.builder()
                    .id(tbQuestion.getId())
                    .qCategory(tbQuestion.getQCategory())
                    .qType(tbQuestion.getQType())
                    .qName(tbQuestion.getQName())
                    .qHp(tbQuestion.getQHp())
                    .qEmail(tbQuestion.getQEmail())
                    .qContent(tbQuestion.getQContent())
                    .qAnswer(tbQuestion.getQAnswer())
                    .qIscheck(tbQuestion.getQIscheck())
                    .qRegdate(tbQuestion.getQRegdate())
                    .qAdate(tbQuestion.getQAdate())
                    .tbMemId(tbQuestion.getTbMem().getId())
                    .build();
            tbQuestionApiRequestList.add(tbQuestionApiRequest);
        }
        return tbQuestionApiRequestList;
    }

    //문의 디테일
    public Header<TbQuestionApiResponse> queget(Integer id){
        return tbQuestionRepository.findById(id)
                .map(tbQuestion -> response(tbQuestion))
                .map(Header::OK)
                .orElseGet(
                        ()->Header.Error("ERROR")
                );
    }
    //문의 삭제
    public Header quedel (Integer id){
        Optional<TbQuestion> optional = baseRepository.findById(id);
        return optional.map(tbQuestion -> {
            baseRepository.delete(tbQuestion);
            return Header.OK();
        }).orElseGet(()-> Header.Error("ERROR"));
    }

    //문의 대답
    public Header<TbQuestionApiResponse> anq(Header<TbQuestionApiRequest>request){
        TbQuestionApiRequest tbQuestionApiRequest = request.getData();
        Optional<TbQuestion> optional = baseRepository.findById(tbQuestionApiRequest.getId());
        return optional.map(tbQuestion -> {
            tbQuestion.setQIscheck("y");
            tbQuestion.setQAdate(LocalDateTime.now());
            tbQuestion.setQAnswer(tbQuestionApiRequest.getQAnswer());
            return tbQuestion;
        }).map(tbQuestion -> baseRepository.save(tbQuestion))
                .map(tbQuestion -> response(tbQuestion))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }


    //response
    private TbQuestionApiResponse response(TbQuestion tbQuestion){
        TbQuestionApiResponse tbQuestionApiResponse = TbQuestionApiResponse.builder()
                .id(tbQuestion.getId())
                .qCategory(tbQuestion.getQCategory())
                .qType(tbQuestion.getQType())
                .qName(tbQuestion.getQName())
                .qHp(tbQuestion.getQHp())
                .qEmail(tbQuestion.getQEmail())
                .qContent(tbQuestion.getQContent())
                .qRegdate(tbQuestion.getQRegdate()) // 문의등록일
                .qAnswer(tbQuestion.getQAnswer())
                .qAdate(tbQuestion.getQAdate())
                .qIscheck(tbQuestion.getQIscheck())
                .tbMemId(tbQuestion.getTbMem().getId())
                .build();
        return tbQuestionApiResponse;
    }


    @Override
    public Header<TbQuestionApiResponse> create(Header<TbQuestionApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbQuestionApiResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbQuestionApiResponse> update(Header<TbQuestionApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbQuestionApiResponse> delete(Integer id) {
        return null;
    }
}
