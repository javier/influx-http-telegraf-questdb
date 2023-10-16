# Influx to QuestDB via Telegraf

1. Make sure you have a questdb instance running and accepting connections on 9009 (or change the telegraf config file accordingly)
2. Start telegraf with the provided config file 
  ```bash
  telegraf --config telegraf_influx_to_questdb.conf
  ```
3. Run this JAVA example. It should write one row using a data point, one using the ILP protocol, and one via a POJO. Demo data will be written into a new `temperature` table in your questdb server
4. After a few moments you should see your data into questdb. Connect to your questdb instance (defaults to `http://localhost:9000`) and run this query `SELECT * from temperature`. Three rows should appear, one for each method we are using at the JAVA class.
 <img width="615" alt="image" src="https://github.com/javier/influx-http-telegraf-questdb/assets/3839/3c22f44b-f6f7-44e9-9e22-24a75ae44e6e">
