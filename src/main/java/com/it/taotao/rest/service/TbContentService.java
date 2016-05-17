package com.it.taotao.rest.service;

import com.it.train.po.TaotaoResult;

/**
 * Created by 55 on 2016/5/16.
 */
public interface TbContentService {
    public TaotaoResult getTbContentByCategoryId(Long categoryId);
    public TaotaoResult clearTbContentCacheByCategoryId(Long categoryId);
}
