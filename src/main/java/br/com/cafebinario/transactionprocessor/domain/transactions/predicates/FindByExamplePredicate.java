package br.com.cafebinario.transactionprocessor.domain.transactions.predicates;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class FindByExamplePredicate implements Predicate<Pair<Object, Object>>{

	public boolean test(final Pair<Object, Object> pair) {

		final Object example = pair.getFirst();
		final Object row = pair.getSecond();

		final List<Field> fieldsExample = getAllFields(example);
		final List<Field> fieldsRow = getAllFields(row);

		for (final Field fieldExample : fieldsExample) {
			for (final Field fieldRow : fieldsRow) {
				if (fieldExample.equals(fieldRow)) {
					final Object value = getFieldValue(example, fieldExample);
					if (value != null && value.equals(getFieldValue(row, fieldRow))) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private List<Field> getAllFields(final Object example) {
		return Arrays.asList(example.getClass().getDeclaredFields());
	}

	private Object getFieldValue(final Object example, final Field fieldExample) {
		try {
			return fieldExample.get(example);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
