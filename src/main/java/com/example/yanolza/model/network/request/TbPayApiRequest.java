package com.example.yanolza.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbPayApiRequest {
    private Integer id;
    private String payName;
    private String payHostName;
    private String payRoomName;
    private String payHp;
    private String payIscar;
    private String payHowpay;
    private String payMoney;
    private String payCheckIn;
    private String payCheckOut;
    private String payCancel;
    private LocalDate payDate;
    private LocalDate payCdate;


    private Integer tbHostId;
    private Integer tbRoomId;   //객실idx
    private Integer tbMemId;  //구매회원idx
}
