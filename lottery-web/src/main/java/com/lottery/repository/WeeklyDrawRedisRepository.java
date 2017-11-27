package com.lottery.repository;

import com.lottery.model.WeeklyDraw;
import org.drools.core.reteoo.CompositeObjectSinkAdapter;

import java.util.List;
import java.util.Map;

/**
 * Inteface a WeeklyDrawRedisRepository implementációhoz.
 */
public interface WeeklyDrawRedisRepository {

    void save(WeeklyDraw weeklyDraw);

    void update(WeeklyDraw weeklyDraw);

    Map<Object, Object> findAll();

    void delete(String id);

    void saveAll(Map weeklyDrawList);

    Long size();

    WeeklyDraw find(String id);

    List<CompositeObjectSinkAdapter.HashKey> values();

}