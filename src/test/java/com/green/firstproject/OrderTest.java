package com.green.firstproject;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberCouponEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;
import com.green.firstproject.repository.master.CouponInfoRepository;
import com.green.firstproject.repository.master.PaymentInfoRepository;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberCouponRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.repository.order.OrderDetailRepository;
import com.green.firstproject.repository.order.OrderInfoRepository;
import com.green.firstproject.repository.order.OrderIngredientsDetailRepository;
import com.green.firstproject.repository.stock.BurgerStockRepository;
import com.green.firstproject.repository.stock.IngredientsStockRepository;

@SpringBootTest
@Transactional
public class OrderTest {
     
     @Autowired MemberInfoReposiroty mRepo;
     @Autowired StoreInfoRepository siRepo;
     @Autowired MenuInfoRepository menuRepo;
     @Autowired EventInfoRepository eRepo;
     @Autowired SideOptionRepository soRepo;
     @Autowired DrinkOptionRepository diRepo;
     @Autowired IngredientsInfoRepository iiRepo;
     @Autowired OrderDetailRepository odRepo;
     @Autowired OrderInfoRepository oiRepository;
     @Autowired PaymentInfoRepository piRepo;
     @Autowired OrderIngredientsDetailRepository oidRepo;
     @Autowired BurgerStockRepository bsRepo;
     @Autowired IngredientsStockRepository isRepo;
     @Autowired CouponInfoRepository cRepo;
     @Autowired MemberCouponRepository mcRepo;

     @Test
     void ??????DB??????(){
          //??????
          PaymentInfoEntity pay = piRepo.findAll().get(0);
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "??????", "010-0000-0000", 1, LocalDate.now(), null, null);
          StoreInfoEntity store = new StoreInfoEntity(null, "????????????", "????????????", "????????????", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1,"?????????", null, null);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null, "?????????????????????", "?????? ?????? ?????????999??? ?????? 1???"); 

          mRepo.save(member);
          siRepo.save(store);

          oiRepository.save(order);

          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());

          OrderDetailEntity orderDetail = new OrderDetailEntity();
          orderDetail = orderDetail.builder().odCount(1).odBiseq(menu).odOiseq(order).build();
          
          odRepo.save(orderDetail);

          OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity(iiRepo.findAll().get(0), orderDetail);
          oidRepo.save(orderIngredient);

          OrderInfoEntity findOrder = oiRepository.findById(order.getOiSeq()).get();
          OrderDetailEntity findOrderDetail = odRepo.findById(orderDetail.getOdSeq()).get();
          OrderIngredientsDetailEntity findOrderIngredient = oidRepo.findById(orderIngredient.getOdiSeq()).get();

          //DB?????? ?????? ??????
          Assertions.assertThat(order).isEqualTo(findOrder);
          Assertions.assertThat(orderDetail).isEqualTo(findOrderDetail);
          Assertions.assertThat(orderIngredient).isEqualTo(findOrderIngredient);

     }

     @Test
     void ???????????????(){
          //??????
          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());

          //????????? ??????
          int originSales = menu.getBurger().getBiSalesRate();
          menu.getBurger().upSales(1);

          //????????? ?????? ??????
          Assertions.assertThat(originSales+1).isEqualTo(menu.getBurger().getBiSalesRate());
     }

     @Test
     void ????????????????????????(){
          //??????
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "??????", "010-0000-0000", 1, LocalDate.now(), null, null);
          mRepo.save(member);
          MemberCouponEntity coupon = new MemberCouponEntity(null, LocalDate.now(), true, member, cRepo.findAll().get(0));
          mcRepo.save(coupon);
          
          if(coupon.getMcUse() &&
               (coupon.getMcDate().getMonth()==LocalDate.now().getMonth() 
               && coupon.getMcDate().getYear()==LocalDate.now().getYear())
          ){
               coupon.setMcUse(false);
          }

          //?????? ???????????? ?????? ??????
          Assertions.assertThat(coupon.getMcUse()).isFalse();
     }
     @Test
     void ??????????????????????????????(){
          //??????
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "??????", "010-0000-0000", 1, LocalDate.now(), null, null);
          mRepo.save(member);

          //???????????? 1????????? ?????????
          MemberCouponEntity coupon = new MemberCouponEntity(null, LocalDate.now().minusYears(1), true, member, cRepo.findAll().get(0));

          mcRepo.save(coupon);
          
          if(coupon.getMcUse() &&
               (coupon.getMcDate().getMonth()==LocalDate.now().getMonth() 
               && coupon.getMcDate().getYear()==LocalDate.now().getYear())
          ){
               fail();
               coupon.setMcUse(false);
          }
     }

     @Test
     void ??????????????????????????????(){
          StoreInfoEntity store = new StoreInfoEntity(null, "????????????", "????????????", "????????????", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1,"?????????", null, null);
          siRepo.save(store);

          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());
          
          BurgerStockEntity burgerStock = new BurgerStockEntity(null, store, menu.getBurger(), 0);
          bsRepo.save(burgerStock);
          if(burgerStock.getBsStock()==1){
               fail();
          }
     }

     @Test
     void ????????????(){
          PaymentInfoEntity pay = piRepo.findAll().get(0);
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "??????", "010-0000-0000", 1, LocalDate.now(), null, null);
          StoreInfoEntity store = new StoreInfoEntity(null, "????????????", "????????????", "????????????", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1,"?????????", null, null);
          siRepo.save(store);

          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null, "?????????????????????", "?????? ?????? ?????????999??? ?????? 1???"); 


          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());
          
          int originSales = menu.getBurger().getBiSalesRate();

          if(order.getOiStatus()==1 || order.getOiStatus()==2){
               order.setOiStatus(5);

               menu.getBurger().setBiSalesRate(originSales+1);
          }else{
               fail();
          }

          Assertions.assertThat(5).isEqualTo(order.getOiStatus());
          Assertions.assertThat(originSales+1).isEqualTo(menu.getBurger().getBiSalesRate());

     }

     @Test
     void ??????????????????(){
          PaymentInfoEntity pay = piRepo.findAll().get(0);
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "??????", "010-0000-0000", 1, LocalDate.now(), null, null);
          StoreInfoEntity store = new StoreInfoEntity(null, "????????????", "????????????", "????????????", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1,"?????????", null, null);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 4, pay, null, "?????????????????????", "?????? ?????? ?????????999??? ?????? 1???"); 
          
          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());
          
          int originSales = menu.getBurger().getBiSalesRate();
          if(order.getOiStatus()==1 || order.getOiStatus()==2){
               fail();
               order.setOiStatus(5);
               
               menu.getBurger().setBiSalesRate(originSales+1);

          }

     }

     @Test
     void ??????????????????(){
          PaymentInfoEntity pay = piRepo.findAll().get(0);
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "??????", "010-0000-0000", 1, LocalDate.now(), null, null);
          StoreInfoEntity store = new StoreInfoEntity(null, "????????????", "????????????", "????????????", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1,"?????????", null, null);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null, "?????????????????????", "?????? ?????? ?????????999??? ?????? 1???"); 

          mRepo.save(member);
          siRepo.save(store);

          oiRepository.save(order);

          List<OrderInfoEntity> result = oiRepository.findByMember(member);

          Assertions.assertThat(result.size()).isEqualTo(1);
     }
}
