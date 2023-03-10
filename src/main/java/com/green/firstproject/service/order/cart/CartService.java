// package com.green.firstproject.service.order.cart;

// import java.util.ArrayList;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Service;

// import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
// import com.green.firstproject.entity.order.cart.CartDetail;
// import com.green.firstproject.entity.order.cart.CartVo;
// import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
// import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
// import com.green.firstproject.repository.menu.option.SideOptionRepository;
// import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
// import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
// import com.green.firstproject.repository.stock.BurgerStockRepository;
// import com.green.firstproject.repository.stock.DogStockRepository;
// import com.green.firstproject.repository.stock.DrinkStockRepository;
// import com.green.firstproject.repository.stock.EventStockRepository;
// import com.green.firstproject.repository.stock.IngredientsStockRepository;
// import com.green.firstproject.repository.stock.SideStockRepository;
// import com.green.firstproject.vo.menu.MenuListVO;
// import com.green.firstproject.vo.menu.SellerMenuVO;
// import com.green.firstproject.vo.menu.cart.CartShowVO;
// import com.green.firstproject.vo.menu.option.DrinkOptionVO;
// import com.green.firstproject.vo.menu.option.SideOptionVO;

// @Service
// public class CartService {

//      private static Long seq=1L; //임의로 기본키 지정

//      @Autowired MenuInfoRepository menuRepo;
//      @Autowired EventInfoRepository eventRepo;
//      @Autowired SideOptionRepository soRepo;
//      @Autowired DrinkOptionRepository doRepo;
//      @Autowired IngredientsInfoRepository iiRepo;
//      @Autowired BurgerStockRepository bsRepo;
//      @Autowired DogStockRepository dogsRepo;
//      @Autowired DrinkStockRepository dsRepo;
//      @Autowired SideStockRepository ssRepo;
//      @Autowired IngredientsStockRepository isRepo;
//      @Autowired EventStockRepository esRepo;
//      @Autowired SideOptionRepository soptRepo;
//      @Autowired DrinkOptionRepository doptRepo;
     
//      // public Map<String, Object> addCart(
//      //       //멤버랑 스토어 로그인, 선택매장으로 바꿔야함
//      //      @Nullable Long menuSeq,
//      //      @Nullable Long eventSeq,
//      //      @Nullable Long sideOptSeq,
//      //      @Nullable Long drinkOptSeq,
//      //      @Nullable Long drinkOpt2Seq,
//      //      @Nullable Set<Long> ingredientsSeq,
//      //      Integer count
//      // ){
//      //      Map<String, Object> map = new LinkedHashMap<>();
//      //      CartDetail cart;
//      //      if((eventSeq!=null&&menuSeq!=null) || (eventSeq==null&&menuSeq==null)){ //메뉴와 이벤트메뉴 모두 선택되지않았거나 둘다 선택되었을경우 에러처리
//      //           map.put("status",  false);
//      //           map.put("message",  "주문 메뉴가 잘못되었습니다.");
//      //           map.put("code",  HttpStatus.BAD_REQUEST);
//      //           return map;
//      //      }else if(eventSeq!=null){ 
//      //           EventInfoEntity event = eventRepo.findByEventMenu(eventSeq);
//      //           cart= new CartDetail(seq, count, event);
//      //           if(sideOptSeq!=null){
//      //                SideOptionEntity sideOpt = soRepo.findBySoSeq(sideOptSeq); 
//      //                cart.setSideOpt(sideOpt); 
//      //           }
//      //           if(drinkOptSeq!=null){
//      //                DrinkOptionEntity drinkOpt = doRepo.findByDoSeq(drinkOptSeq);
//      //                cart.setDrinkOpt(drinkOpt);
//      //           }
//      //           if(drinkOpt2Seq!=null){
//      //                DrinkOptionEntity drinkOpt2 = doRepo.findByDoSeq(drinkOpt2Seq);
//      //                cart.setDrink2Opt(drinkOpt2);
//      //           }
//      //           cart.setTotalPrice();
//      //           map.put("message",  event.getEiName()+"을/를 카트에 담았습니다.");
//      //           return map;
               
//      //      }else if(menuSeq!=null){
//      //           MenuInfoEntity menu = menuRepo.findMenuSeq(menuSeq);
//      //           cart = new CartDetail(seq, count, menu); 
//      //           if(menu.getBurger()!=null && menu.getSide()!=null&&menu.getDrink()!=null){ //세트메뉴일경우
//      //                if(sideOptSeq!=null){
//      //                     SideOptionEntity sideOpt = soRepo.findBySoSeq(sideOptSeq);
//      //                     cart.setSideOpt(sideOpt);
//      //                }
//      //                if(drinkOptSeq!=null){
//      //                     DrinkOptionEntity drinkOpt = doRepo.findByDoSeq(drinkOptSeq);
//      //                     cart.setDrinkOpt(drinkOpt);
//      //                }
//      //           }
//      //           if(menu.getMenuSelect() && ingredientsSeq!=null){ //재료선택이 가능한 세트메뉴 + 추가한 재료가 있을 경우
//      //                System.out.println("iiii");
//      //                Set<IngredientsInfoEntity> list = iiRepo.findByingSeq(ingredientsSeq); //menu안에 lazy도 같이나감??
//      //                System.out.println("yyyy");
//      //                for(IngredientsInfoEntity i : list){
//      //                     cart.addIngredient(i);
//      //                }
//      //           }
//      //           cart.setTotalPrice();
//      //           map.put("message",  menu.getMenuName()+"을/를 카트에 담았습니다.");
//      //           map.put("cart",  cart);
//      //      }
//      //      map.put("status",  true);
//      //      map.put("code",  HttpStatus.ACCEPTED);
//      //      seq++; //임의로 지정한 기본키 늘려줌
//      //      return map;
//      // }

