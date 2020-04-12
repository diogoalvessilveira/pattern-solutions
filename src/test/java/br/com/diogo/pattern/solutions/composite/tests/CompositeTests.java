package br.com.diogo.pattern.solutions.composite.tests;

import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import br.com.diogo.pattern.solutions.builder.Builder;
import br.com.diogo.pattern.solutions.tests.TestandoA;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompositeTests {

	@Test
	public void composite() {
		TestandoA teste1 = Builder.build(TestandoA.class).with(t -> t.setNome("Filho1")).toBuilder();
		TestandoA teste3 = Builder.build(TestandoA.class).with(t -> t.setNome("Filho do filho2")).toBuilder();
		TestandoA teste2 = Builder.build(TestandoA.class).with(t -> t.setNome("Filho2")).with(t -> t.add(teste3))
				.toBuilder();
		TestandoA teste = Builder.build(TestandoA.class).with(t -> t.setNome("Pai")).with(t -> t.add(teste1))
				.with(t -> t.add(teste2)).toBuilder();

		buscar(teste);
	}

	public void buscar(TestandoA objeto) {
		if (Objects.nonNull(objeto) && !CollectionUtils.isEmpty(objeto.getFolhas())) {
			objeto.getFolhas().forEach(o -> {
				System.out.println(o.getNome());
				buscar(o);
			});
		}
	}
}
