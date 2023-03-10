package com.green.firstproject.entity.master;

import java.time.LocalTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store_info")
@DynamicInsert
public class StoreInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_seq")                    private Long siSeq;
    @Column(name = "si_name")                   private String siName;
    @Column(name = "si_address")                private String siAddress;
    @Column(name = "si_address_detail")         private String siAddressDetail;
    @Column(name = "si_phone")                  private String siPhone;
    @Column(name = "si_open_time")              private LocalTime siOpenTime;
    @Column(name = "si_close_time")             private LocalTime siCloseTime;
    @Column(name = "si_min_order_amount")       private Integer siMinOrderAmount;
    @Column(name = "si_status")      @ColumnDefault("1")        
    private Integer siStatus;
    @Column(name="si_delivery_area") private String siDeliveryArea;
    @Column(name="si_latitude")   private String siLatitude;
    @Column(name="si_longgitude")   private String siLonggitude;
}
