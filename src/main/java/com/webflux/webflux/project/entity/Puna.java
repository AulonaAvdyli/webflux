package com.webflux.webflux.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table("puna")
    public class Puna {
        @Id
        private Integer id;
        private String emri;
        @Column("user_id")
        private Integer userId;
        private double paga ;
    }
