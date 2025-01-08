package com.bantanger.elegant.pipeline.selector;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
public interface FilterSelector {

    /**
     * filter 匹配
     * @param currentFilterName
     * @return
     */
    boolean matchFilter(String currentFilterName);

    /**
     * 获取所有 filter 的名称
     * @return
     */
    List<String> getFilterNames();
}
