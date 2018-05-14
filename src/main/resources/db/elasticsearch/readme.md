1 创建索引
PUT http://192.168.2.81:9200/fastsearch

{

    "settings": {
        "index": {
            "number_of_shards": 3,
            "number_of_replicas": 2            
        } 
    }
}

注:
获取索引
GET http://192.168.2.81:9200/fasstsearch/

删除索引
DELETE http://192.168.2.81:9200/fastsearch/

2 增加映射
PUT http://192.168.2.81:9200/fastsearch

{
    
    "mapping": {
        "car": {
            "properties": {
                
            }
        }
    }
}

或者:
PUT http://192.168.2.81:9200/fastsearch/_mapping/car

{
    
    "properties": {
        
    }
}

3 获取映射
GET http://192.168.2.81:9200/fastsearch/_mapping/car


4 增加文档
PUT http://192.168.2.81:9200/fastsearch/car/1

{

    
}