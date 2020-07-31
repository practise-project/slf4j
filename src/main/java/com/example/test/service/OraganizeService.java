package com.example.test.service;

import com.example.test.model.Organize;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author ClowLAY
 * create date 2020/7/14
 */
@Service
public class OraganizeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OraganizeService.class);

    //用于读取数据库
    private MongoTemplate mongoTemplate;

    ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public OraganizeService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 获取全部组织列表（按组织层次返回json格式）
     * @return
     */
    public JsonNode getAllOrganize() {
        regex();
        var root = objectMapper .createObjectNode();
        root.put("code", "0");
        var query = new Query(Criteria.where("pid").is('0'));
        var organizeList= mongoTemplate.find(query, Organize.class);
        var data = objectMapper.createArrayNode();
        organizeList.forEach(organize -> data.add(convertOrganizeToJson(organize)));
        root.put("msg", "success");
        root.set("data", data);
        LOGGER.warn("产生一条警告日志用于测试");
        LOGGER.error("产生一条错误日志用于测试");
        return root;
    }

    /**
     * 将Organize转化为JSON
     * @param organize
     * @return
     */
    private ObjectNode convertOrganizeToJson(Organize organize) {

        var root = objectMapper.createObjectNode();
        root.put("_id", organize.getId().toString());
        root.put("pid", organize.getPId());
        root.put("org_type", organize.getOrgType());
        root.put("org_name", organize.getOrgName());
        root.put("org_code", organize.getOrgCode());
        root.put("org_level", organize.getOrgLevel());
        var array = objectMapper.createArrayNode();
        var query = new Query(Criteria.where("pid").is(organize.getId().toString()));
        var organizeList = mongoTemplate.find(query, Organize.class);
        if (organizeList.size()>0){
            //使用递归遍历下一级组织
            organizeList.forEach(organize1 -> {
                var  item = convertOrganizeToJson(organize1);
                array.add(item);
            });
            root.set("children",array);
        }

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
