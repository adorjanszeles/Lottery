package com.lottery.repository;


import com.lottery.model.WeeklyDraw;
import org.drools.core.reteoo.CompositeObjectSinkAdapter;

import java.util.List;
import java.util.Map;

//@Repository - to try CrudRepository but it wont work
public interface WeeklyDrawRepository {

    void save(WeeklyDraw weeklyDraw);

    void update(WeeklyDraw weeklyDraw);

    Map<Object, Object> findAll();

    void delete(String id);

    void saveAll(Map weeklyDrawList);

    Long size();

    WeeklyDraw find(final String id);

    List<CompositeObjectSinkAdapter.HashKey> values();

}