package com.example.yanolza.model.network.request;

import com.example.yanolza.model.entity.TbMem;
import com.example.yanolza.model.entity.TbRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbReviewApiRequest {
    private Integer id;
    private String tbHostname;
    private String reNkname;
    private Integer reGrade;
    private String reTitle;
    private String reContent;
    private String reReply;
    private String ische;
    private LocalDateTime reRegdate;
    private LocalDateTime reRpdate;
    private Integer tbRoomId;
    private Integer tbMemId;
    private Integer tbHostId;



}
