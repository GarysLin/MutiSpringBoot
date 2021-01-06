# Micro SpringBoot
## 資料夾結構
```
src
|-- java.com.cloudinteractive    
|    |-- core 核心程式，較不常異動
|    |    |- config 各式基本設定
|    |    |- filter 各式基本filter
|    |    |- controller BaseController放置共通載入元件
|    |    |- model 基本的model
|    |    |- security token檢核設定
|    |    |- service BaseService，基本的Service
|    |
|    |-- webapi 專案資料夾，基本會在此新增程式
|         |- config 依專案增加的設定
|         |- filter 依專案增加的filter
|         |- rest restController
|         |- model 各類Model，response需繼承BaseHttpResponse並複寫resultData
|         |- service 各類service
|         |- utils 好用工具類
|
|-- resources
     |-- i18n.message* 多國語言訊息設定
     |-- doc 各種文件資料

```