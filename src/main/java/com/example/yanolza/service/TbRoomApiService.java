package com.example.yanolza.service;

import com.example.yanolza.model.entity.TbRoom;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbReviewApiRequest;
import com.example.yanolza.model.network.request.TbRoomApiRequest;
import com.example.yanolza.model.network.response.TbReviewApiResponse;
import com.example.yanolza.model.network.response.TbRoomApiResponse;
import com.example.yanolza.repository.TbHostRepository;
import com.example.yanolza.repository.TbRoomRepository;
import com.example.yanolza.service.img.TbRoomImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TbRoomApiService extends BaseService<TbRoomApiRequest, TbRoomApiResponse, TbRoom> {
    @Autowired
    private final TbHostRepository tbHostRepository;
    @Autowired
    private final TbRoomRepository tbRoomRepository;
    @Autowired
    private final TbRoomImgService tbRoomImgService;


    //객실 등록
    public Header<TbRoomApiResponse> regr(TbRoomApiRequest request, MultipartHttpServletRequest multipartHttpServletRequest){
        TbRoom tbRoom = TbRoom.builder()
                .myStime(request.getMyStime())
                .myCtime(request.getMyCtime())
                .rmName(request.getRmName())
                .rmType(request.getRmType())
                .rmNumber(request.getRmNumber())
                .rmIntro(request.getRmIntro())
                .rmService(request.getRmService())
                .prSdate(request.getPrSdate())
                .prSprice(request.getPrSprice())
                .prDate(request.getPrDate())
                .prPrice(request.getPrPrice())
                .tbHost(tbHostRepository.getById(request.getTbHostId()))
                .build();
        TbRoom newRoom = baseRepository.save(tbRoom);

        List<MultipartFile> files = multipartHttpServletRequest.getFiles("files");
        String path = "C://jennyboo/Spring/yanolzafinal/src/main/resources/static/img/roomimage//";

        for(MultipartFile multi : files){
            if(multi.getSize()>0){
                String originName = multi.getOriginalFilename();
                long fileSize = multi.getSize();
                UUID uuid = UUID.randomUUID();
                originName = uuid.toString() + "_" + originName;
                String safeFile = path + originName;
                try {
                    multi.transferTo(new File(safeFile));
                    tbRoomImgService.regist(originName, fileSize, safeFile, newRoom.getId());
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return Header.OK(response(newRoom));
    }

    // 객실 리스트
    public List<TbRoomApiRequest> getrlist(){
        List<TbRoom> tbRooms = tbRoomRepository.findAll();
        List<TbRoomApiRequest> tbRoomApiRequestList = new ArrayList<>();
        for (TbRoom tbRoom : tbRooms){
            TbRoomApiRequest tbRoomApiRequest = TbRoomApiRequest.builder()
                    .id(tbRoom.getId())
                    .myStime(tbRoom.getMyStime())
                    .myCtime(tbRoom.getMyCtime())
                    .rmName(tbRoom.getRmName())
                    .rmType(tbRoom.getRmType())
                    .rmNumber(tbRoom.getRmNumber())
                    .rmIntro(tbRoom.getRmIntro())
                    .rmService(tbRoom.getRmService())
                    .prSdate(tbRoom.getPrSdate())
                    .prSprice(tbRoom.getPrSprice())
                    .prDate(tbRoom.getPrDate())
                    .prPrice(tbRoom.getPrPrice())
                    .tbHostId(tbRoom.getTbHost().getId())
                    .build();
            tbRoomApiRequestList.add(tbRoomApiRequest);
        }
        return tbRoomApiRequestList;
    }

    //객실디테일
    public Header<TbRoomApiResponse> roomget(Integer id){
        return tbRoomRepository.findByid(id)
                .map(tbRoom -> response(tbRoom))
                .map(Header::OK)
                .orElseGet(
                        ()-> Header.Error("ERROR")
                );
    }

    //객실수정
    public Header<TbRoomApiResponse> updater(Header<TbRoomApiRequest> request){
        TbRoomApiRequest tbRoomApiRequest = request.getData();
        Optional<TbRoom> optional = baseRepository.findById(tbRoomApiRequest.getId());
        return optional.map(tbRoom -> {
                    tbRoom.setMyStime(tbRoomApiRequest.getMyStime());
                    tbRoom.setMyCtime(tbRoomApiRequest.getMyCtime());
                    tbRoom.setRmName(tbRoomApiRequest.getRmName());
                    tbRoom.setRmType(tbRoomApiRequest.getRmType());
                    tbRoom.setRmNumber(tbRoomApiRequest.getRmNumber());
                    tbRoom.setRmIntro(tbRoomApiRequest.getRmIntro());
                    tbRoom.setRmService(tbRoomApiRequest.getRmService());
                    tbRoom.setPrSdate(tbRoomApiRequest.getPrSdate());
                    tbRoom.setPrSprice(tbRoomApiRequest.getPrSprice());
                    tbRoom.setPrDate(tbRoomApiRequest.getPrDate());
                    tbRoom.setPrPrice(tbRoomApiRequest.getPrPrice());
                    return tbRoom;
                }).map(tbRoom -> baseRepository.save(tbRoom))
                .map(tbRoom -> response(tbRoom))
                .map(Header::OK)
                .orElseGet(()-> Header.Error("ERROR"));
    }


    // 객실 삭제
    public Header roomdel(Integer id){
        Optional<TbRoom> optional = baseRepository.findById(id);
        return optional.map(tbRoom -> {
            baseRepository.delete(tbRoom);
            return Header.OK();
        }).orElseGet(()->Header.Error("ERROR"));
    }

    //response
    public TbRoomApiResponse response(TbRoom tbRoom) {
        TbRoomApiResponse tbRoomApiResponse = TbRoomApiResponse.builder()
                .id(tbRoom.getId())
                .myStime(tbRoom.getMyStime())
                .myCtime(tbRoom.getMyCtime())
                .rmName(tbRoom.getRmName())
                .rmType(tbRoom.getRmType())
                .rmNumber(tbRoom.getRmNumber())
                .rmIntro(tbRoom.getRmIntro())
                .rmService(tbRoom.getRmService())
                .prSdate(tbRoom.getPrSdate())
                .prSprice(tbRoom.getPrSprice())
                .prDate(tbRoom.getPrDate())
                .prPrice(tbRoom.getPrPrice())
                .tbHostId(tbRoom.getTbHost().getId())
                .build();
        return tbRoomApiResponse;
    }

    @Override
    public Header<TbRoomApiResponse> create(Header<TbRoomApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbRoomApiResponse> read(Integer id) {
        return null;
    }

    @Override
    public Header<TbRoomApiResponse> update(Header<TbRoomApiRequest> request) {
        return null;
    }

    @Override
    public Header<TbRoomApiResponse> delete(Integer id) {
        return null;
    }
}
