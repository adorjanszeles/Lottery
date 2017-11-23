package com.lottery.repository;

import com.lottery.model.WeeklyDraw;
import org.drools.core.reteoo.CompositeObjectSinkAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * A Redis adatbázisunk vezérlését végző osztály.
 */
@Repository
public class WeeklyDrawRedisRepositoryImpl implements WeeklyDrawRedisRepository {

    private static final String KEY = "WeeklyDraw";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public WeeklyDrawRedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(final WeeklyDraw weeklyDraw) {
        this.hashOperations.put(KEY, weeklyDraw.getRedisId(), weeklyDraw);
    }

    public void update(final WeeklyDraw weeklyDraw) {
        this.hashOperations.put(KEY, weeklyDraw.getRedisId(), weeklyDraw);
    }

    public Map<Object, Object> findAll() {
        return this.hashOperations.entries(KEY);
    }

    public void delete(final String id) {
        this.hashOperations.delete(KEY, id);
    }

    public void saveAll(final Map weeklyDrawList) {
        this.hashOperations.putAll(KEY, weeklyDrawList);
    }

    public Long size() {
        return this.hashOperations.size(KEY);
    }

    public WeeklyDraw find(final String id) {
        return (WeeklyDraw) this.hashOperations.get(KEY, id);
    }

    public List<CompositeObjectSinkAdapter.HashKey> values() {
        return this.hashOperations.values(KEY);
    }
}
