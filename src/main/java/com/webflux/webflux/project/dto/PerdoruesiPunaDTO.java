package com.webflux.webflux.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public class PerdoruesiPunaDTO {
        private Integer userId;
        private String emriPerdoruesit;
        private int mosha;
        private double paga;
        private Integer punaId;
        private String emriPunes;
        private String gjinia;
    }

