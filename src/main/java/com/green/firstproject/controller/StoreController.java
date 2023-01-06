package com.green.firstproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.service.store.StoreInfoService;

import io.micrometer.common.lang.Nullable;

@RestController
public class StoreController {
    @Autowired StoreInfoRepository siRepo;
    @Autowired StoreInfoService siService;
    @GetMapping("/store")
    public ResponseEntity<Object> storeInfo(@PageableDefault(size = 8) Pageable pageable, String keyword){
    Map<String, Object> resultMap = siService.getStoreInfo(pageable, keyword);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    @GetMapping("/search/{type}")
    public ResponseEntity<Object> getStoreSearch (
        @PageableDefault(size = 8) Pageable pageable, @RequestParam @Nullable String keyword,
        @PathVariable @Nullable String type) {
            // if(type.equals("name")
            if (type == null) {
                return new ResponseEntity<>(siService.getStoreInfo(pageable, keyword), HttpStatus.BAD_REQUEST);
            }
            else if(type.equals("name")){
                return new ResponseEntity<>(siService.getStoreDetailInfo(pageable, keyword), HttpStatus.OK);
            }
            return null;
        }
}