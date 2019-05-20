package br.com.cafebinario.transactionprocessor.functions.exposes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.functions.dtos.reports.AttributeDefinition;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.ObjectDefinition;

@Service("api-definitions")
public class TransactionProcessorDefinitions implements Function<String, ObjectDefinition> {
	
	@Override
	public ObjectDefinition apply(final String defininitionName) {

		try {
			
			final Class<?> clazz = Class //
					.forName(defininitionName);
			
			if(clazz.isEnum()) {
				return ObjectDefinition
						.builder()
						.attributes(
								Arrays.asList(clazz.getEnumConstants())
								.stream()
								.map(mapper->AttributeDefinition
										.builder()
										.name(mapper.toString())
										.type(mapper.getClass().getName())
										.build())
										.collect(Collectors.toList()))
						.build();
			}
			
			final Object builder = clazz
					.getDeclaredMethod("builder") //
					.invoke(null);

			final Object object = builder //
					.getClass() //
					.getDeclaredMethod("build") //
					.invoke(builder);

			final List<AttributeDefinition> attributes = Arrays //
					.asList( //
							object //
									.getClass() //
									.getDeclaredFields()) //
					.stream() //
					.map(this::formmatAttributeDefinition) //
					.collect(Collectors.toList());

			return ObjectDefinition //
					.builder() //
					.attributes(attributes) //
					.build();

		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			return null;
		}
	}

	private AttributeDefinition formmatAttributeDefinition(final Field field) {
		
		return AttributeDefinition //
				.builder() //
				.name(field.getName()) //
				.type(field.getGenericType() == null ? field.getType().getName() : field.getGenericType().getTypeName()) //
				.build();
	}
}
