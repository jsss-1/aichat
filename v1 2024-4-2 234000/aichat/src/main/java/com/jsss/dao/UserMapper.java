package com.jsss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jsss.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jsss
 * @since 2024年02月28日
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
