package com.wyw.es;

import com.wyw.es.pojo.Item;
import com.wyw.es.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyw
 * @date 2019/11/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    ElasticsearchTemplate template;
    @Autowired
    ItemRepository api;

    @Test
    public void testCreate(){
        //创建索引库
        //参数通过注解实现
        template.createIndex(Item.class);
        //映射关系
        template.putMapping(Item.class);

        template.deleteIndex("java_demo");
    }

    @Test
    public void insertIndex(){
        List<Item> list = new ArrayList<Item>();
        list.add(new Item(1L,"坚果手机R1","手机","锤子",3699.00,"http://xxx"));
        list.add(new Item(2L,"华为Mate10","手机","华为",4699.00,"http://xxx"));
        api.saveAll(list);
    }

    @Test
    public void testFind(){
        Iterable<Item> all = api.findAll();
        for (Item item : all){
            System.out.println("item="+item);
        }
    }

    @Test
    public void testFindBy(){
        //不需要实现
        Iterable<Item> all = api.findByPriceBetween(2000d,4000d);
        for (Item item : all){
            System.out.println("item="+item);
        }
    }


}
