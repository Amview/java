1. 从华为jdk开源站下载jdk

   ```
   https://repo.huaweicloud.com/java/jdk/
   ```

2. 编辑` ~/.bashrc`，添加

   ```
   JAVA_HOME=/opt/jdk1.8.0_151
   
   export PATH=$JAVA_HOME/bin:$PATH 
   
   export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
   ```

   

