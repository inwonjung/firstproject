package com.green.firstproject.entity.order;

import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail_composition")
@DynamicInsert
public class OrderDetailCompositionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="odc_seq") private Long odcSeq;
    // @Column(name="odc_od_seq") private Long odcOdSeq;
    // @Column(name="odc_lsopt_seq") private Long odcLostSeq;
    // @Column(name="odc_ldopt_seq") private Long odcLdoptSeq;
    @ManyToOne @JoinColumn(name="odc_od_seq") OrderDetailEntity orderDetail;
    @ManyToOne @JoinColumn(name="odc_lsopt_seq") SideOptionEntity side;
    @ManyToOne @JoinColumn(name="odc_ldopt_seq") DrinkOptionEntity drink;
}