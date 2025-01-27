package com.bantanger.extension.thift;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Setter
@Getter
public class SmsSendModel {

    private List<String> phones;
    private Map<String, String> params;
    private String templateCode;

}
