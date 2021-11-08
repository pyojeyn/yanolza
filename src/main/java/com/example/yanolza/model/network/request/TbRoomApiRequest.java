package com.example.yanolza.model.network.request;

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
public class TbRoomApiRequest {
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

    private List<TbReview> tbReviewList;
    private List<TbPay> tbPayList;
}
