package com.example.yanolza.model.network.response;

import com.example.yanolza.model.network.response.TbHostApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbHostTbRoomApiResponse {
    private TbHostApiResponse tbHostApiResponse;
}
