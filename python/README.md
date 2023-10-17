# Influx to QuestDB via Telegraf

1. Make sure you have a questdb instance running and accepting connections on 9009 (or change the telegraf config file accordingly). You can start questdb via docker with
```shell
   docker run -p 9000:9000 -p 9009:9009 -p 8812:8812 -p 9003:9003 questdb/questdb:latest
```
   
2. Start [telegraf](https://docs.influxdata.com/telegraf/v1/install/) with the provided config file 
```shell
  telegraf --config telegraf_influx_to_questdb.conf
```

3. (See [JAVA folder](../) if you prefer JAVA). Install requirements Run this JAVA example. 
```
pip install influxdb-client
```

4. Run the Python script. Demo data will be written into a new `my_measurement` table in your questdb server. You can run it via
```shell
python insert_via_python.py
```

4. After a few moments you should see your data into questdb. Connect to your questdb instance (defaults to `http://localhost:9000`) and run this query `SELECT * from my_measurement`. One row should appear.
![image](https://github.com/javier/influx-http-telegraf-questdb/assets/3839/afeb1fc3-e3a1-42a8-acb8-f355686b0ee0)


   

