package com.frank.blog.service.impl;

import com.frank.blog.dao.mapper.BlogInfoMapper;
import com.frank.blog.dao.model.BlogInfo;
import com.frank.blog.service.IBlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chenxx
 * @Title: BlogInfoServiceImpl <br>
 * @Description: <br>
 * @date 2019/4/8 17:10
 */
@Service("blogInfoService")
public class BlogInfoServiceImpl implements IBlogInfoService {

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public BlogInfo getOne(Integer id) {
        return blogInfoMapper.selectByPrimaryKey(id);
    }
}
