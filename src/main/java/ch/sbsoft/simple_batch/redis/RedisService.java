package ch.sbsoft.simple_batch.redis;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY = "mydata";

    public void saveSimpleRecord(String valore) {
        redisTemplate.opsForList().rightPush(KEY, valore);
    }

    public String getSimpleRecord() {
        return redisTemplate.opsForList().leftPop(KEY);
    }
    
    public List<String> getAllSimpleRecord() {
        Long size = redisTemplate.opsForList().size(KEY);
        if (size == null || size == 0) {
            return Collections.emptyList();
        }
        return redisTemplate.opsForList().range(KEY, 0, size - 1);
    }
}
