package com.frank.blog.service;

import com.frank.blog.dao.model.BlogInfo;

/**
 * @author Chenxx
 * @Title: IBlogInfoService <br>
 * @Description: <br>
 * @date 2019/4/8 17:10
 */

public interface IBlogInfoService {

      BlogInfo getOne(Integer id);
}
