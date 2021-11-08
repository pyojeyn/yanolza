package com.example.yanolza.controller.api;

import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbZzim;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbZzimApiRequest;
import com.example.yanolza.model.network.response.TbZzimApiResponse;
import com.example.yanolza.service.TbZzimApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zzim")
@RequiredArgsConstructor
public class TbZzimController extends CrudController<TbZzimApiRequest, TbZzimApiResponse, TbZzim> {
    @Autowired
    TbZzimApiService tbZzimApiService;

    //찜하기
    @PostMapping("/zzim")
    public Header<TbZzimApiResponse> zzim(@RequestBody Header<TbZzimApiRequest> request){
        System.out.println(request);
        return tbZzimApiService.regz(request);
    }

    // 찜 취소
    @DeleteMapping("/delete/{id}")
    public Header<TbZzimApiResponse> delzzim(@PathVariable(name = "id")Integer id){
        return tbZzimApiService.zzimdel(id);
    }

    //예약 리스트 (user)
    @GetMapping("/zzzlist/{id}")
    public List<TbZzimApiRequest> listzzim(@PathVariable Integer id){
        return tbZzimApiService.getzzim(id);
    }
}
