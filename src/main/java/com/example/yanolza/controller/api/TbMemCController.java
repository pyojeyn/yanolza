package com.example.yanolza.controller.api;


import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbMemC;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbMemApiRequest;
import com.example.yanolza.model.network.request.TbMemCRequest;
import com.example.yanolza.model.network.request.TbRoomApiRequest;
import com.example.yanolza.model.network.response.TbMemApiResponse;
import com.example.yanolza.model.network.response.TbMemCResponse;
import com.example.yanolza.model.network.response.TbRoomApiResponse;
import com.example.yanolza.service.TbMemCApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/memc8")
@RequiredArgsConstructor
public class TbMemCController extends CrudController<TbMemCRequest, TbMemCResponse, TbMemC> {
    @Autowired
    private final TbMemCApiService tbMemCApiService;


    @PostMapping("/cc")
    public Header<TbMemCResponse> mregist(@RequestBody Header<TbMemCRequest> request){
        System.out.println(request);
        return tbMemCApiService.create(request);
    }
    // 탈퇴회원리스트(admin) ok
    @GetMapping("/clist")
    public List<TbMemCRequest> list(){
        return tbMemCApiService.List();
    }


}
