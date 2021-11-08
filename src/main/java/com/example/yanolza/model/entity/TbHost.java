package com.example.yanolza.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name = "host_seq",
        sequenceName = "host_seq",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TbHost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "host_seq")
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
    private Double latitude;    //위도
    private Double longitude;   //경도
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


    @ManyToOne
    @JoinColumn(name = "tb_mem_id")
    private TbMem tbMem;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tbHost",cascade = CascadeType.REMOVE  )
    private List<TbHostImg> tbHostImgList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tbHost" ,cascade = CascadeType.REMOVE  )
    private List<TbPay> tbPayList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tbHost",cascade = CascadeType.REMOVE )
    private List<TbRoom> tbRoomList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tbHost" ,cascade = CascadeType.REMOVE  )
    private List<TbZzim> tbZzimList;

}
