
#### 服务列表：
##### eureka服务
eureka-server: 9761
##### 服务提供者
service-hi: 9763/9762
##### ribbon服务
ribbon-server: 9764
##### feign服务
feign-server: 9765
##### zuul服务
zuul-server: 9769
##### config服务
config-server: 8888
<br>
如果需要更改成别的端口配置文件需要改为bootstrap.properties

#### 配置中心整合消息总线
发送post请求：http://localhost:port/actuator/bus-refresh更新配置文件
<br>
这个功能实在太鸡肋


