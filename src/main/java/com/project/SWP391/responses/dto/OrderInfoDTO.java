package com.project.SWP391.responses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.SWP391.entities.Item;
import com.project.SWP391.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO {

    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private String orderDate;

      private StoreInfoDTO store;



    private String noteText;

    private float total;

    private int status;



    Set<ItemInfoDTO> items ;

}
