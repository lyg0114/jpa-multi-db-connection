package com.waterleak.dto;

import java.util.Calendar;
import lombok.Builder;
import lombok.Data;

@Data
public class AckNbiotDto {
    private String imei;
    private String nbInstruction;
    private String insertDateStr;
    private String updateDateStr;

    @Builder
    public AckNbiotDto(String imei, String nbInstruction,
                       Calendar insertDateSt, Calendar updateDate) {
        this.imei = imei;
        this.nbInstruction = nbInstruction;
    }
}
