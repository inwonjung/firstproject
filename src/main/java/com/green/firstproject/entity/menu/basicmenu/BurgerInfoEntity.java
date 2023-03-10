package com.green.firstproject.entity.menu.basicmenu;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.menu.CategoryEntity;

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
@DynamicInsert
@Table(name="burger_info")
public class BurgerInfoEntity {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="bi_seq") private Long biSeq;
   @Column(name="bi_name") private String biName;
   @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore @JoinColumn(name="bi_cate") private CategoryEntity cate;
   @Column(name="bi_detail") private String biDetail;
   @Column(name="bi_file") private String biFile;
   @Column(name="bi_uri") private String biUri;
   @Column(name="bi_reg_dt") /* @ColumnDefault("CURRENT_TIMESTAMP") */ private LocalDate biRegDt;
   @Column(name="bi_sales_rate") @ColumnDefault("0") private Integer biSalesRate;

   public void setCategory(CategoryEntity cate){
      this.cate = cate;
   }

   public void upSales(Integer count){
      int sales = biSalesRate+count;
      this.biSalesRate = sales;
   }
}