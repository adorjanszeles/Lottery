package com.lottery.repository;

import com.lottery.model.WeeklyDraw;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WeeklyDrawJPARepository extends JpaRepository<WeeklyDraw,String>{

}
