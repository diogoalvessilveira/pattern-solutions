package br.com.diogo.pattern.solutions.visitor.tests;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.diogo.pattern.solutions.visitor.Visitor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitorTests {
	@Test
	public void visitor() {
		Visitor<String> valorTest = new Visitor<>();
		Function<Object, String> valor = valorTest.on(MyVisitor.class).then(m -> m.getValor() + "Teste");
		Visitor<Double> valorDouble = new Visitor<>();
		Function<Object, Double> teste = valorDouble.on(MyVisitor2.class).then(m -> m.getValor() * 3);

		List<Object> objetos = Arrays.asList(new MyVisitor("Visita"), new MyVisitor("Visita2"));
		String sTeste = objetos.stream().map(valor).reduce("", (v1, v2) -> v1 + "_" + v2);
		List<Object> objetos2 = Arrays.asList(new MyVisitor2(5.0), new MyVisitor2(10.0));
		Double sTeste2 = objetos2.stream().map(teste).reduce(0.0, (v1, v2) -> v1 + v2);
		System.out.println(sTeste);
		System.out.println(sTeste2);
	}
}
