package com.example.yanolza.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "pay_seq",
        sequenceName = "pay_seq",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TbPay {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_seq")
    private Integer id;
    private String payName;
    private String payHp;
    private String payIscar;
    private String payHowpay;
    private String payMoney;
    private String payHostName;
    private String payRoomName;
    private String payCheckIn;
    private String payCheckOut;
    private String payCancel;
    private LocalDate payDate;
    @CreatedDate
    private LocalDate payCdate;

    @ManyToOne
    private TbHost tbHost;
    @ManyToOne
    private TbRoom tbRoom;
    @ManyToOne
    private TbMem tbMem;



}
