package com.frank.blog.service.impl;

import com.frank.MybatisGereratorApplication;
import com.frank.blog.dao.model.BlogInfo;
import com.frank.blog.service.IBlogInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Chenxx
 * @Title: BlogInfoServiceImplTest <br>
 * @Description: <br>
 * @date 2019/4/8 17:12
 */
@SpringBootTest(classes = MybatisGereratorApplication.class)
@RunWith(SpringRunner.class)
public class BlogInfoServiceImplTest {
    @Autowired
    private IBlogInfoService blogInfoService;

    @Test
    public void getOne() throws Exception {
        BlogInfo one = blogInfoService.getOne(2);
        System.out.println(one.toString());
    }

}