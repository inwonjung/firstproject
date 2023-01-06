package com.green.firstproject.service.menu;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.vo.menu.HiaBurgerAddVO;
import com.green.firstproject.vo.menu.HiaDogAddVO;
import com.green.firstproject.vo.menu.HiaDrinkAddVO;
import com.green.firstproject.vo.menu.HiaIngredAddVO;
import com.green.firstproject.vo.menu.HiaSideAddVO;

@Service
public class HiaBurgerService {
    @Autowired BurgerInfoRepository bRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired SideInfoRepository sideRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired IngredientsInfoRepository iRepo;

    public Map<String,Object> addBurger(HiaBurgerAddVO data){ 
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            BurgerInfoEntity entity = new BurgerInfoEntity(data);
            CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
            if(bRepo.countByBiName(entity.getBiName()) != 0){
                resultMap.put("status", false);
                resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
            else{
                entity.setCategory(cate);
                bRepo.save(entity);
                resultMap.put("status", true);
                resultMap.put("message", "버거 정보가 등록되었습니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
            }
        return resultMap;
        }

     public Map<String, Object> addSide(HiaSideAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SideInfoEntity entity = new SideInfoEntity(data);
        CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
        if(sideRepo.countBySideName(entity.getSideName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            entity.setCategory(cate);
            sideRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "사이드 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
     }   

     public Map<String, Object> addDrink(HiaDrinkAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DrinkInfoEntity entity = new DrinkInfoEntity(data);
        CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
        if(dRepo.countByDiName(entity.getDiName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            entity.setCategory(cate);
            dRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "음료 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
     }

     public Map<String, Object> addDog(HiaDogAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DogInfoEntity entity = new DogInfoEntity(data);
        CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
        if(dogRepo.countByDogName(entity.getDogName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            entity.setCategory(cate);
            dogRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "독퍼 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
     }

     public Map<String, Object> addIngredients(HiaIngredAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        IngredientsInfoEntity entity = new IngredientsInfoEntity(data);
        if(iRepo.countByIiName(entity.getIiName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            iRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "재료 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
     }
}