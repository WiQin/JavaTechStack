package com.wyw.es.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 商品
 *
 * @author wangyw
 * @date 2019/11/25
 */
@Data
@Document(indexName = "java_demo",type = "item",shards = 1,replicas = 1)
public class Item {
    @Id
    @Field(type = FieldType.Long)
    Long id;
    @Field(type = FieldType.Text,analyzer = "ik_smart")
    String title;//标题
    @Field(type = FieldType.Keyword)
    String category;//分类
    @Field(type = FieldType.Keyword)
    String brand;//品牌
    @Field(type = FieldType.Double)
    Double price;//价格
    @Field(type = FieldType.Keyword,index = false)
    String images;//图片地址

    public Item() {
    }

    public Item(Long id, String title, String category, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }
}
