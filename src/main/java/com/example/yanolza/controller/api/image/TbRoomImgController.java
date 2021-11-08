package com.example.yanolza.controller.api.image;

import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbRoomImg;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbReviewImgRequest;
import com.example.yanolza.model.network.request.TbRoomImgRequest;
import com.example.yanolza.model.network.response.TbReviewImgResoponse;
import com.example.yanolza.model.network.response.TbRoomImgResponse;
import com.example.yanolza.service.img.TbRoomImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;


@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class TbRoomImgController extends CrudController<TbRoomImgRequest, TbRoomImgResponse, TbRoomImg> {
    private final TbRoomImgService tbRoomImgService;

    @PostMapping("/img")
    public Header<TbRoomImgResponse> hregist(String originName, Long fileSize, String safeFile, Integer id){
        return tbRoomImgService.regist(originName, fileSize, safeFile, id);
    }

    @GetMapping("/imglist/{tbRoomId}")  // (Admin)
    public List<TbRoomImgRequest> imgList(@PathVariable(name = "tbRoomId")Integer tbRoomId){
        return tbRoomImgService.getRimgList(tbRoomId);
    }

    //수정1021
    @PutMapping("/img/{id}")
    public String hupdate(@PathVariable(name = "id") Integer id, @RequestPart(value = "data", required = false) TbRoomImgRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return tbRoomImgService.update(id, request, multipartHttpServletRequest);
    }

    @DeleteMapping("/img/{id}")
    public String hdelete(@PathVariable(name="id") Integer id)throws Exception {
        return tbRoomImgService.imgdelete(id);
    }

}