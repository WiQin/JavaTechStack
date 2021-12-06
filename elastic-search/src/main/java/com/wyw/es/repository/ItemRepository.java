package com.wyw.es.repository;

import com.wyw.es.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * @author wangyw
 * @date 2019/11/25
 */
public interface ItemRepository extends ElasticsearchCrudRepository<Item,Long> {

    List<Item> findByPriceBetween(Double begin,Double end);
}
