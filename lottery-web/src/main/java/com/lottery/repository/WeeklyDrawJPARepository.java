package com.lottery.repository;

import com.lottery.model.WeeklyDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * WeeklyDraw repository
 */
@Transactional
public interface WeeklyDrawJPARepository extends JpaRepository<WeeklyDraw, Long> {

    /**
     * weeklyDraw-k dátum szerinti szűrése
     *
     * @param from dátum intervallum kezdete
     * @param to dátum intervallum vége
     * @return WeeklyDraw-kat tartalmazó lista a megadott időintervallumban
     */
    List<WeeklyDraw> findWeeklyDrawByDrawDateAfterAndDrawDateBefore(Date from, Date to);

    /**
     * @return a legkorábbi húzás dátuma
     */
    @Query("SELECT min(d.drawDate) FROM WeeklyDraw d")
    Date minDate();

    /**
     * @return a legújabb húzás dátuma
     */
    @Query("SELECT max(p.drawDate) FROM WeeklyDraw p")
    Date maxDate();

}
