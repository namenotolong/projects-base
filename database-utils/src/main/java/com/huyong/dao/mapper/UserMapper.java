package com.huyong.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huyong.dao.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User get(@Param("id") Integer id);

}