//      // // 매장 재고 검사
//      // public Map<String, Object> stockCheck(CartDetail c, StoreInfoEntity store){
//      //      Map<String, Object> map = new LinkedHashMap<>();
//      //      MenuInfoEntity menu = c.getMenu();
//      //      BurgerInfoEntity burger = menu.getBurger();
//      //      DogInfoEntity dog = menu.getDog();
//      //      DrinkInfoEntity drink = menu.getDrink();
//      //      SideInfoEntity side = menu.getSide();
//      //      EventInfoEntity event = c.getEventMenu();
//      //      boolean check = true;
//      //      String soldout = "";
//      //      if(burger!=null){
//      //           BurgerStockEntity bs = bsRepo.findByStoreAndBurger(store, burger);
//      //           if(bs.getBsStock()<c.getMenuCount()){
//      //                check = false;
//      //                soldout+=burger.getBiName();
//      //           }
//      //      }
//      //      if(dog!=null){
//      //           DogStockEntity dogstock = dogsRepo.findByStoreAndDog(store, dog);
//      //           if(dogstock.getDogsStock()<c.getMenuCount()){
//      //                if(!check){
//      //                     soldout+=", ";
//      //                }
//      //                check = false;
//      //                soldout+=dog.getDogName();
//      //           }
//      //      }
//      //      if(drink!=null){
//      //           DrinkStockEntity ds = dsRepo.findByStoreAndDrink(store, drink);
//      //           if(ds.getDsStock()<c.getMenuCount()){
//      //                if(!check){
//      //                     soldout+=", ";
//      //                }
//      //                check = false;
//      //                soldout+=drink.getDiName();
//      //           }
//      //      }
//      //      if(side!=null){
//      //           SideStockEntity ss = ssRepo.findByStoreAndSide(store, side);
//      //           if(ss.getSsStock()<c.getMenuCount()){
//      //                if(!check){
//      //                     soldout+=", ";
//      //                }
//      //                check = false;
//      //                soldout+=side.getSideName();
//      //           }
//      //      }
//      //      if(event!=null){
//      //           EventStockEntity es = esRepo.findByStoreAndEvent(store, event);
//      //           if(es.getEsStock()<c.getMenuCount()){
//      //                if(!check){
//      //                     soldout+=", ";
//      //                }
//      //                check = false;
//      //                soldout+=event.getEiName();
//      //           }
//      //      }
//      //      if(c.getIngredient().size()!=0){
//      //           Set<Long> ingSeqs = new HashSet<>();
//      //           for(IngredientVo i : c.getIngredient()){
//      //                ingSeqs.add(i.getIngredirentSeq());
//      //           }
//      //           List<IngredientsStockEntity> ing = isRepo.findStoreAndIngredient(store, ingSeqs);
//      //           for(IngredientsStockEntity i : ing){
//      //                if(i.getIsStock()<c.getMenuCount()){
//      //                     if(!check){
//      //                          soldout+=", ";
//      //                     }
//      //                     check = false;
//      //                     soldout+=i.getIngredient().getIiName();
//      //                }
//      //           }
//      //      }
//      //      if(check){
//      //           map.put("status", check);
//      //           map.put("message", "해당 메뉴를 카트에 담았습니다.");
//      //           map.put("code", HttpStatus.ACCEPTED);
//      //      }else{
//      //           map.put("status", check);
//      //           map.put("message", soldout+"은/는 현재 품절된 메뉴입니다. 해당 메뉴를 제외하고 다시 주문해주세요");
//      //           map.put("code", HttpStatus.BAD_REQUEST);
//      //      }
//      //      return map;
//      // }

