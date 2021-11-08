package com.example.yanolza.service.img;

import com.example.yanolza.model.entity.TbHost;
import com.example.yanolza.model.entity.TbHostImg;
import com.example.yanolza.model.entity.TbRoomImg;
import com.example.yanolza.model.network.Header;
import com.example.yanolza.model.network.request.TbHostImgRequest;
import com.example.yanolza.model.network.request.TbRoomImgRequest;
import com.example.yanolza.model.network.response.TbHostImgResponse;
import com.example.yanolza.repository.TbHostImgRepository;
import com.example.yanolza.repository.TbHostRepository;
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
public class TbHostImgService extends BaseService<TbHostImgRequest, TbHostImgResponse, TbHostImg> {

    private final TbHostRepository tbHostRepository;
    private final TbHostImgRepository tbHostImgRepository;

    public String regist(TbHostImgRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
        List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("files");
         String path = "C://jennyboo/Spring/yanolzafinal/src/main/resources/static/img/hostimage//";
        for (MultipartFile multipartFile : fileList) {
            if (multipartFile.getSize() > 0) {
                String originName = multipartFile.getOriginalFilename();
                long fileSize = multipartFile.getSize();
                UUID uuid = UUID.randomUUID();
                originName = uuid + "_" + originName;
                String safeFile = path + originName;
                TbHostImg tbHostImg = TbHostImg.builder()
                        .originName(originName)
                        .fileSize(fileSize)
                        .safeFile(safeFile)
                        .tbHost(tbHostRepository.getById(request.getTbHostId()))
                        .build();
                     baseRepository.save(tbHostImg);
                try {
                    multipartFile.transferTo(new File(safeFile));
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return "파일첨부완료";
    }

    public String update(Integer id,TbHostImgRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
        Optional<TbHostImg> optional = tbHostImgRepository.findById(id);
        optional.map(tbHostImg -> {
            tbHostImgRepository.delete(tbHostImg);
            return Header.OK();
        }).orElseGet(()-> Header.Error("데이터 없음!"));

        List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("files");
        String path = "C://jennyboo/Spring/yanolzafinal/src/main/resources/static/img/hostimage//";

        for (MultipartFile multipartFile : fileList) {
            if (multipartFile.getSize() > 0) {
                String originName = multipartFile.getOriginalFilename();
                long fileSize = multipartFile.getSize();
                UUID uuid = UUID.randomUUID();
                originName = uuid + "_" + originName;
                String safeFile = path + originName;
                TbHostImg tbHostImg = TbHostImg.builder()
                        .originName(originName)
                        .fileSize(fileSize)
                        .safeFile(safeFile)
                        .tbHost(tbHostRepository.getById(request.getTbHostId()))
                        .build();
                baseRepository.save(tbHostImg);

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

    public String imgdelete(Integer id){
        Optional<TbHostImg> optional = tbHostImgRepository.findById(id);
        optional.map(tbHostImg -> {
            tbHostImgRepository.delete(tbHostImg);
            return Header.OK();
        }).orElseGet(()-> Header.Error("데이터 없음!"));
        return "파일 삭제 완료";
    }

    public List<TbHostImgRequest> gethpiclist(Integer tbHostId){
        List<TbHostImg> tbHostImgs = tbHostImgRepository.findAllByTbHostId(tbHostId);
        List<TbHostImgRequest> tbHostImgRequestList = new ArrayList<>();

        for (TbHostImg tbHostImg: tbHostImgs){
            TbHostImgRequest tbHostImgRequest = TbHostImgRequest.builder()
                    .id(tbHostImg.getId())
                    .originName(tbHostImg.getOriginName())
                    .fileSize(tbHostImg.getFileSize())
                    .safeFile(tbHostImg.getSafeFile())
                    .build();
            tbHostImgRequestList.add(tbHostImgRequest);
        }
        return tbHostImgRequestList;
    }

    public TbHostImgResponse response(TbHostImg tbHostImg){
        TbHostImgResponse tbHostImgResponse = TbHostImgResponse.builder()
                .id(tbHostImg.getId())
                .originName(tbHostImg.getOriginName())
                .fileSize(tbHostImg.getFileSize())
                .safeFile(tbHostImg.getSafeFile())
                .build();
        return tbHostImgResponse;
    }

    @Override
    public Header<TbHostImgResponse> create(Header<TbHostImgRequest> request) {
        return null;
    }
    @Override
    public Header<TbHostImgResponse> read(Integer id) {
        return null;
    }
    @Override
    public Header<TbHostImgResponse> update(Header<TbHostImgRequest> request) {
        return null;
    }
    @Override
    public Header<TbHostImgResponse> delete(Integer id) {
        return null;
    }
}
