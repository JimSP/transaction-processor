package br.com.cafebinario.transactionprocessor.functions.exposes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.hazelcast.util.function.Predicate;

import br.com.cafebinario.transactionprocessor.functions.dtos.reports.AttributeDefinition;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.Contracts;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.FunctionContract;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.FunctionHandle;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.HttpMethod;

@Service("api-catalog")
public class TransactionProcessorCatalog implements Supplier<Contracts> {

	private static final String EMPTY = "";

	@Value("${transactionprocessing.functions.exposePackeage:br.com.cafebinario.transactionprocessor.functions.exposes}")
	private String functionsExposePackage;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Contracts get() {

		final List<String> beanNamesAnnotateds = Arrays
				.asList(applicationContext.getBeanNamesForAnnotation(Service.class));

		return Contracts //
				.builder() //
				.functions(functions(getFunctions(beanNamesAnnotateds, Function.class))) //
				.consumes(consumes(getFunctions(beanNamesAnnotateds, Consumer.class))) //
				.produces(supplier(getFunctions(beanNamesAnnotateds, Supplier.class))) //
				.build();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Pair<String, Object>> getFunctions(final List<String> beanNamesAnnotateds, final Class functionClass) {

		return beanNamesAnnotateds //
				.stream() //
				.map(name -> Pair.of(name, applicationContext.getBean(name))) //
				.filter(pair -> functionClass.isAssignableFrom(pair.getSecond() //
						.getClass())) //
				.collect(Collectors.toList());
	}

	private List<FunctionContract> functions(final List<Pair<String, Object>> functionPairs) {

		return streamOf(functionPairs) //
				.get() //
				.map(pair -> functionContract( //
						pair //
								.getSecond(), //
						Arrays.asList( //
								functionHandle(pair.getFirst(), HttpMethod.GET, Boolean.TRUE),
								functionHandle(pair.getFirst(), HttpMethod.POST, Boolean.FALSE)),
						"apply"::equals, 1)) //
				.collect(Collectors.toList());
	}

	private List<FunctionContract> consumes(final List<Pair<String, Object>> consumePairs) {

		return streamOf(consumePairs) //
				.get() //
				.map(pair -> functionContract( //
						pair.getSecond(), //
						Arrays.asList( //
								functionHandle(pair.getFirst(), HttpMethod.POST, Boolean.FALSE)),
						"accept"::equals, 1)) //
				.collect(Collectors.toList());
	}

	private List<FunctionContract> supplier(final List<Pair<String, Object>> suplierPairs) {

		return streamOf(suplierPairs) //
				.get() //
				.map(pair -> functionContract( //
						pair.getSecond(), //
						Arrays.asList( //
								functionHandle(pair.getFirst(), HttpMethod.GET, Boolean.FALSE)),
						"get"::equals, 0)) //
				.collect(Collectors.toList());
	}

	private Supplier<Stream<Pair<String, Object>>> streamOf(final List<Pair<String, Object>> functionPairs) {
		return () -> functionPairs //
				.stream() //
				.filter(this::wasExposePackage);
	}

	private FunctionContract functionContract(final Object bean, final List<FunctionHandle> handles,
			final Predicate<String> methodNamePredicate, int parameterCount) {

		final Method method = Arrays //
				.asList(bean //
						.getClass() //
						.getDeclaredMethods()) //
				.stream() //
				.filter(methodFiltred -> methodFiltred.getParameterCount() == parameterCount)
				.filter(methodFiltred -> methodNamePredicate.test(methodFiltred.getName())) //
				.findAny() //
				.orElseThrow(RuntimeException::new);

		final Class<?> parameterType = method //
				.getParameterTypes() != null && method.getParameterTypes().length > 0 ? method.getParameterTypes()[0]
						: null;

		final Class<?> returnType = method.getReturnType() != null ? method.getReturnType() : null;

		return FunctionContract //
				.builder() //
				.handles(handles) //
				.input(parameter(null, parameterType != null ? parameterType.getName() : null)) //
				.output(parameter(null, returnType != null ? returnType.getName() : null)) //
				.build();
	}
	
	private FunctionHandle functionHandle(final String name, final HttpMethod httpMethod, final Boolean hasItem) {

		return FunctionHandle //
				.builder() //
				.httpMethod(httpMethod) //
				.path(httpMethod.getPath(name) + (hasItem ? "/{item}" : EMPTY)) //
				.build();
	}

	private AttributeDefinition parameter(final String name, final String type) {

		return AttributeDefinition.builder().name(name).type(type).build();
	}

	private boolean wasExposePackage(Pair<String, Object> pair) {

		return pair //
				.getSecond() //
				.getClass() //
				.getName() //
				.contains(functionsExposePackage);
	}
}
