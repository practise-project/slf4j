package com.example.test.service;

import com.example.test.common.annotation.AroundMonitor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author ClowLAY
 * create date 2020/7/14
 */
@Service
public class OraganizeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OraganizeService.class);


    ObjectMapper objectMapper = new ObjectMapper();



    /**
     * 用于测试（返回json格式）
     * @return
     */
    @AroundMonitor("测试方法")
    public JsonNode test(String name) {
        regex();
        var root = objectMapper .createObjectNode();
        root.put("result", "success");
        LOGGER.warn("产生一条警告日志用于测试");
        LOGGER.error("产生一条错误日志用于测试");
        return root;
    }

    /**
     * 正则匹配(获取字符中应用程序名称的值)
     */
    public String regex(){
        var str="应用程序信息:   进程 ID:  4580   应用程序名称: \\device\\harddiskvolume3\\windows\\system32\\dashost.exe  " +
                "  网络信息:   方向:  入站   源地址:  172.168.1.39   源端口:  52963   目标地址: 239.255.255.250 " +
                "  目标端口:  3702   协议:  17 ";

        var pattern = Pattern.compile(".*?应用程序名称:\\s*?(.*?exe)\\s"); //第一种匹配
        var matcher = pattern.matcher(str);
        var bool = matcher.find();
        if (!bool){
            System.out.println("未匹配到满足条件数据");
            return "";
        }else{
            System.out.println(matcher.group(1));

            return matcher.group(1);
        }

    }

}
