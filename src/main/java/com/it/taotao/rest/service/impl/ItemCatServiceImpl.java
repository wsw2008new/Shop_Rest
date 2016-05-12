package com.it.taotao.rest.service.impl;

import com.it.taotao.rest.dao.ItemCatDao;
import com.it.taotao.rest.pojo.ItemCatResult;
import com.it.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 55 on 2016/5/12.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    ItemCatDao itemCatDao;
    @Override
    public ItemCatResult getItemCatResult() {
        ItemCatResult itemCatResult = itemCatDao.getItemCatResult();
        return itemCatResult;
    }
}
