package com.example.yanolza.controller.api;


import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbMem;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbMemApiRequest;
import com.example.yanolza.model.network.response.TbMemApiResponse;
import com.example.yanolza.model.network.response.TbMemTbHostApiResponse;
import com.example.yanolza.model.network.response.TbMemTbZzimApiResponse;
import com.example.yanolza.model.network.response.TbmemTbpayApiResoponse;
import com.example.yanolza.service.TbMemApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tbmem")
@RequiredArgsConstructor
public class TbMemController extends CrudController<TbMemApiRequest, TbMemApiResponse, TbMem> {
    private final TbMemApiService tbMemApiService;

    @PostMapping("/regist")
    public Header<TbMemApiResponse> mregist(@RequestBody Header<TbMemApiRequest> request){
        System.out.println(request);
        return tbMemApiService.regist(request);
    }
    // 전체회원리스트(admin) ok
    @GetMapping("/mlist")
    public List<TbMemApiRequest> mlist(){
        return tbMemApiService.getMemList();
    }

    //회원 리스트 디테일(admin) ok
    @GetMapping("/mlist/{id}")
    public Header<TbMemApiResponse> mdetai(@PathVariable(name = "id") Integer id){
        return tbMemApiService.mdetail(id);
    }
    // 비밀번호 재설정
    @GetMapping("/regis/{memEmail}")
    public List<TbMemApiRequest> gethp(@PathVariable(name = "memEmail")String memEmail){
        return tbMemApiService.getph(memEmail);
    }

    // 세션
    @GetMapping("/session/{memEmail}")
    public Integer getsse(@PathVariable(name = "memEmail") String memEmail){
        return tbMemApiService.session(memEmail);
    }

    //닉네임 가져올때
    @GetMapping("/nik/{id}")
    public String getnik(@PathVariable(name = "id")Integer id){
        return tbMemApiService.nik(id);
    }

    //회원 정보 삭제(admin) ok 탈퇴에도 쓰면됨
    @DeleteMapping("/mlist/{id}")
    public Header<TbMemApiResponse> delmem(@PathVariable(name = "id")Integer id){
        return tbMemApiService.memdel(id);
    }

    //닉네임 수정
    @PatchMapping("/nkname/{id}")
    public Header<TbMemApiResponse> updatenk(@PathVariable(name = "id")Integer id, @RequestBody Header<TbMemApiRequest> request){
        return tbMemApiService.nkupdate(id, request);
    }
    // 휴대폰번호 수정
    @PatchMapping("/hp/{id}")
    public Header<TbMemApiResponse> updatehp(@PathVariable(name = "id")Integer id, @RequestBody Header<TbMemApiRequest> request){
        return tbMemApiService.hpupdate(id, request);
    }

    //개인 예약 리스트 (user)
    @GetMapping("/paylistu/{id}")
    public Header<TbmemTbpayApiResoponse> paylist(@PathVariable Integer id){
        return tbMemApiService.getreser(id);
    }

    //개인 찜 리스트
    @GetMapping("/zzimlist/{id}")
    public Header<TbMemTbZzimApiResponse> zzimlist(@PathVariable Integer id){
        return tbMemApiService.getZzimu(id);
    }

    //호스트 입장시 필요.
    @GetMapping("/tbhost/{id}")
    public Header<TbMemTbHostApiResponse> gethost(@PathVariable Integer id){
        return tbMemApiService.getHhost(id);
    }


    // 예약결제쪽 전화번호 불러오기
    @GetMapping("/phone/{id}")
    public String getHP(@PathVariable(name = "id")Integer id){
        return tbMemApiService.getHp(id);
    }
}