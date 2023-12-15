package example;

import java.time.Instant;
import java.util.List;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

public class InfluxDB2Example {

    private static char[] token = "lE_GhvB4QzZyw5vI0T7hY5Iu5DSxdtl1j2KTh7hPFLTAkOaqymuDxpUCfHDqb5ZeFYd-qKuUF3Crr5_wwLAI0Q==".toCharArray();
    private static String org = "influx";
    private static String bucket = "influx";

    public static void main(final String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

	if  (influxDBClient.ping()) {

        	//
        	// Write data
        	//
        	WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

        	//
        	// Write by LineProtocol
        	//
        	writeApi.writeRecord(WritePrecision.NS, "temperature,location=north value=60.0");

        	//
        	// Write by Data Point
        	//
        	Point point = Point.measurement("temperature")
                	.addTag("location", "west")
                	.addField("value", 55D)
                	.time(Instant.now().toEpochMilli(), WritePrecision.MS);

        	writeApi.writePoint(point);



        	//
        	// Write by POJO
        	//
        	Temperature temperature = new Temperature();
        	temperature.location = "south";
        	temperature.value = 62D;
        	temperature.time = Instant.now();

	        writeApi.writeMeasurement( WritePrecision.NS, temperature);

        }


        influxDBClient.close();
	System.exit(0);
    }

    @Measurement(name = "temperature")
    private static class Temperature {

        @Column(tag = true)
        String location;

        @Column
        Double value;

        @Column(timestamp = true)
        Instant time;
    }
}
