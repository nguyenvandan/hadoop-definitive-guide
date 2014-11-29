import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

public class MaxTemperatureMapperTest {
	@Test
	public void processesValidRecord() throws IOException, InterruptedException {
		Text value = new Text(
				"0043011990999991950051518004+68750+023550FM-12+0382" +
				// Year ^^^^
						"99999V0203201N00261220001CN9999999N9-00111+99999999999");
		// Temperature ^^^^^
		new MapDriver<LongWritable, Text, Text, IntWritable>()
				.withMapper(new MaxTemperatureMapper()).withInputValue(value)
				.withOutput(new Text("1950"), new IntWritable(-11)).runTest();
	}

	@Test
	public void ignoresMissingTemperatureRecord() throws IOException,
			InterruptedException {
		Text value = new Text(
				"0043011990999991950051518004+68750+023550FM-12+0382" +
				// Year ^^^^
						"99999V0203201N00261220001CN9999999N9+99991+99999999999");
		// Temperature ^^^^^
		new MapDriver<LongWritable, Text, Text, IntWritable>()
				.withMapper(new MaxTemperatureMapper()).withInputValue(value)
				.runTest();
	}

	@Test
	public void MaxTemperatureDriver() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "file:///");
		conf.set("mapreduce.framework.name", "local");

		Path input = new Path("input/1901");
		Path output = new Path("output");

		FileSystem fs = FileSystem.getLocal(conf);
		fs.delete(output, true); // delete old output

		MaxTemperatureDriver driver = new MaxTemperatureDriver();
		driver.setConf(conf);

		int exitCode = driver.run(new String[] { input.toString(),
				output.toString() });
		
		assertThat(exitCode, is(0));
		//checkOutput(conf, output);
	}
}
