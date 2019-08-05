
#### 服务列表：

##### eureka服务
eureka-server: 9761

##### 服务提供者
service-hi: 9763/9762

##### ribbon服务
ribbon-server: 9764

##### feign服务
feign-server: 9765

##### 断路器聚合监控服务
turbine-server: 9766
<br>
打开:http://localhost:port/hystrix,输入监控流http://localhost:9766/turbine.stream

##### zuul服务
zuul-server: 9769

##### config客户端
config-client: 8889

##### config服务
config-server: 8888
<br>
如果需要更改成别的端口配置文件需要改为bootstrap.properties

##### 配置中心整合消息总线
发送post请求：http://localhost:port/actuator/bus-refresh更新配置文件
<br>
这个功能实在太鸡肋

##### 链路追踪服务
server-zipkin: 9411
<br>
http://localhost:9411打开管理界面
<br>
不用自己构建，运行zipkin-server-2.10.1-exec.jar就可以

##### 断路器监控
http://localhost:port/actuator/hystrix.stream
管理界面:http://localhost:port/hystrix

##### consul服务端
不需要自己构建，官网下载即可，默认端口8500<br>
consul服务 8500

##### consul消费者
consul-consumer 9771

##### consul生产者
consul-provider 9770
 



