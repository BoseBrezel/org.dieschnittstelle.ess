package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.dieschnittstelle.ess.basics.reflection.ReflectedStockItemBuilder.getAccessorNameForField;
import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations{

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}
	// TODO BAS2: create a string representation of instance by iterating
	//  over the object's attributes / fields as provided by its class
	//  and reading out the attribute values. The string representation
	//  will then be built from the field names and field values.
	//  Note that only read-access to fields via getters or direct access
	//  is required here.
	private static void showAttributes(Object instance)
	{
		Class klass = instance.getClass();
		try
		{
            String attri = "";
            for (Field field : klass.getDeclaredFields())
			{
				String fieldName = field.getName();
                String gettername = getAccessorNameForField("get", field.getName());
                Method getterMethod = klass.getDeclaredMethod(gettername);
                Object value = getterMethod.invoke(instance);
				attri += (fieldName + ": " + value + ", ");
			}
			if (attri.length() > 0)
			{
				attri = attri.substring(0, attri.length() - 2);
			}
			show("{"+instance.getClass().getSimpleName() + " " + attri + "}");

			// TODO BAS3: if the new @DisplayAs annotation is present on a field,
            //  the string representation will not use the field's name, but the name
            //  specified in the the annotation. Regardless of @DisplayAs being present
            //  or not, the field's value will be included in the string representation.

        }
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("showAnnotations(): exception occurred: " + e,e);
		}
	}
}
