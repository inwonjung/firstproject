package com.green.firstproject.entity.menu.sellermenu;
import java.time.LocalDate;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="event_info")
public class EventInfoEntity {
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name="ei_seq") private Long eiSeq;
      @Column(name="ei_name") private String eiName;
      @Column(name="ei_start_dt") private LocalDate eiStartDt;
      @Column(name="ei_end_dt") private LocalDate eiEndDt;
      @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore @JoinColumn(name="ei_cate") private CategoryEntity cate;
      @Column(name="ei_detail") private String eiDetail;
      @Column(name="ei_file") private String eiFile;
      @Column(name="ei_uri") private String eiUri;

      public void setCategory(CategoryEntity cate){
      this.cate = cate;
      }

}