package com.example.yanolza.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbHostApiResponse {
    private Integer id;
    private String myHp;
    private String almName;
    private String almHp;
    private String almWeek;
    private String almStime;
    private String almCtime;
    private String myCorporate;
    private String myConame;
    private String myCondition;
    private String myType;
    private String myConum;
    private String myAddr1;
    private String myAddr2;
    private String myEmail;
    //--------------------------------1
    private String myIntcom;
    private String myIntcomadd1;
    private String myIntcomadd2;
    private Double latitude;    // 위도
    private Double longitude;   // 경도
    private String myService;
    private String myIntro;
    private String myComeway;
    private String mySidein1;
    private String mySidein2;
    private String mySidein3;
    private String myRmrule;
    private String myOneline;
    private String myPcash;
    private String myParking;
    private String myKakunin;
    //-------------------------------2

    private Integer myRetime;
    private String pkSsdate;    //준 성수기 시간 시작일
    private String pkCcdate;    //준 성수기 시간 마감일
    private String pkSdate;     //성수기 시간 시작일
    private String pkCdate;     //성수기 시간 마감일
    private String pkAdmit;
    private Integer tbMemId;


    private List<TbHostImgResponse> tbHostImgResponseList;
    private List<TbRoomApiResponse> tbRoomApiResponseList;
    private List<TbPayApiResponse> tbPayApiResponseList;
    private List<TbHostApiResponse> tbHostApiResponseList;

}
