package com.yuuki.starter.redis;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isMember(String key, String member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * 删除key 可批量
     *
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 无过期时间缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 有过期时间缓存
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 设置hashmap到redis
     *
     * @param key key值
     * @param map map
     * @return
     */
    public Boolean setMap(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加 hash 键值对. 存在覆盖
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public void put(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 添加 hash 键值对. 不存在的时候才添加
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean putIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }


    /**
     * 删除指定 hash 的 HashKey
     *
     * @param key
     * @param hashKeys
     * @return 删除成功的 数量
     */
    public Long delete(String key, String... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }


    /**
     * 给指定 hash 的 hashkey 做增减操作
     *
     * @param key
     * @param hashKey
     * @param number
     * @return
     */
    public Long increment(String key, String hashKey, long number) {
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 获取指定 key 下的 hashkey 的value
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object getHashValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取指定key下所有的hash key
     *
     * @param key
     * @return
     */
    public List<String> getKeys(String key) {
        return Arrays.asList(redisTemplate.opsForHash().keys(key).stream().map(strObj -> (String) strObj).toArray(String[]::new));
    }

    /**
     * 验证指定 key 下 有没有指定的 hashkey
     *
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * set 集合中放入value
     *
     * @param key
     * @param values
     */
    public void addValue(String key, String... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    public void addValue(String key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
    }


    public void addValueForList(String key, Object... values) {
        redisTemplate.opsForList().leftPush(key, values);
    }

    /**
     * set 集合移除value
     *
     * @param key
     * @param values
     */
    public void removeValue(String key, String... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    public void removeValue(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 删除list内的指定元素
     *
     * @param key
     * @param values
     */
    public void removeValueForList(String key, Object... values) {
        redisTemplate.opsForList().remove(key, 1, values);
    }

    /**
     * 获取指定key 的 set 集合元素个数
     *
     * @param key
     * @return
     */
    public Long getSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }


    /**
     * 获取list长度
     *
     * @param key
     * @return
     */
    public Long getSizeForList(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 获取指定key set集合的所有元素
     *
     * @param key
     * @return
     */
    public Set<Object> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * Set扫描
     *
     * @param key
     * @param filter
     * @return
     */
    public <T> List<T> scan(String key, Filter<T> filter) {
        List<T> list = new ArrayList<>();
        Cursor<Object> cursor = redisTemplate.opsForSet().scan(key, ScanOptions.NONE);
        while (cursor.hasNext()) {
            T next = (T) cursor.next();
            if (filter.accept(next)) {
                list.add(next);
            }
        }
        return list;
    }


    /**
     * 获取指定下标范围的子列表 （按闭区间获取）
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public <T> List<T> subList(String key, Long start, Long end) {
        return (List<T>) redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 求两个集合的交集
     *
     * @param key1
     * @param otherKey
     * @param key2
     * @return
     */
    public Long intersect(String key1, String otherKey, String key2) {
        return redisTemplate.opsForSet().intersectAndStore(key1, otherKey, key2);
    }

    /**
     * 随机获取一个集合元素
     *
     * @param key
     * @return
     */
    public Object randomMenber(String key) {

        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取list中的一个元素
     *
     * @param key
     * @return
     */
    public Object randomMenberFromList(String key) {
        if (redisTemplate.hasKey(key) && redisTemplate.opsForList().size(key) > 0) {
            return redisTemplate.opsForList().index(key, RandomUtils.nextInt(0, Math.toIntExact(redisTemplate.opsForList().size(key))));
        }
        return null;
    }

    /**
     * 获取所有前缀的key
     *
     * @param keyword
     * @return
     */
    public Set<String> keys(String keyword) {
        return redisTemplate.keys(keyword);
    }

    /**
     * 过滤器
     */
    public interface Filter<T> {
        boolean accept(T object);
    }

}
