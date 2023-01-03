package com.green.firstproject.repository.menu.basicmenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
@Repository
public interface SideInfoRepository extends JpaRepository<SideInfoEntity, Long> {
        
}
