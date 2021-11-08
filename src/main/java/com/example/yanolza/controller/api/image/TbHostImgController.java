package com.example.yanolza.controller.api.image;

import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbHostImg;
import com.example.yanolza.model.network.request.TbHostImgRequest;
import com.example.yanolza.model.network.response.TbHostImgResponse;
import com.example.yanolza.service.img.TbHostImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/host")
@RequiredArgsConstructor
public class TbHostImgController extends CrudController<TbHostImgRequest, TbHostImgResponse, TbHostImg> {
    private final TbHostImgService tbHostImgService;

    @PostMapping("/img")
    public String hregist(@RequestPart(value = "data", required = false) TbHostImgRequest request, MultipartHttpServletRequest multipartHttpServletRequest)throws Exception {
        return tbHostImgService.regist(request, multipartHttpServletRequest);
    }
    @GetMapping("/himglist/{tbHostId}")
    public List<TbHostImgRequest> imglist(@PathVariable(name = "tbHostId")Integer tbHostId){
        return tbHostImgService.gethpiclist(tbHostId);
    }

    //수정1021
    @PutMapping("/img/{id}")
    public String hupdate(@PathVariable(name = "id") Integer id, @RequestPart(value = "data", required = false) TbHostImgRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return tbHostImgService.update(id, request, multipartHttpServletRequest);
    }

    @DeleteMapping("/img/{id}")
    public String hdelete(@PathVariable(name="id") Integer id)throws Exception {
        return tbHostImgService.imgdelete(id);
    }
}
