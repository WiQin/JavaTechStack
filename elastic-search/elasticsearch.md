# ElasticSearch 
## 前期准备 
1.虚拟机安装ElasticSearch(非root用户)(可能存在配置问题，需在root用户下修改配置文件)   
2.安装kibana  congig--kibana.yml 修改elasticsearch.url地址
3.安装ik分词器,自定义分词  
4.测试  
POST _analyze  
{
  "analyzer": "ik_smart",  
  "text": "我是中国人"  
}

## 操作索引
### 基本概念
1.索引库（indices）--是索引（index）的复数，与mysql的数据库对应  
2.类型（type）--模拟mysql的table概念，一个索引库下可以有不同类型的索引（未来版本可能会删除）  
3.文档（Document）--  存入索引库原始的数据，类似于数据库的一行数据  
4.字段（Field）-- 文档的属性（类型）  
5.映射配置（mappings）-- 字段的数据类型，属性，是否索引，是否存储等特性  

其他：分片（shard）--数据拆分后的各个部分，将一个索引的数据分开存储；  
副本（replica）--每个分片的复制，一个分片中有一个或多个副本，做备份用


## API
### Resuful风格Api--Http请求接口
可通过浏览器，kibana等发起请求
#### 1.创建索引
语法：PUT/索引库名  

PUT /es_demo 
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 1
  }
}
### 2.查询索引
GET /es_demo
### 3.删除索引
DELETE /es_demo
### 4.创建映射字段
语法：  
PUT /索引库名/_mapping/类型名称
{  
    "properties":{  
        "字段名":{  
            "type":"类型",
            "index":true,
            "store":true,
            "analyzer":"分词器"
        }
    }
}  
类型名称：type,类似于数据库的不同表  
字段名：字段
属性：type,类型，  
        支持类型：字符串：text(可分词),keyword(不可分词);
                 数值：long,short,integer,double,byte,float,half_float,scaled_float等;
                 日期：date；
                 布尔：boolean;
                 复杂类型：数组,object,json等
     index:是否索引，默认为true,即用户可根据该字段进行搜索;
     store:是否存储，默认为false,存储，在返回结果中可显示;  
        **es中不使用store控制是否显示，而是将文档中原始数据备份，保存到一个_source的属性中--提高写的效率  
        也可以通过修改_source控制哪些字段显示
     boost:激励因子
     analyzer:分词器，如"ik_max_word","ik_smart"等  
示例：  
PUT es_demo/_mapping/goods
{
  "properties": {
    "title":{
      "type": "text",
      "analyzer": "ik_max_word"
    },
    "images":{
      "type": "keyword",
      "index": false
    },
    "price":{
      "type": "float"
    }
  }
}
### 新增数据
通过POST请求，向一个已经存在的索引库中添加数据    
语法:  
POST /索引库/类型名称
{  
    "key":"value"
}  
示例：  
POST es_demo/goods  
POST es_demo/goods/1  指定id为1  
{
  "title":"小米手机",
  "image":"http://xxxx.com",
  "price":2699.00
}  
返回：  
{
  "_index": "es_demo",
  "_type": "goods",
  "_id": "CETKoW4B5ZWCt0-iuNP_",
  "_version": 1,
  "result": "created",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 0,
  "_primary_term": 1
}
### 查询数据
#### 根据条件
语法：GET 索引库名/_search
示例：  
GET es_demo/_search
{
  "query":{"match_all":{}}
}  
返回：  
{
  "took": 402,
  "timed_out": false,
  "_shards": {
    "total": 1,
    "successful": 1,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": 1,
    "max_score": 1,
    "hits": [
      {
        "_index": "es_demo",
        "_type": "goods",
        "_id": "CETKoW4B5ZWCt0-iuNP_",
        "_score": 1,
        "_source": {
          "title": "小米手机",
          "image": "http://xxxx.com",
          "price": 2699
        }
      }
    ]
  }
}
#### 根据id
语法：GET 索引库名/类型名称/id
GET es_demo/goods/1

### 删除数据
DELETE es_demo/goods/ID

### 智能推断
根据插入数据智能判断类型  
新增字符串类型，会加入两个字段，字段（text类型）字段.keyword(keyword类型)

### 动态映射

### 修改数据
PUT  
修改时必须指定id  
（若id存在则为修改，不存在则为新增）

### 删除数据
DELETE 索引库名/类型名/id

### 基本查询（全文检索）
1.match
GET /索引库名/_search
{  
    "query":{
        match:{
            "字段名":"内容";
            "字段名":{"query":"内容","operator":"and"} // 也可以为or  分词的索引关系，全部存在或某个存在
        }   
    }
}  
2.match_all  查询所有  
3.多字段查询  multi_match
GET /索引库名/_search
{  
    "query":{  
        "multi_match":{  
            "query":"小米",
            "fields":{"字段名1","字段名2"}
        }
    }
}  
4.词条匹配  term  （以搜索内容作为词条匹配，一般用来查询不分词的字段（除text外的其他类型））
GET /索引库名/_search
{  
    "term":{  
        "字段名"：{  
        "value":"内容"
        }
    }
}  
### 结果过滤
_source:"字段名"  只搜索某个或某些字段（数组）
_source:{inclueds:{}, //包含哪些字段 
         excludes:{}  //排除哪些字段
        } 
