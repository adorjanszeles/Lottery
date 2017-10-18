package com.lottery.repository;

import com.lottery.model.WeeklyDraw;
import org.drools.core.reteoo.CompositeObjectSinkAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class WeeklyDrawRepositoryImpl implements WeeklyDrawRepository {

    private static final String KEY = "WeeklyDraw";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public WeeklyDrawRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(final WeeklyDraw weeklyDraw) {
        hashOperations.put(KEY, weeklyDraw.getId(), weeklyDraw);
    }

    public void update(final WeeklyDraw weeklyDraw) {
        hashOperations.put(KEY, weeklyDraw.getId(), weeklyDraw);
    }

    public void saveAll(final Map weeklyDrawList){hashOperations.putAll(KEY, weeklyDrawList); }

    public WeeklyDraw find(final String id) {
        return (WeeklyDraw) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> findAll() {
        return hashOperations.entries(KEY);
    }

    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    public Long size(){return  hashOperations.size(KEY);}

    public List<CompositeObjectSinkAdapter.HashKey> values() {
        return hashOperations.values(KEY);
    }
}
