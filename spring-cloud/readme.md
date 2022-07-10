# 说明
学习Spring Cloud过程中的练习

# 结构
- spring cloud 管理Maven依赖的父工程
 - model 存放实体类的子项目
 - provider 提供服务的子项目
 - consumer 消费服务的子项目

# 问题
 - consumer使用RestTemplate调用provider的部分服务时，得到的响应体为空
*在provider中通过打印出参数发现参数为null，因此注意到应该是参数传递的问题，通过检查`public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables)`的参数发现，该方法适用于GET请求的RESTful url，uriVariables参数对应着目标url的每个路径参数，因此要求目标url是RESTful风格的url。
