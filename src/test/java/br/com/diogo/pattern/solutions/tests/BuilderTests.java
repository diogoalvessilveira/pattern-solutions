package br.com.diogo.pattern.solutions.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.diogo.pattern.solutions.builder.Builder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuilderTests {

	@Test
	public void createBuilder() {
		TestandoA teste = Builder.build(TestandoA.class).with(t -> t.setNome("Nome do teste")).toBuilder();
		Assert.assertNotNull(teste);
		Assert.assertEquals(teste.getNome(), "Nome do teste");
	}

}
