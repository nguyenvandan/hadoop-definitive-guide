package mapreduce;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.*;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

public class ConfigurationTest {
	
	@Test
	public void configuration() {
		Configuration conf = new Configuration();
		conf.addResource("configuration-1.xml");
		
		assertThat(conf.get("color"), is("yellow"));
		assertThat(conf.getInt("size", 0), is(10));
		assertThat(conf.get("breadth", "wide"), is("wide"));
	}
	
	@Test
	public void twoConfigurations() {
		Configuration conf = new Configuration();
		conf.addResource("configuration-1.xml");
		conf.addResource("configuration-2.xml");
		
		System.setProperty("size", "14");
		
		assertThat(conf.get("color"), is("yellow"));
		assertThat(conf.getInt("size", 0), is(12));
		assertThat(conf.get("breadth", "wide"), is("wide"));
		assertThat(conf.get("weight"), is("heavy"));
		
		assertThat(conf.get("size-weight"), is("14,heavy"));
	}
}
