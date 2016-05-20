package com.it.taotao.rest.dao.impl;

import com.it.taotao.dao.mapper.TbItemMapper;
import com.it.taotao.pojo.TbItem;
import com.it.taotao.pojo.TbItemExample;
import com.it.taotao.rest.dao.TbItemDao;
import com.it.train.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 55 on 2016/5/20.
 */
@Repository
public class TbItemDaoImpl implements TbItemDao {

    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public TbItem getTbItemByItemId(Long itemId) {
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria  criteria = tbItemExample.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
        if(!CollectionUtil.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }
}
