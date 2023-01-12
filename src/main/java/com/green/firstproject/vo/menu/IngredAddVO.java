package com.green.firstproject.vo.menu;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredAddVO {
    @Autowired IngredientsInfoRepository iRepo;
    @Autowired CategoryRepository cateRepo;
    private String name;
    private Integer price;
    private String file;
    private String uri; 
}