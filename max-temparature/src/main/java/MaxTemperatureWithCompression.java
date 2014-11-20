import org.apache.hadoop.mapreduce.Job;

public class MaxTemperatureWithCompression {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err
					.println("Usage: MaxTemperatureWithCompression <input path> "
							+ "<output path>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(MaxTemperature.class);
	}
}
