package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  ml
 */
@Mapper
public interface  UserMapper extends BaseMapper<User> {
    User findByIdML(Long id);
}
