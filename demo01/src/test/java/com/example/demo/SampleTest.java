package com.example.demo;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

/**
 * Demo class
 *
 * @author mlNothing
 * @date 2016/10/31
 */
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
//    Assert.assertEquals();及其重载方法: 1. 如果两者一致, 程序继续往下运行. 2. 如果两者不一致, 中断测试方法, 抛出异常信息
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
}

        /* 插入一条数据*/
    @Test
    public void  saveOne(){
        User user = new User();
        user.setAge(12);
        user.setMail("12.qq.com");
        user.setName("张三");
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user.getId());//1430797745800912898 这样的话生成的id的值不是自动增长的 ，我们需要设置mp的自增长的策略
//        修改自动增长的策略
//        1.在属性的加上@TableId(type = IdType.AUTO) 主键注解
//        2.设计表-选项-自动增长 改为 7
    }

//    更新数据
    @Test
    public void updateById(){
        User user = new User();
//        要更新数据的的条件
        user.setId(1L);
//        要更新数据的字段
        user.setAge(30);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
/**
 * 根据 whereEntity 条件，更新记录
 *
 * @param *entity 实体对象 (set 条件值,可以为 null)
 * @param *updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
 * int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T>
 * updateWrapper);
 */
    @Test
    public  void updateQeryWrapper(){
        User user = new User();
        user.setName("王五");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",1);// column:数据库的字段
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
//        或者使用updateWrapper 在下面表示
    }
    @Test
    public  void updateWrapper(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",1).set("name","幺六");
        userMapper.update(null,wrapper);
    }
//    删除一条数据
    @Test
    public void  deleteById(){
        int i = userMapper.deleteById(1);
        System.out.println(i);
    }
    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     *  int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
     */
    @Test
    public  void deleteByMap(){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("age",20);
        columnMap.put("name","Jack");
        int i = userMapper.deleteByMap(columnMap);
        System.out.println(i);
    }

    /**
     * 根据 entity 条件，删除记录
     *int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
     * @param wrapper 实体对象封装操作类（可以为 null）
     */

    @Test
    public void delete(){
        User user = new User();
        user.setAge(18);
        user.setName("Jone");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);//如果什么都不传的话，会全部删掉
        int delete = userMapper.delete(wrapper);
        System.out.println(delete);
    }
    /**
     * 删除（根据ID 批量删除）
     *   int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    @Test
    public  void deleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1L, 2L));
        System.out.println(i);
    }
//    查询数据
    @Test
    public void selectID(){
//        根据id查询数据
        User user = userMapper.selectById(3);
        System.out.println(user);//User(id=3, name=Tom, age=null, mail=test3@baomidou.com, address=null)
//         根据主键查询多个
        List<User> users = userMapper.selectBatchIds(Arrays.asList(4L, 5L, 6L));
        System.out.println(users);
        //[User(id=4, name=Sandy, age=null, mail=test4@baomidou.com, address=null), User(id=5, name=Billie, age=null, mail=test5@baomidou.com, address=null)]
//          根据字段查询一个
        User user1 = new User();
        user1.setAge(24);
        QueryWrapper<User> wrapper = new QueryWrapper<>(user1);
        User user2 = userMapper.selectOne(wrapper);
        System.out.println(user2);
//       查询map集合 返回list集合
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
         userQueryWrapper.gt("age", 12);
        Integer count = userMapper.selectCount(userQueryWrapper);
        System.out.println(count);
//     根据查询map集合  返回list集合
        List<User> users1 = userMapper.selectList(userQueryWrapper);
        System.out.println(users1);

    }
//分页
    @Test
    public  void  testSelectPage()
    {
        //1. 需要先配置文件 MybatisPlusConfig
//        2.接下来
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",10);
        Page<User> page = new Page<>(1, 1);
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println(userPage.getTotal()+"总条数");
        System.out.println(userPage.getSize()+"总大小");
        System.out.println(userPage.getPages()+"总页数");
        List<User> records = userPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }

    }

    /**
     * 自定义的id
     */
    @Test
    public  void  findByIdML(){
        User byIdML = userMapper.findByIdML(5L);
        System.out.println(byIdML);
    }


    @Test
    public void testAllEq(){
        HashMap<String,  Object> params = new HashMap<>();
        params.put("name","sf");
        params.put("age",12);
        params.put("email",null);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
 //       userQueryWrapper.allEq(params);
//        sql语句为：select * from user where email is null and name ='sf' and age=12;

//        sql语句为：select * from user where name ='sf' and age=12;
//         userQueryWrapper.allEq(params, false);
//        sql语句为：select * from user where name ='sf'
//        userQueryWrapper.allEq((k,v)->(k.equals("id")||k.equals("name")),params);

        userQueryWrapper.allEq(params).select("name");//查询某个字段
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);


    }
}
