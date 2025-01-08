package com.bantanger.elegant.pipeline.selector;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Objects;

/**
 * @author chensongmin
 * @description 基于本地列表的 Filter 选择器
 * @create 2025/1/4
 */
public class LocalListBasedFilterSelector implements FilterSelector {

    private List<String> filterNames = Lists.newArrayList();

    public LocalListBasedFilterSelector() {
    }

    public LocalListBasedFilterSelector(List<String> filterNames) {
        this.filterNames = filterNames;
    }

    @Override
    public boolean matchFilter(String currentFilterName) {
        return filterNames.stream().anyMatch(s -> Objects.equals(s, currentFilterName));
    }

    @Override
    public List<String> getFilterNames() {
        return filterNames;
    }

    public void addFilter(String clsName) {
        filterNames.add(clsName);
    }

    public void addFilterAll(List<String> filterNameList) {
        filterNames.addAll(filterNameList);
    }

}
