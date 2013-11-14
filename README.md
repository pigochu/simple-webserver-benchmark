Simple Web Server Benchmark
===========================

這個專案是我自己評估那一種語言，架構來寫即時通訊應用效能比較

## 路徑說明 ##
- files : 存放測試用的靜態檔案
- nodejs : 以 nodejs javascript 語言寫的測試
- phpevent : 以 php 語言搭配 pecl-event 寫的測試
- vertx : 官方 vertx version 2.1M1 套件 (註1)
- vertx-java : 以 vertx java 語言寫的測試
- vertx-js : 以 vertx javascript 語言寫的測試



以上三種是我自己有能力來碰的範圍，所以針對三種架構(php/nodejs/vertx)寫出簡單的 http server ，並且測試其效能。

## 測試環境需求 ##

這些 Code 我是用下列環境測試的，其他的 Linux 版本只要別太舊應該都可以正常跑吧

- CentOS 6.4 x86_64
- EPEL 提供的 nodejs
- 內建的 openjdk 及 openjdk-devel (openjdk 開發套件)
- remi 版的 php 及 pecl event

## 測試環境安裝相關的套件指令 ##

### nodejs 的支援 ###

    yum install nodejs (註2)

### php 支援 event ###
    yum --enablerepo=remi install php-pecl-event (註3)

### vertx 需要 java 的支援 ###

    yum install java-1.7.0-openjdk.x86_64 java-1.7.0-openjdk-devel.x86_64

## 執行方式 ##

以下將介紹各種測試項目的執行方式，建議用 root 權限來跑，要跑各種測試前可以先執行 ulimit -a 4096 ，這樣子若用 ab 開 1000 條連線測試的時候比較不會出錯

### phpevent ###

於命令列輸入以下指令

    php simple-webserver-benchmark/phpevent/server.php

若無意外，畫面就停在 console，接著可以用 curl 直接看是否有內容

    curl http://127.0.0.1:8080/helloworld
    curl http://127.0.0.1:8080/sendfile1
    curl http://127.0.0.1:8080/sendfile2
    curl http://127.0.0.1:8080/arrayjson

接下來各種架構的測試方式都可以用 curl 檢查是否有跑起來，若要結束 server ，按 Ctrl + C 停止，而以上用 curl 所測試的項目說明分別是

- helloworld :　只回應 Hello World !
- sendfile1 :　傳送一個很小的檔案，不到 100 Bytes
- sendfile2 :　傳送一個差不多 1K Bytes 的檔案
- arrayjson : 將陣列轉為 json 並回傳以測試 json 轉換效能


### nodejs ###

於命令列輸入以下指令

    node simple-webserver-benchmark/nodejs/server.js

### vertx-js ###

vertx 的命令可以由專案中的 vertx/bin 中找到，可以自己設定好 PATH，或自行官網下載安裝最新版

    vertx run simple-webserver-benchmark/vertx-js/server.js

### vertx-java ###

    vertx run simple-webserver-benhmark/vertx-java/src/Server.java

## 註釋 ##
- 註 1 : vertx 是什請至 [vertx 官方網站](http://vertx.io "vertx") 看看
- 註 2 : nodejs 被包在 EPEL 中，必須於系統先安裝好 EPEL 的 yum repo 支援 ，請看參見 [EPEL Wiki](http://fedoraproject.org/wiki/EPEL "EPEL Wiki")
- 註 3 : yum 要使用 remi repo 請參考 [http://blog.famillecollet.com/pages/Config-en](http://blog.famillecollet.com/pages/Config-en)

## 相關資料參考 ##

- PHP Event Manual : [http://www.php.net/manual/en/book.event.php](http://www.php.net/manual/en/book.event.php)


## 版權 ##

還不知道要用甚麼授權～總之要拿去改要保留原作者名稱就是了

## 作者 ##

壞蛋 Pigo