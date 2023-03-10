package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.vo.menu.BurgerCateVo;

@Repository
public interface BurgerInfoRepository extends JpaRepository<BurgerInfoEntity, Long> {

    Integer countByBiName(String biName);


    BurgerInfoEntity findByBiSeq(Long seq);
    BurgerInfoEntity findByBiUri(String uri);

    List<BurgerInfoEntity> findByCate(CategoryEntity cate);

    @Query(value = "select c.bi_seq as seq, c.bi_name as name," 
                        +"c.bi_cate as category,"
                        +"c.bi_detail as detail, c.bi_uri as uri, min(e.menu_price) as price, "
                        +"if(DATEDIFF(CURDATE( ),c.bi_reg_dt)<=30,'true','false') as new,"
                        +"if(ranking<=10,'true','false') as best, if(d.bs_stock=0, 'true','false') as soldout, f.count as 'count', "
                        +"'SET' as type "
                +"from burger_info c "
                +"join(select RANK() OVER (ORDER BY a.bi_sales_rate desc) as ranking, a.bi_seq from burger_info a) b on c.bi_seq=b.bi_seq "
                +"join (select * from burger_stock bs where bs.bs_si_seq=:store) d on d.bs_bi_seq =c.bi_seq "
                +"join (select g.menu_bi_seq , count(g.menu_bi_seq) as 'count' from menu_info g group by g.menu_bi_seq) f on f.menu_bi_seq = c.bi_seq "
                +"join menu_info e on e.menu_bi_seq = c.bi_seq group by c.bi_seq "
                +"having c.bi_cate =:cate"
            , nativeQuery = true)
    List<BurgerCateVo> searchBurger(@Param("cate") Long cate, @Param("store") Long store);

}
