package com.it.taotao.rest.Controller;

import com.it.taotao.rest.pojo.ItemCatResult;
import com.it.taotao.rest.service.ItemCatService;
import com.it.train.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 55 on 2016/5/12.
 */
@Controller
@RequestMapping("/rest/itemcat")
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;

    /**
     * Spring Mvc 对Jsonp的响应方式
     * 先把ItemCatResult对象转换成json字符串，
     * 然后使用字符串拼接的方法拼装成jsonp格式的数据。
     * 需要设置相应结果的MediaType。
     * */
    @RequestMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemCatAll(String callback){
        ItemCatResult itemCatResult = itemCatService.getItemCatResult();
        //把对象转换成json数据
        String jsonResult = JsonUtils.objectToJson(itemCatResult);
        //拼接字符串
        String resultStr = callback + "(" + jsonResult + ");";
        return resultStr;
    }

}
