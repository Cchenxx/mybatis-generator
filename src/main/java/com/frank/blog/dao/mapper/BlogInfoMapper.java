package com.frank.blog.dao.mapper;

import com.frank.blog.dao.model.BlogInfo;
import com.frank.blog.dao.model.BlogInfoExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BlogInfoMapper extends Mapper<BlogInfo> {
    int countByExample(BlogInfoExample example);

    int updateByExampleSelective(@Param("record") BlogInfo record, @Param("example") BlogInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") BlogInfo record, @Param("example") BlogInfoExample example);

    int updateByExample(@Param("record") BlogInfo record, @Param("example") BlogInfoExample example);
}