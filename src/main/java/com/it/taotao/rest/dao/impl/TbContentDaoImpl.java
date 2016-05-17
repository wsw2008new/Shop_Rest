package com.it.taotao.rest.dao.impl;

import com.it.taotao.dao.mapper.TbContentMapper;
import com.it.taotao.pojo.TbContent;
import com.it.taotao.pojo.TbContentExample;
import com.it.taotao.rest.dao.TbContentDao;
import com.it.train.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by 55 on 2016/5/16.
 */
@Repository
public class TbContentDaoImpl implements TbContentDao {

    @Autowired
    TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> getTbContentByCategoryId(Long categoryId) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
        if(CollectionUtil.isEmpty(list)){
            return Collections.emptyList();
        }else{
            return  list;
        }
    }
}
