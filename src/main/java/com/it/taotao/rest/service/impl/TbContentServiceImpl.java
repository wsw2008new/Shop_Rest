package com.it.taotao.rest.service.impl;

import com.it.taotao.pojo.TbContent;
import com.it.taotao.rest.component.JedisClient;
import com.it.taotao.rest.dao.TbContentDao;
import com.it.taotao.rest.service.TbContentService;
import com.it.train.po.TaotaoResult;
import com.it.train.util.ExceptionUtil;
import com.it.train.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 55 on 2016/5/16.
 */
@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    TbContentDao tbContentDao;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;

    @Override
    public TaotaoResult getTbContentByCategoryId(Long categoryId) {
        try{
            String listTbContentString = jedisClient.hget(REDIS_CONTENT_KEY, categoryId + "");
            if(!StringUtils.isBlank(listTbContentString)){
                List<TbContent> list = JsonUtils.jsonToList(listTbContentString, TbContent.class);
                return TaotaoResult.ok(list);
            }
        }catch (Exception e){
            ExceptionUtil.getStackTrace(e);
        }


        List<TbContent> list = tbContentDao.getTbContentByCategoryId(categoryId);

        try{
            /**
             * 选用散列的原因:
             * Reids很多部门都在用，要加以区别。
             * */
            jedisClient.hset(REDIS_CONTENT_KEY, categoryId + "", JsonUtils.objectToJson(list));
        }catch (Exception e){
            ExceptionUtil.getStackTrace(e);
        }

        return TaotaoResult.ok(list);
    }

    @Override
    public TaotaoResult clearTbContentCacheByCategoryId(Long categoryId) {
        Long number = jedisClient.hdel(REDIS_CONTENT_KEY, categoryId + "");
        return TaotaoResult.ok(number);
    }


}
