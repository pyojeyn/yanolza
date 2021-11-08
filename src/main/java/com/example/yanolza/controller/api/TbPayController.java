package com.example.yanolza.controller.api;

import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbPay;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbPayApiRequest;
import com.example.yanolza.model.network.response.TbPayApiResponse;
import com.example.yanolza.service.TbPayApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class TbPayController extends CrudController<TbPayApiRequest, TbPayApiResponse, TbPay> {
    @Autowired
    TbPayApiService tbPayApiService;
    //예약 결제
    @PostMapping("/res")
    public Header<TbPayApiResponse> resr(@RequestBody Header<TbPayApiRequest> request){
        System.out.println(request);
        return tbPayApiService.book(request);
    }
    //예약 리스트 (admin)
    @GetMapping("/paylist")
    public List<TbPayApiRequest> list(){
        return tbPayApiService.getPayList();
    }

    //취소 예약리스트 (admin)
    @GetMapping("/payClist")
    public List<TbPayApiRequest> clist(){
        return tbPayApiService.getPayCList();
    }
    @GetMapping("/full/{tbHostId}/{payDate}")
    public List<TbPayApiRequest> fuuul(@PathVariable(name = "tbHostId")Integer tbHostId, @PathVariable(name = "payDate")@DateTimeFormat(pattern="yyyyMMdd") LocalDate payDate){
        return tbPayApiService.full(tbHostId, payDate);
    }

    //실시간 예약현황
    @GetMapping("/paylisth/{tbHostId}")
    public List<TbPayApiResponse> mother(@PathVariable(name = "tbHostId")Integer tbHostId){
        return tbPayApiService.mango(tbHostId);
    }

    //개인예약 리스트 (user)
    @GetMapping("/paylist1/{id}")
    public List<TbPayApiRequest> list1(@PathVariable(name = "id")Integer id){
        return tbPayApiService.getPayUList(id);
    }
    //예약취소
    @PatchMapping("/paylistc/{id}")
    public Header<TbPayApiResponse> with(@PathVariable(name = "id")Integer id, @RequestBody Header<TbPayApiRequest> request){
        return tbPayApiService.cancel(id, request);
    }
    //예약 디테일 (admin)
    @GetMapping("/paylist/{id}")
    public Header<TbPayApiResponse> getpay(@PathVariable(name = "id")Integer id){
        return tbPayApiService.getPaydetail(id);
    }
    //예약결제 삭제 (admin)
    @DeleteMapping("/del/{id}")
    public Header<TbPayApiResponse> paydel(@PathVariable(name = "id")Integer id){
        return tbPayApiService.delpay(id);
    }

    //예약자 이름 수정
   @PatchMapping("/paylist/{id}")
    public Header<TbPayApiResponse> nameup(@RequestBody Header<TbPayApiRequest>request){
        return tbPayApiService.upname(request);
    }
    //예약자 번호 수정
    @PatchMapping("/payhp/{id}")
    public Header<TbPayApiResponse> hpup(@RequestBody Header<TbPayApiRequest>request){
        return tbPayApiService.uphp(request);
    }
}
