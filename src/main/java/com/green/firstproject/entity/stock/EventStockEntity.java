package com.green.firstproject.entity.stock;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "event_stock")
@DynamicInsert
public class EventStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "es_seq") private Long esSeq;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "es_si_seq") private StoreInfoEntity store;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "es_ei_seq") private EventInfoEntity event;
    @Column(name = "es_stock") @ColumnDefault("0")
    private Integer esStock;
    
}
