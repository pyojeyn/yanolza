package com.example.yanolza.model.network.response;

import com.example.yanolza.model.entity.TbPay;
import com.example.yanolza.model.entity.TbReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbRoomApiResponse {
    private Integer id;
    private String myStime;
    private String myCtime;
    private String rmName;
    private String rmType;
    private String rmNumber;
    private String rmIntro;
    private String rmService;
    private String prSdate;   // 요일
    private String prSprice;  // 금액
    private String prDate;    // 요일
    private String prPrice;   // 금액
    private Integer tbHostId; // 숙박번호
    private List<TbPay> tbPayList;
    private List<TbPayApiResponse> tbPayApiResponseList;
    private List<TbRoomImgResponse> tbRoomImgResponseList;
    private List<TbReviewApiResponse> tbReviewApiResponseList;
}
