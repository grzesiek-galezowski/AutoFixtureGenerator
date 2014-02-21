package jfixture.publicinterface.generators;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class BuiltInCollectionGenerator implements CollectionGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> clazz) {
		return Collection.class == clazz.getRawType()
				|| ArrayList.class == clazz.getRawType(); 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T next(TypeToken<T> clazz, Fixture fixture) {
		ArrayList list = new ArrayList();
		ParameterizedType genericTypeDefinition = (ParameterizedType)(clazz.getType());
		Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[0];
		
		TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
		Object instance1 = fixture.create(nestedGenericTypeToken);
		Object instance2 = fixture.create(nestedGenericTypeToken);
		Object instance3 = fixture.create(nestedGenericTypeToken);
		
		list.add(instance1);
		list.add(instance2);
		list.add(instance3);
		
		return (T) list;
	}

}
