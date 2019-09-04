package com.lu.activiti.mock;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RedisMock {
  private Map<String, Object> processKeyCache = new HashMap<>();
  private Map<String, Object> verifyCodeCache = new HashMap<>();
  public void addProcessKey(String key, Object value){
    processKeyCache.put(key,value);
  }
  public Object getProcessKey(String key){
    return processKeyCache.get(key);
  }

  public void addVerifyCode(String key, Object value){
    verifyCodeCache.put(key, value);
  }

  public Object getVerifyCode(String key){
    return verifyCodeCache.get(key);
  }
}
