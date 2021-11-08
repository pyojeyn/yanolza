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
public class TbHostForPayResponse {
    private Integer tbHostid;
    private List<TbPayApiResponse> tbPayApiResponseList;


}
