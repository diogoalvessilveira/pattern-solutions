package br.com.diogo.pattern.solutions.template.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTests {
	@Test
	public void template() {
		Template tem = new Template(new MyTemplate());
		tem.with(t -> t.getObject());
	}
}
