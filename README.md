# Influx to QuestDB via Telegraf

1. Make sure you have a questdb instance running and accepting connections on 9009 (or change the telegraf config file accordingly)
2. Start telegraf with the provided config file 
  ```bash
  telegraf --config telegraf_influx_to_questdb.conf
  ```
3. Run this JAVA example. Demo should be written into the `temperature` table in your questdb server