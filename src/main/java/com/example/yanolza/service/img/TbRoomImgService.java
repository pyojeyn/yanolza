package com.example.yanolza.service.img;

import com.example.yanolza.model.entity.TbReviewImg;
import com.example.yanolza.model.entity.TbRoomImg;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbRoomImgRequest;
import com.example.yanolza.model.network.response.TbRoomImgResponse;
import com.example.yanolza.repository.TbRoomImgRepository;
import com.example.yanolza.repository.TbRoomRepository;
import com.example.yanolza.service.BaseService;
import lombok.RequiredArgsConstructor;
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
public class TbRoomImgService extends BaseService<TbRoomImgRequest, TbRoomImgResponse, TbRoomImg> {

    private final TbRoomRepository tbRoomRepository;
    private final TbRoomImgRepository tbRoomImgRepository;

    public Header<TbRoomImgResponse> regist(String originName, Long fileSize, String safeFile, Integer id) {
        TbRoomImg tbRoomImg = TbRoomImg.builder()
                .originName(originName)
                .fileSize(fileSize)
                .safeFile(safeFile)
                .tbRoom(tbRoomRepository.getById(id))
                .build();
        TbRoomImg newRoomImg = baseRepository.save(tbRoomImg);
        return Header.OK(response(newRoomImg));

    }

    public List<TbRoomImgRequest> getRimgList(Integer tbRoomId){
        List<TbRoomImg> tbRoomImgs = tbRoomImgRepository.findAllByTbRoomId(tbRoomId);
        List<TbRoomImgRequest> tbRoomImgRequestList = new ArrayList<>();
        for (TbRoomImg tbRoomImg: tbRoomImgs){
            TbRoomImgRequest tbRoomImgRequest = TbRoomImgRequest.builder()
                    .id(tbRoomImg.getId())
                    .originName(tbRoomImg.getOriginName())
                    .fileSize(tbRoomImg.getFileSize())
                    .safeFile(tbRoomImg.getSafeFile())
                    .build();
            tbRoomImgRequestList.add(tbRoomImgRequest);
        }
        return tbRoomImgRequestList;
    }

    public String update(Integer id, TbRoomImgRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
        Optional<TbRoomImg> optional = tbRoomImgRepository.findById(id);
        optional.map(tbRoomImg -> {
            tbRoomImgRepository.delete(tbRoomImg);
            return Header.OK();
        }).orElseGet(()-> Header.Error("데이터 없음!"));

        List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("files");
        String path = "C://jennyboo/Spring/yanolzafinal/src/main/resources/static/img/roomimage//";

        for (MultipartFile multipartFile : fileList) {
            if (multipartFile.getSize() > 0) {
                String originName = multipartFile.getOriginalFilename();
                long fileSize = multipartFile.getSize();
                UUID uuid = UUID.randomUUID();
                originName = uuid + "_" + originName;
                String safeFile = path + originName;
                TbRoomImg tbRoomImg = TbRoomImg.builder()
                        .originName(originName)
                        .fileSize(fileSize)
                        .safeFile(safeFile)
                        .tbRoom(tbRoomRepository.getById(request.getTbRoomId()))
                        .build();
                baseRepository.save(tbRoomImg);

                try {
                    multipartFile.transferTo(new File(safeFile));
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return "파일수정완료";
    }

    // 삭제
    public String imgdelete(Integer id){
        Optional<TbRoomImg> optional = tbRoomImgRepository.findById(id);
        optional.map(tbHostImg -> {
            tbRoomImgRepository.delete(tbHostImg);
            return Header.OK();
        }).orElseGet(()-> Header.Error("데이터 없음!"));

        return "파일 삭제 완료";
    }

    public TbRoomImgResponse response(TbRoomImg tbRoomImg){
        TbRoomImgResponse tbRoomImgResponse = TbRoomImgResponse.builder()
                .id(tbRoomImg.getId())
                .originName(tbRoomImg.getOriginName())
                .fileSize(tbRoomImg.getFileSize())
                .safeFile(tbRoomImg.getSafeFile())
                .build();
        return tbRoomImgResponse;
    }

    @Override
    public Header<TbRoomImgResponse> create(Header<TbRoomImgRequest> request) {
        return null;
    }
    @Override
    public Header<TbRoomImgResponse> read(Integer id) {
        return null;
    }
    @Override
    public Header<TbRoomImgResponse> update(Header<TbRoomImgRequest> request) {
        return null;
    }
    @Override
    public Header<TbRoomImgResponse> delete(Integer id) {
        return null;
    }
}
