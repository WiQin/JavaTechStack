# Nginx
> Nginx同Apache一样都是一种WEB服务器。基于REST架构风格，以统一资源描述符(Uniform Resources Identifier)URI或者统一资源定位符(Uniform Resources Locator)URL作为沟通依据，通过HTTP协议提供各种网络服务

> Nginx是一款自由的、开源的、高性能的HTTP服务器和反向代理服务器；同时也是一个IMAP、POP3、SMTP代理服务器；Nginx可以作为一个HTTP服务器进行网站的发布处理，另外Nginx可以作为反向代理进行负载均衡的实现。

## 关于代理
1. 正向代理 
* 正向代理，"它代理的是客户端，代客户端发出请求"，是一个位于客户端和原始服务器(origin server)之间的服务器，
* 为了从原始服务器取得内容，客户端向代理发送一个请求并指定目标(原始服务器)，然后代理向原始服务器转交请求并将获得的内容返回给客户端。
* 客户端必须要进行一些特别的设置才能使用正向代理。

2. 反向代理
* 反向代理，"它代理的是服务端，代服务端接收请求"，主要用于服务器集群分布式部署的情况下，反向代理隐藏了服务器的信息。  
`反向代理的作用`
* 保证内网的安全，通常将反向代理作为公网访问地址，Web服务器是内网
* 负载均衡，通过反向代理服务器来优化网站的负载

## 负载均衡
Nginx作为反向代理服务器，不可避免的需要承担请求分发的责任，这时就涉及到了负载均衡的问题  
Nginx支持的负载均衡算法：
1. weight轮询(默认，常用)：接收到的请求按照权重分配到不同的后端服务器，即使在使用过程中，某一台后端服务器宕机，Nginx会自动将该服务器剔除出队列，请求受理情况不会受到任何影响。 这种方式下，可以给不同的后端服务器设置一个权重值(weight)，用于调整不同的服务器上请求的分配率；权重数据越大，被分配到请求的几率越大；该权重值，主要是针对实际工作环境中不同的后端服务器硬件配置进行调整的。
2. ip_hash（常用）：每个请求按照发起客户端的ip的hash结果进行匹配，这样的算法下一个固定ip地址的客户端总会访问到同一个后端服务器，这也在一定程度上解决了集群部署环境下session共享的问题。
3. fair：智能调整调度算法，动态的根据后端服务器的请求处理到响应的时间进行均衡分配，响应时间短处理效率高的服务器分配到请求的概率高，响应时间长处理效率低的服务器分配到的请求少；结合了前两者的优点的一种调度算法。但是需要注意的是Nginx默认不支持fair算法，如果要使用这种调度算法，请安装upstream_fair模块。
4. url_hash：按照访问的url的hash结果分配请求，每个请求的url会指向后端固定的某个服务器，可以在Nginx作为静态服务器的情况下提高缓存效率。同样要注意Nginx默认不支持这种调度算法，要使用的话需要安装Nginx的hash软件包。

## nginx配置详解
### 反向代理
~~~editorconfig
#运行用户
#user somebody;

#启动进程,通常设置成和cpu的数量相等
worker_processes  1;

#全局错误日志
error_log  D:/Tools/nginx-1.10.1/logs/error.log;
error_log  D:/Tools/nginx-1.10.1/logs/notice.log  notice;
error_log  D:/Tools/nginx-1.10.1/logs/info.log  info;

#PID文件，记录当前启动的nginx的进程ID
pid        D:/Tools/nginx-1.10.1/logs/nginx.pid;

#工作模式及连接数上限
events {
   worker_connections 1024;    #单个后台worker process进程的最大并发链接数
}

#设定http服务器，利用它的反向代理功能提供负载均衡支持
http {
   #设定mime类型(邮件支持类型),类型由mime.types文件定义
   include       D:/Tools/nginx-1.10.1/conf/mime.types;
   default_type  application/octet-stream;
   
   #设定日志
   log_format  main  '[$remote_addr] - [$remote_user] [$time_local] "$request" '
                     '$status $body_bytes_sent "$http_referer" '
                     '"$http_user_agent" "$http_x_forwarded_for"';
                     
   access_log    D:/Tools/nginx-1.10.1/logs/access.log main;
   rewrite_log     on;
   
   #sendfile 指令指定 nginx 是否调用 sendfile 函数（zero copy 方式）来输出文件，对于普通应用，
   #必须设为 on,如果用来进行下载等应用磁盘IO重负载应用，可设置为 off，以平衡磁盘与网络I/O处理速度，降低系统的uptime.
   sendfile        on;
   #tcp_nopush     on;

   #连接超时时间
   keepalive_timeout  120;
   tcp_nodelay        on;
   
   #gzip压缩开关
   #gzip  on;

   #设定实际的服务器列表 
   upstream zp_server1{
       server 127.0.0.1:8089;
   }

   #HTTP服务器
   server {
       #监听80端口，80端口是知名端口号，用于HTTP协议
       listen       80;
       
       #定义使用www.xx.com访问
       server_name  www.helloworld.com;
       
       #首页
       index index.html
       
       #指向webapp的目录
       root D:_WorkspaceProjectgithubzpSpringNotesspring-securityspring-shirosrcmainwebapp;
       
       #编码格式
       charset utf-8;
       
       #代理配置参数
       proxy_connect_timeout 180;
       proxy_send_timeout 180;
       proxy_read_timeout 180;
       proxy_set_header Host $host;
       proxy_set_header X-Forwarder-For $remote_addr;

       #反向代理的路径（和upstream绑定），location 后面设置映射的路径
       location / {
           proxy_pass http://zp_server1;
       } 

       #静态文件，nginx自己处理
       location ~ ^/(images|javascript|js|css|flash|media|static)/ {
           root D:_WorkspaceProjectgithubzpSpringNotesspring-securityspring-shirosrcmainwebappiews;
           #过期30天，静态文件不怎么更新，过期可以设大一点，如果频繁更新，则可以设置得小一点。
           expires 30d;
       }
   
       #设定查看Nginx状态的地址
       location /NginxStatus {
           stub_status           on;
           access_log            on;
           auth_basic            "NginxStatus";
           auth_basic_user_file  conf/htpasswd;
       }
   
       #禁止访问 .htxxx 文件
       location ~ /.ht {
           deny all;
       }
       
       #错误处理页面（可选择性配置）
       #error_page   404              /404.html;
       #error_page   500 502 503 504  /50x.html;
       #location = /50x.html {
       #    root   html;
       #}
   }
}
~~~