//      //장바구니 조회
//      // public Map<String, Object> showCart(List<CartDetail> cart){
//      //      Map<String, Object> map = new LinkedHashMap<>();
//      //      if(cart==null || cart.size()==0){
//      //           map.put("status", false);
//      //           map.put("message", "카트에 담긴 메뉴가 없습니다.");
//      //           map.put("code", HttpStatus.OK);
//      //      }else{
//      //           List<CartVo> carts = new ArrayList<>();
//      //           for(CartDetail c : cart){
//      //                // MenuInfoEntity menu = menuRepo.findByMenuSeq(c.getMenu());
//      //                // SideOptionVO side = new SideOptionVO(soRepo.findBySoSeq(c.getSideOpt()));
//      //                // DrinkOptionVO drink = new DrinkOptionVO(doRepo.findByDoSeq(c.getDrinkOpt()));
//      //                // DrinkOptionVO drink2 = new DrinkOptionVO(doRepo.findByDoSeq(c.getDrink2Opt()));
//      //                // SellerMenuVO seller = new SellerMenuVO(menu, side, drink, drink2);
//      //                // CartShowVO cartShwo = new CartShowVO(c.getCount(), seller, null)
//      //                // Integer count = new 
//      //                // CartShowVO cartVO = new 
//      //                // c.setTotalPrice();
//      //                // c.ingredientFreeMenu();
//      //                // carts.add(new CartVo(c));
//      //           }

//      //           map.put("status", true);
//      //           map.put("message", "카트를 조회했습니다.");
//      //           map.put("code", HttpStatus.ACCEPTED);
//      //           map.put("cart", carts);
//      //      }
          
//      //      return map;
//      // }
     
//      // //장바구니 수량 변경
//      // public Map<String, Object> cartCountChange(CartDetail cart, int count){
//      //      Map<String, Object> map = new LinkedHashMap<>();
//      //      cart.setMenuCount(count);
//      //      map.put("status", true);
//      //      map.put("message", "메뉴를 수정하였습니다.");
//      //      map.put("code", HttpStatus.ACCEPTED);
//      //      // map.put("change", cart);
          
//      //      return map;
//      // }
//      // //장바구니 옵션 변경
//      // public Map<String, Object> cartOptionChange(CartDetail cart ,Long side, Long drink, Long drink2, Set<Long> ingredient){ 
//      //      Map<String, Object> map = new LinkedHashMap<>();
//      //      Boolean setMenu = cart.getMenu().getBurger()!=null && cart.getMenu().getSide()!=null && cart.getMenu().getDrink()!=null;
//      //      if(setMenu){
//      //           if(side!=null){
//      //                SideOptionEntity sideOpt = soptRepo.findBySoSeq(side);
//      //                cart.setSideOpt(sideOpt);
//      //           }
//      //           if(drink!=null){
//      //                DrinkOptionEntity drinkOpt = doptRepo.findByDoSeq(drink);
//      //                cart.setDrinkOpt(drinkOpt);
//      //           }
//      //      }else if(cart.getEventMenu()!=null){
//      //           if(side!=null){
//      //                SideOptionEntity sideOpt = soptRepo.findBySoSeq(side);
//      //                cart.setSideOpt(sideOpt);
//      //           }
//      //           if(drink!=null){
//      //                DrinkOptionEntity drinkOpt = doptRepo.findByDoSeq(drink);
//      //                cart.setDrinkOpt(drinkOpt);
//      //           }
//      //           if(drink2!=null){
//      //                DrinkOptionEntity drinkOpt2 = doptRepo.findByDoSeq(drink2);
//      //                cart.setDrinkOpt(drinkOpt2);
//      //           }
//      //      }
//      //      if(cart.getMenu().getMenuSelect()){
//      //           Set<IngredientVo> ing = new HashSet<>();
//      //           if(ingredient.size()!=0){
//      //                Set<IngredientsInfoEntity> list = iiRepo.findByingSeq(ingredient); //lazy 설정해놓은 연관관계매핑도 쿼리문 날아감           
//      //                for(IngredientsInfoEntity i : list){
//      //                     ing.add(new IngredientVo(i));
                         
//      //                }
//      //           }
//      //           cart.setIngredient(ing);
//      //      }else if(!cart.getMenu().getMenuSelect() && ingredient.size()!=0){ //재료 추가 불가능한 메뉴인데 재료추가를 시도했다면 에러처리
//      //           map.put("status", false);
//      //           map.put("message", "옵션을 변경할 수 없는 메뉴입니다.");
//      //           map.put("code", HttpStatus.BAD_REQUEST);
//      //           return map;
//      //      }
//      //      map.put("status", true);
//      //      map.put("message", "옵션을 변경하였습니다.");
//      //      map.put("code", HttpStatus.ACCEPTED);
//      //      return map;
//      // }
//      // //장바구니 메뉴 삭제
//      // public Map<String, Object> cartMenuDelete(List<CartDetail> carts ,Long seq){
//      //      Map<String, Object> map = new LinkedHashMap<>();
//      //      CartDetail cart = findCart(carts, seq);
//      //      carts.remove(cart);
//      //      map.put("status", true);
//      //      map.put("message", "카트에서 메뉴를 삭제했습니다.");
//      //      map.put("code", HttpStatus.ACCEPTED);
//      //      return map;
//      // }
//      // //선택 장바구니 찾기
//      // public CartDetail findCart(List<CartDetail> carts ,Long seq){
//      //      for(CartDetail c : carts){
//      //           if(c.getCartSeq() == seq){
//      //                return c;
//      //           }
//      //      }
//      //      return null;
//      // }
     
// }
