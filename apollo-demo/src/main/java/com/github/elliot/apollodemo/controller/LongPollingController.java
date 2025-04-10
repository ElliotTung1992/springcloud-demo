package com.github.elliot.apollodemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 如何用Java实现一个长轮询
 */
@RequestMapping("/long-polling")
@RestController
public class LongPollingController {

    // 创建map
    private final Map<String, DeferredResult<ResponseEntity<String>>> pendingMap = new ConcurrentHashMap<>();

    // 存储最新数据
    private String lastData = null;

    @GetMapping("/poll")
    public DeferredResult<ResponseEntity<String>> poll(@RequestParam String clientId) {
        // 创建DeferredResult对象
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>(300_000L);
        // 设置超时处理逻辑
        deferredResult.onTimeout(() -> {
            pendingMap.remove(clientId);
            deferredResult.setResult(ResponseEntity.ok("TimeOut"));
        });
        // 创建完成处理逻辑
        deferredResult.onCompletion(() -> {
            pendingMap.remove(clientId);
        });
        if(StringUtils.isEmpty(lastData)){
            pendingMap.put(clientId, deferredResult);
        } else {
            deferredResult.setResult(ResponseEntity.ok(lastData));
            lastData = null;
        }
        return deferredResult;
    }

    /**
     * 模拟数据更新接口
     * @param newData 新数据
     * @return 操作结果
     */
    @GetMapping("/update")
    public String updateData(@RequestParam String newData) {
        lastData = newData;
        pendingMap.forEach((k,v) -> {
            v.setResult(ResponseEntity.ok(newData));
        });
        pendingMap.clear();
        lastData = null;
        return "newData:" + newData;
    }
}
