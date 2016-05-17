package com.it.taotao.rest.Controller;

import com.it.taotao.rest.service.TbContentService;
import com.it.train.po.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 55 on 2016/5/16.
 */
@RequestMapping("/rest/content")
@Controller
public class TbContentController {

    @Autowired
    TbContentService tbContentService;

    @RequestMapping("/{cid}")
    @ResponseBody
    public TaotaoResult getTbContent(@PathVariable Long cid){
        return tbContentService.getTbContentByCategoryId(cid);
    }

    @RequestMapping("clean/{cid}")
    @ResponseBody
    public TaotaoResult cleanTbContentCache(@PathVariable Long cid){
        return tbContentService.clearTbContentCacheByCategoryId(cid);
    }
}
