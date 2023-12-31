# Influx to QuestDB via Telegraf

1. Make sure you have a questdb instance running and accepting connections on 9009 (or change the telegraf config file accordingly). You can start questdb via docker with
```shell
   docker run -p 9000:9000 -p 9009:9009 -p 8812:8812 -p 9003:9003 questdb/questdb:latest
```
   
2. Start [telegraf](https://docs.influxdata.com/telegraf/v1/install/) with the provided config file 
```shell
  telegraf --config telegraf_influx_to_questdb.conf
```

3. (See [Python folder](./python) if you prefer Python). Run this JAVA example. It should write one row using a data point, one using the ILP protocol, and one via a POJO. Demo data will be written into a new `temperature` table in your questdb server. You can run it via
```shell
mvn compile exec:java -Dexec.mainClass=example.InfluxDB2Example
```

4. After a few moments you should see your data into questdb. Connect to your questdb instance (defaults to `http://localhost:9000`) and run this query `SELECT * from temperature`. Three rows should appear, one for each method we are using at the JAVA class.
 <img width="615" alt="image" src="https://github.com/javier/influx-http-telegraf-questdb/assets/3839/3c22f44b-f6f7-44e9-9e22-24a75ae44e6e">
