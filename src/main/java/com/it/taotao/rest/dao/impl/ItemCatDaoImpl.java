package com.it.taotao.rest.dao.impl;

import com.it.taotao.dao.mapper.TbItemCatMapper;
import com.it.taotao.pojo.TbContentCategoryExample;
import com.it.taotao.pojo.TbItemCat;
import com.it.taotao.pojo.TbItemCatExample;
import com.it.taotao.rest.dao.ItemCatDao;
import com.it.taotao.rest.pojo.CatNode;
import com.it.taotao.rest.pojo.ItemCatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 55 on 2016/5/12.
 */
@Repository
public class ItemCatDaoImpl implements ItemCatDao{

    @Autowired
    TbItemCatMapper tbItemCatMapper;

    @Override
    public ItemCatResult getItemCatResult() {
        ItemCatResult itemCatResult = new ItemCatResult();
        List<CatNode> catNodeList = getCatList(0L);
        itemCatResult.setData(catNodeList);
        return itemCatResult;
    }

    public List getCatList(Long parentId){
        TbItemCatExample tbContentCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbContentCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCatSubList = tbItemCatMapper.selectByExample(tbContentCatExample);
        List dataList = new ArrayList();
        for(TbItemCat tbItemCat:itemCatSubList){
            CatNode catNode = new CatNode();
            if(tbItemCat.getIsParent()){
                catNode.setName("/products/" + tbItemCat.getId() + ".html");
                //如果当前节点为第一级节点
                if (tbItemCat.getParentId() == 0) {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                catNode.setItems(getCatList(tbItemCat.getId()));
                dataList.add(catNode);
            }else{
                String catItem = "/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
                dataList.add(catItem);

            }
        }
        return dataList;
    }

    /**
     * Mybatis 连接模式是单例的。
     * 一个 ***Example一次只能查询一次数据。对于三级循环表，只能采用递归的方式来查询
     * */
    /*public List<CatNode> getItemCatSubList( List<TbItemCat> listTbItemCat){
        TbItemCatExample tbContentCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbContentCatExample.createCriteria();
        List<CatNode> listCatNode = new ArrayList<CatNode>();

        for(TbItemCat tbItemCat: listTbItemCat){
            //一级目录
            CatNode catNodeFirst = new CatNode();
            catNodeFirst.setUrl("/products/" + tbItemCat.getId() + ".html");
            catNodeFirst.setName("<a href='/products/" + tbItemCat.getId() +".html" + tbItemCat.getName() + "</a>");
            List<CatNode> listSub = new ArrayList<CatNode>();

            long id = tbItemCat.getId();
            criteria.andParentIdEqualTo(id);
            List<TbItemCat> listTbItemCatSubList = tbItemCatMapper.selectByExample(tbContentCatExample);
            for(TbItemCat tbItemCatSub: listTbItemCatSubList){

                //二级目录
                CatNode catNodeSecond = new CatNode();
                catNodeSecond.setName(tbItemCatSub.getName());
                catNodeSecond.setUrl("/products/" + tbItemCatSub.getId() + ".html");


                criteria.andParentIdEqualTo(tbItemCatSub.getParentId());
                List<TbItemCat> listTbItemCatThreeList = tbItemCatMapper.selectByExample(tbContentCatExample);
                List<String> listString = new ArrayList<String>();
                for(TbItemCat tbItemCatThree:listTbItemCatThreeList){
                    listString.add("/products/" + tbItemCatThree.getId() + ".html|" + tbItemCatThree.getName());
                }
                catNodeSecond.setItems(listString);
                listSub.add(catNodeSecond);
            }
            catNodeFirst.setItems(listSub);
            listCatNode.add(catNodeFirst);
        }
        return  listCatNode;
    }*/
}
