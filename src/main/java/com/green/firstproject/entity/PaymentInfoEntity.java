package com.green.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_info")
public class PaymentInfoEntity {
    
    @Column(name = "pay_seq")               private Long paySeq;
    @Column(name = "pay_method")            private String payMethod;
    @Column(name = "pay_type")              private Integer payType;
}
