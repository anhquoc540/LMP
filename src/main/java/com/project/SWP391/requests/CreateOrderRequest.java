package com.project.SWP391.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.SWP391.requests.dto.ItemReqDTO;
import com.project.SWP391.responses.dto.ItemInfoDTO;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderRequest {

    private List<ItemReqDTO> items ;
    private Float total ;

    private Long storeId;

   // private Long userId ;
    private Long storeTimeId;

    private Long createDate;




}
