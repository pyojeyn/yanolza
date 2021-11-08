package com.example.yanolza.controller.api.image;

import com.example.yanolza.controller.CrudController;
import com.example.yanolza.model.entity.TbReview;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbHostImgRequest;
import com.example.yanolza.model.network.request.TbReviewImgRequest;
import com.example.yanolza.model.network.response.TbReviewImgResoponse;
import com.example.yanolza.service.TbReviewApiService;
import com.example.yanolza.service.img.TbReviewImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class TbReviewImgController extends CrudController<TbReviewImgRequest, TbReviewImgResoponse, TbReview> {
    private final TbReviewApiService tbReviewApiService;
    private final TbReviewImgService tbReviewImgService;


    //review 이미지 등록
    @PostMapping("/img")
    public Header<TbReviewImgResoponse> hregist(String originName, Long fileSize, String safeFile, Integer id){
        return tbReviewImgService.review(originName, fileSize, safeFile, id);
    }

    //이미지 불러오기 (admin)
    @GetMapping("/imgList/{tbReviewId}")
    public List<TbReviewImgRequest> RevImg(@PathVariable(name = "tbReviewId")Integer tbReviewId){
        return tbReviewImgService.getRevImg(tbReviewId);
    }

    //수정1021
    @PutMapping("/img/{id}")
    public String hupdate(@PathVariable(name = "id") Integer id, @RequestPart(value = "data", required = false) TbReviewImgRequest request, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        return tbReviewImgService.update(id, request, multipartHttpServletRequest);
    }

    @DeleteMapping("/img/{id}")
    public String hdelete(@PathVariable(name="id") Integer id)throws Exception {
        return tbReviewImgService.imgdelete(id);
    }
}
