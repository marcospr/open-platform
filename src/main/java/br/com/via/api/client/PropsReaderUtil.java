package br.com.via.api.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsReaderUtil {
	private static InputStream inputStream;
	
	
	public String getHost()  {
		return getSingleProp("host");
	}
	
	public String getToken()  {
		return getSingleProp("token");
	}

	private String getSingleProp(String propertieName) {
		String token = null;
		try {
			 token = getProp().getProperty(propertieName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
	
	private Properties getProp() throws IOException {
		Properties prop = new Properties();
		String propFileName = "config.properties";
		
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		try {
			prop.load(inputStream);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}
}
