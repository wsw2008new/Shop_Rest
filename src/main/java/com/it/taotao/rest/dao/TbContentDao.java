package com.it.taotao.rest.dao;

import com.it.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by 55 on 2016/5/16.
 */
public interface TbContentDao {
    public List<TbContent> getTbContentByCategoryId(Long categoryId);
}
