package com.example.yanolza.model.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "room_seq",
        sequenceName = "room_seq",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TbRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
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

    @ManyToOne
    @JoinColumn(name = "tb_host_id")
    private TbHost tbHost;

    @OneToMany (fetch = FetchType.LAZY, mappedBy ="tbRoom" ,cascade = CascadeType.REMOVE  )
    private List<TbReview> tbReviewList;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "tbRoom",cascade = CascadeType.REMOVE  )
    private List<TbPay> tbPayList;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "tbRoom" ,cascade = CascadeType.REMOVE  )
    private List<TbRoomImg> tbRoomImgList;

}

