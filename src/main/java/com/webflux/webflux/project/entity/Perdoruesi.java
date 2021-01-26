package com.webflux.webflux.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table("perdoruesit")
    public class Perdoruesi {

        @Id
        private Integer id;
        private String emri;
        private int mosha;
        private String gjinia ;
    }