### 高级查询  
#### 模糊查询
GET /索引库名/_search  
{  
    "query":{  
        "fuzzy":{  
            "字段名":"内容"  
        }  
    }   
}  
#### 范围查询  
GET /索引库名/_search  
{  
    "query":{  
        "range":{  
            "字段":{
                "gte":值,//大于
                "lte":值 //小于
            }
        }  
    }   
} 
### 布尔查询
bool  
must/must_not/should --查询条件
filter--过滤条件（过滤条件不影响查询的关系度（得分））  
示例：  
GET es_demo/_search
{
  "_source":{
    "excludes":"images"
  },
  "query":{
    "bool": {
      "must": [
        {"match": {
          "title": "小米"
        }}
      ],
      "filter": {
        "range": {
          "price": {
            "gte": 10,
            "lte": 3000
          }
        }
      }
    }
  }
}
### 排序
sort:[  
    {  
     "":{
     "order":"desc"}
    }]  
示例：  
GET es_demo/_search
{
  "_source":{
    "excludes":"images"
  },
  "query":{
    "bool": {
      "must": [
        {"match": {
          "title": "小米"
        }}
      ],
      "filter": {
        "range": {
          "price": {
            "gte": 10,
            "lte": 3000
          }
        }
      }
    }
  },
  "sort": [
    {
      "price": {
        "order": "desc"
      }
    }
  ]
}
### 分页  
from：xxx,
size:xxx  
示例：  
GET es_demo/_search
{
  "_source":{
    "excludes":"images"
  },
  "query":{
    "bool": {
      "must": [
        {"match": {
          "title": "小米"
        }}
      ],
      "filter": {
        "range": {
          "price": {
            "gte": 10,
            "lte": 3000
          }
        }
      }
    }
  },
  "sort": [
    {
      "price": {
        "order": "desc"
      }
    }
  ],
  "from": 0,
  "size": 20
}
## 聚合aggregation 
数据统计分析 
### 常见类型
#### 桶
按照某种方式对数据进行分组，每一组数据在ES中称为一个桶  
划分方式：  
1、Date Histogram Aggregation:根据阶梯日期分组（给定阶梯：年，月或周等）  
2、Histogram Aggregation:根据数值阶梯分组，与日期类似  
3、Terms Aggregation:根据词条内容分组，词条内容完全匹配的为一组  
4、Range Aggregation:数值和日期的范围分组，指定开始和结束，然后按段分组
#### 度量
对桶中数据进行分组运算  
常见方式:  
Avg Aggregation:平均值  
Max Aggregation:最大值  
Min Aggregation:最小值  
Percentiles Aggregation:求百分比  
Stats Aggregation:同时返回avg,max,min,sum,count等  
Sum Aggregation:求和  
Top hits Aggregation:求前几  
Value Count Aggregation:求总数  
示例：  
创建索引：  
PUT /cars 
{
  "settings":{
    "number_of_shards":1,
    "number_of_replicas":0
  },
  "mappings":{
    "transactions":{
      "properties":{
        "color":{
          "type":"keyword"
        },
        "make":{
          "type":"keyword"
        }
      }
    }
  }
}  
测试数据：  
POST /cars/transactions/_bulk
{"index":{}}
{"price":10000,"color":"red","make":"honda","sole":"2014-10-28"}
{"index":{}}
{"price":20000,"color":"red","make":"honda","sole":"2014-11-05"}
{"index":{}}
{"price":30000,"color":"green","make":"ford","sole":"2014-05-18"}
{"index":{}}
{"price":150000,"color":"blue","make":"toyota","sole":"2014-07-02"}
{"index":{}}
{"price":120000,"color":"green","make":"toyota","sole":"2014-08-19"}
{"index":{}}
{"price":200000,"color":"red","make":"honda","sole":"2014-11-05"}
{"index":{}}
{"price":80000,"color":"red","make":"bmw","sole":"2014-01-01"}
{"index":{}}
{"price":25000,"color":"blue","make":"ford","sole":"2014-02-12"}  

GET /cars/_search  
{"query": {"match_all": {}}}  
按照制造商分桶：  
GET /cars/_search
{
  "size": 0, 
  "aggs": {
    "popular_by_brand": {
      "terms": {
        "field": "make"
      }
    }
  }
}  
桶内数据求平均值：  
GET /cars/_search
{
  "size": 0, 
  "aggs": {
    "popular_by_brand": {
      "terms": {
        "field": "make"
      },
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }
  }
}
## Spring Data ElasticSearch  
