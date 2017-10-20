package com.client.commande.ClientCommandeGestion.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("prod")
public class CloudDatabaseConfiguration {

	private final Logger log = LoggerFactory.getLogger(CloudDatabaseConfiguration.class);

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		String databaseUrl = System.getenv("DATABASE_URL");
		log.info("Initializing Mysql database: {}", databaseUrl);

		URI dbUri;
		try {
			dbUri = new URI(databaseUrl);
		} catch (URISyntaxException e) {
			log.error(String.format("Invalid DATABASE_URL: %s", databaseUrl), e);
			return null;
		}

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnReturn(true);
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}
}
