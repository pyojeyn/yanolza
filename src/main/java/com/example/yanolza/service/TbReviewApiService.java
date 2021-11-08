package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbReview;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbReviewApiRequest;
import com.example.yanolza.model.network.response.TbReviewApiResponse;
import com.example.yanolza.repository.TbHostRepository;
import com.example.yanolza.repository.TbMemRepository;
import com.example.yanolza.repository.TbReviewRepository;
import com.example.yanolza.repository.TbRoomRepository;
import com.example.yanolza.service.img.TbReviewImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TbReviewApiService extends BaseService<TbReviewApiRequest, TbReviewApiResponse, TbReview> {

    private final TbRoomRepository tbRoomRepository;
    private final TbMemRepository tbMemRepository;
    private final TbReviewRepository tbReviewRepository;
    private final TbReviewImgService tbReviewImgService;

    // 리뷰 작성 memid, roomid, nkname
    public Header<TbReviewApiResponse> writer(TbReviewApiRequest request, MultipartHttpServletRequest multipartHttpServletRequest){
        TbReview tbReview = TbReview.builder()
                .tbHostname(request.getTbHostname())
                .reNkname(request.getReNkname())
                .reGrade(request.getReGrade())
                .reTitle(request.getReTitle())
                .reContent(request.getReContent())
                .reReply(request.getReReply())
                .tbHostId(request.getTbHostId())
                .ische("n")
                .tbMem(tbMemRepository.getById(request.getTbMemId()))
                .tbRoom(tbRoomRepository.getById(request.getTbRoomId()))
                .build();
        TbReview newReview = baseRepository.save(tbReview);

        List<MultipartFile> files = multipartHttpServletRequest.getFiles("files");
        String path = "C://jennyboo/Spring/yanolzafinal/src/main/resources/static/img/reviewImg//";

        for(MultipartFile multi : files){
            if(multi.getSize()>0){
                String originName = multi.getOriginalFilename();
                long fileSize = multi.getSize();
                UUID uuid = UUID.randomUUID();
                originName = uuid.toString() + "_" + originName;
                String safeFile = path + originName;
                try {
                    multi.transferTo(new File(safeFile));
                    tbReviewImgService.review(originName, fileSize, safeFile, newReview.getId());
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return Header.OK(response(newReview));
    }

    // 리뷰 리스트
    public List<TbReviewApiRequest> getRreList(){
        List<TbReview> tbReviews = tbReviewRepository.findAll();
        List<TbReviewApiRequest> tbReviewApiRequestList = new ArrayList<>();

        for (TbReview tbReview : tbReviews){
            TbReviewApiRequest tbReviewApiRequest = TbReviewApiRequest.builder()
                    .id(tbReview.getId())
                    .tbHostname(tbReview.getTbHostname())
                    .reNkname(tbReview.getReNkname())
                    .reGrade(tbReview.getReGrade())
                    .reTitle(tbReview.getReTitle())
                    .reContent(tbReview.getReContent())
                    .ische(tbReview.getIsche())
                    .reRegdate(tbReview.getReRegdate())
                    .reRpdate(tbReview.getReRpdate())
                    .reReply(tbReview.getReReply())
                    .tbMemId(tbReview.getTbMem().getId())
                    .tbRoomId(tbReview.getTbRoom().getId())
                    .tbHostId(tbReview.getTbHostId())
                    .build();
            tbReviewApiRequestList.add(tbReviewApiRequest);
        }
        return tbReviewApiRequestList;
    }

    // 리뷰 리스트 (답변)
    public List<TbReviewApiRequest> getReList(){
        List<TbReview> tbReviews = tbReviewRepository.findAllByIsche("y");
        List<TbReviewApiRequest> tbReviewApiRequestList = new ArrayList<>();

        for (TbReview tbReview : tbReviews){
            TbReviewApiRequest tbReviewApiRequest = TbReviewApiRequest.builder()
                    .id(tbReview.getId())
                    .tbHostname(tbReview.getTbHostname())
                    .reNkname(tbReview.getReNkname())
                    .reGrade(tbReview.getReGrade())
                    .reTitle(tbReview.getReTitle())
                    .reContent(tbReview.getReContent())
                    .ische(tbReview.getIsche())
                    .reRegdate(tbReview.getReRegdate())
                    .reRpdate(tbReview.getReRpdate())
                    .reReply(tbReview.getReReply())
                    .tbMemId(tbReview.getTbMem().getId())
                    .tbRoomId(tbReview.getTbRoom().getId())
                    .tbHostId(tbReview.getTbHostId())
                    .build();
            tbReviewApiRequestList.add(tbReviewApiRequest);
        }
        return tbReviewApiRequestList;
    }

    // 리뷰 리스트 (답변) (host)
    public List<TbReviewApiRequest> getHlist(Integer tbHostId){
        List<TbReview> tbReviews = tbReviewRepository.findAllByTbHostIdAndIsche(tbHostId,"y");
        List<TbReviewApiRequest> tbReviewApiRequestList = new ArrayList<>();

        for (TbReview tbReview : tbReviews){
            TbReviewApiRequest tbReviewApiRequest = TbReviewApiRequest.builder()
                    .id(tbReview.getId())
                    .tbHostname(tbReview.getTbHostname())
                    .reNkname(tbReview.getReNkname())
                    .reGrade(tbReview.getReGrade())
                    .reTitle(tbReview.getReTitle())
                    .reContent(tbReview.getReContent())
                    .ische(tbReview.getIsche())
                    .reRegdate(tbReview.getReRegdate())
                    .reRpdate(tbReview.getReRpdate())
                    .reReply(tbReview.getReReply())
                    .tbMemId(tbReview.getTbMem().getId())
                    .tbRoomId(tbReview.getTbRoom().getId())
                    .tbHostId(tbReview.getTbHostId())
                    .build();
            tbReviewApiRequestList.add(tbReviewApiRequest);
        }
        return tbReviewApiRequestList;
    }

    // 리뷰 리스트 (미답변)
    public List<TbReviewApiRequest> getmiReList(Integer tbHostId){
        List<TbReview> tbReviews = tbReviewRepository.findAllByTbHostIdAndIsche(tbHostId,"n");
        List<TbReviewApiRequest> tbReviewApiRequestList = new ArrayList<>();

        for (TbReview tbReview : tbReviews){
            TbReviewApiRequest tbReviewApiRequest = TbReviewApiRequest.builder()
                    .id(tbReview.getId())
                    .tbHostname(tbReview.getTbHostname())
                    .reNkname(tbReview.getReNkname())
                    .reGrade(tbReview.getReGrade())
                    .reTitle(tbReview.getReTitle())
                    .reContent(tbReview.getReContent())
                    .ische(tbReview.getIsche())
                    .reRegdate(tbReview.getReRegdate())
                    .reRpdate(tbReview.getReRpdate())
                    .reReply(tbReview.getReReply())
                    .tbMemId(tbReview.getTbMem().getId())
                    .tbRoomId(tbReview.getTbRoom().getId())
                    .tbHostId(tbReview.getTbHostId())
                    .build();
            tbReviewApiRequestList.add(tbReviewApiRequest);
        }
        return tbReviewApiRequestList;
    }

    // 리뷰 디테일 ok
    public Header<TbReviewApiResponse> getrdetail(Integer id) {
        return baseRepository.findById(id)
                .map(tbReview -> response(tbReview))
                .map(Header::OK)
                .orElseGet(
                        ()-> Header.Error("데이터 없음! 산뜻하넴 말투")
                );
    }

    //리뷰 삭제 ok
    public Header revdel(Integer id){
        Optional <TbReview> optional = baseRepository.findById(id);
        return optional.map(tbReview -> {
            baseRepository.delete(tbReview);
            return Header.OK();
        }).orElseGet(()->Header.Error("ERROR"));
    }


    //사장님 댓글 달기
    public Header<TbReviewApiResponse> updatere(Header<TbReviewApiRequest>request){
        TbReviewApiRequest tbReviewApiRequest = request.getData();
        Optional<TbReview> optional = baseRepository.findById(tbReviewApiRequest.getId());
        return optional.map(tbReview -> {
                    tbReview.setIsche("y");
                    tbReview.setReReply(tbReviewApiRequest.getReReply());
                    tbReview.setReRpdate(LocalDateTime.now());
                    return tbReview;
                }).map(tbReview -> baseRepository.save(tbReview))
                .map(tbReview -> response(tbReview))
                .map(Header::OK)
                .orElseGet(()->Header.Error("ERROR"));
    }

    public Header<TbReviewApiResponse> rereupdate(Header<TbReviewApiRequest>request){
        TbReviewApiRequest tbReviewApiRequest = request.getData();
        Optional<TbReview> optional = baseRepository.findById(tbReviewApiRequest.getId());
        return optional.map(tbReview -> {
                    tbReview.setReReply(tbReviewApiRequest.getReReply());
                    return tbReview;
                }).map(tbReview -> baseRepository.save(tbReview))
                .map(tbReview -> response(tbReview))
                .map(Header::OK)
                .orElseGet(()->Header.Error("ERROR"));
    }

    TbReviewApiResponse response(TbReview tbReview){
        TbReviewApiResponse tbReviewApiResponse = TbReviewApiResponse.builder()
                .id(tbReview.getId())
                .reGrade(tbReview.getReGrade())
                .reTitle(tbReview.getReTitle())
                .tbHostname(tbReview.getTbHostname())
                .reNkname(tbReview.getReNkname())
                .reGrade(tbReview.getReGrade())
                .reTitle(tbReview.getReTitle())
                .reContent(tbReview.getReContent())
                .reReply(tbReview.getReReply())
                .ische(tbReview.getIsche())
                .reRegdate(tbReview.getReRegdate())
                .reRpdate(tbReview.getReRpdate())
                .tbHostId(tbReview.getTbHostId())
                .tbRoomId(tbReview.getTbRoom().getId())
                .tbMemId(tbReview.getTbMem().getId())
                .build();
        return tbReviewApiResponse;
    }

    @Override
    public Header<TbReviewApiResponse> create(Header<TbReviewApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbReviewApiResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbReviewApiResponse> update(Header<TbReviewApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbReviewApiResponse> delete(Integer id) {
        return null;
    }
}
