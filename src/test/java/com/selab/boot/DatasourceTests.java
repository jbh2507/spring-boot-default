package com.selab.boot;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class DatasourceTests {

	@Setter(onMethod_ = @Autowired)
	DataSource datasource;
	
	@Test
	public void existTest() {
		assertNotNull(datasource);
		log.info(datasource + "\tdatasource");
	}

}
