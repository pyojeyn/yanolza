package com.example.yanolza.controller.api;

import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbQuestion;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbQuestionApiRequest;
import com.example.yanolza.model.network.response.TbHostApiResponse;
import com.example.yanolza.model.network.response.TbQuestionApiResponse;
import com.example.yanolza.service.TbQuestionApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ques")
@RequiredArgsConstructor
public class TbQuestionController extends CrudController<TbQuestionApiRequest, TbQuestionApiResponse, TbQuestion> {
    @Autowired
    private final TbQuestionApiService tbQuestionApiService;

    // 1대1문의 등록
    @PostMapping("/regi")
    public Header<TbQuestionApiResponse> reg(@RequestBody Header<TbQuestionApiRequest> request){
        System.out.println(request);
        return tbQuestionApiService.regi(request);
    }
    //1대1문의 리스트 (admin)
    @GetMapping("/qlist")
    public List<TbQuestionApiRequest> glist(){
        return tbQuestionApiService.getqlist();
    }

    //1대1문의 리스트 (user)
    @GetMapping("/qlist/{tbMemId}")
    public List<TbQuestionApiRequest> qlist(@PathVariable(name = "tbMemId") Integer tbMemId){
        return tbQuestionApiService.getlistu(tbMemId);
    }

    //1대1문의 디테일 (admin)
    @GetMapping("/qulist/{id}")
    public Header<TbQuestionApiResponse> getque(@PathVariable(name = "id")Integer id){
        return tbQuestionApiService.queget(id);
    }
    //1대1문의 삭제 (admin)
    @DeleteMapping("/qlist/{id}")
    public Header<TbQuestionApiResponse> delque(@PathVariable(name = "id")Integer id){
        return tbQuestionApiService.quedel(id);
    }
    //관리자 답변 (admin)
    @PatchMapping("/qlist/{id}")
    public Header<TbQuestionApiResponse> qna(@RequestBody Header<TbQuestionApiRequest> request){
        return tbQuestionApiService.anq(request);
    }
}
