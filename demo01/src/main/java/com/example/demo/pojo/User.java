package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@TableName("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @TableId(type = IdType.AUTO) //解决字段自增长
    private Long id;
    private String name;
    @TableField(select=false) //解决隐私问题，查询数据的时候不查询出来
    private Integer age;
    @TableField(value="email")//解决属性与字段不一致的方法
    private String mail;
    @TableField(exist=false) //解决属性在数据库中不存在的状态
    private String address;



}