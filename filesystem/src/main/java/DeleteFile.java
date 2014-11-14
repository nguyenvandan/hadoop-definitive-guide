import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class DeleteFile {
	public static void main(String[] args) throws IOException {
		String uri = args[0];
		FileSystem fs = FileSystem.get(URI.create(uri), new Configuration());
		fs.delete(new Path(uri), true);
	}
}
