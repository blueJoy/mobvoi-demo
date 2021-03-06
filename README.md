### 出门问问小demo

####R estful接口文档
1. 给定用户ID,​ ​返回当前金币金额
   ##### URL  
     ​/coins/user/{userId}  
    ##### HTTP请求方式
      GET   
     ##### 请求参数
     
     参数 | 是否必填  | 类型    |   说明  
     ------------ | ------------- | -------------| -------------
     userId | YES  |    Long  | 用户ID

  
   ##### 返回值
   ```
            {
                "message": null,
                "data": 100
            }
   ```
  
   ##### 错误说明
  
   httpcode | 说明  
   ------------ | -------------
   422 | 参数校验失败  
   404 | 不存在该用户  
  


2. 添加用户和金币,如果用户存在则更新用户金币数
   ##### URL  
     ​/coins/add  
    ##### HTTP请求方式
      POST
     ##### 请求参数
     
      参数 | 是否必填  | 类型    |   说明  
      ------------ | ------------- | -------------| -------------
      userId | YES  |    Long  | 用户ID
      coins | YES  |    Integer  | 金币数
  
        
     ##### 请求体 json格式
     ```
        {
            "userId":1232,
            "coins":5
        }
    ```
    
    ##### 返回值
    无返回值，http状态码为200为操作成功
    
    
   ##### 错误说明
   
   httpcode | 说明  
   ------------ | -------------
   422 | 参数校验失败  

    
3. 用户金币转账
   ##### URL  
     ​/coins/transfer   
    ##### HTTP请求方式   
      POST
     ##### 请求参数
     
     参数 | 是否必填  | 类型    |   说明  
     ------------ | ------------- | -------------| -------------
     fromUserId | YES  |    Long  | 源用户ID
     toUserId | YES  |    Long  | 目标用户ID
     coins | YES  |    Integer  | 金币数

    ##### 请求体 json格式  
     ```
        {
            "fromUserId":1232,
            "toUserId":1231,
            "coins":5
        }
    ```
    
    ##### 返回值
    
    无返回值，http状态码为200为操作成功
    
    
   ##### 错误说明
   
   httpcode | 说明  
   ------------ | -------------
   400 | 转账金币不足  
   404 | 用户不存在  
   422 | 参数校验失败  


#### 部署文档

##### 环境准备
- jdk8
- mavnen
- docker

##### 代码覆盖率
  - mvn cobertura:cobertura
   
   在target的site目录下的index查看代码覆盖率信息  
        ps:该demo只是实现的controller的mock测试，覆盖率不高
        
##### 打包镜像
  - mvn clean package docker:build


##### 监控信息
 - 查看内存线程信息 http://host:8081/ops/jstack
   
    