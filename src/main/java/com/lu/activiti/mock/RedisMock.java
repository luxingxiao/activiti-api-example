package com.lu.activiti.mock;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RedisMock {
  private Map<String, Object> cache = new HashMap<>();
  public void add(String key, Object value){
    cache.put(key,value);
  }
  public Object get(String key){
    return cache.get(key);
  }
}
