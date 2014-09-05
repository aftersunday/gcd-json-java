/**
 * Copyright (C) 2014 xuanhung2401.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.google.datastore.entity.core;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cloud.google.datastore.annotation.Annotation.Index;
import cloud.google.util.Utility;

/**
 * @author xuanhung2401
 * @param <T>
 * 
 */
public class Entity<T> {

	private Key<T> key;
	private HashMap<String, HashMap<String, Object>> properties;

	public Entity(T obj, String datasetId) {
		this.setKey(new Key<T>(obj, datasetId));
		this.setProperties(generateProperties(obj));
	}

	public Key<T> getKey() {
		return key;
	}

	public void setKey(Key<T> key) {
		this.key = key;
	}

	public HashMap<String, HashMap<String, Object>> getProperties() {
		return properties;
	}

	public void setProperties(
			HashMap<String, HashMap<String, Object>> properties) {
		this.properties = properties;
	}

	public HashMap<String, HashMap<String, Object>> generateProperties(T obj) {
		try {
			HashMap<String, HashMap<String, Object>> properties = new HashMap<String, HashMap<String, Object>>();
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				String genericType = f.getGenericType().toString();
				String fieldType = Utility.generateGoogleDataType(genericType);
				HashMap<String, Object> propertyValue = new HashMap<String, Object>();
				if (fieldType.equals("listValue")) {
					@SuppressWarnings("unchecked")
					List<Object> list = (List<Object>) f.get(obj);
					List<HashMap<String, Object>> listItemValue = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; i < list.size(); i++) {
						HashMap<String, Object> itemValue = new HashMap<String, Object>();
						String listItemType = list.get(i).getClass().toString();
						String typeValue = Utility
								.generateGoogleDataType(listItemType);
						itemValue.put(typeValue, typeValue
								.equals("integerValue") ? list.get(i)
								.toString() : list.get(i));
						listItemValue.add(itemValue);
					}
					propertyValue.put(fieldType, listItemValue);
				} else if (fieldType.equals("dateTimeValue")) {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					try {
						cal.setTime((Date) f.get(obj));
					} catch (Exception e) {
						e.printStackTrace();
					}

					Date a = (Date) f.get(obj);
					propertyValue.put(fieldType, dateFormat.format(a));
				} else {
					propertyValue.put(fieldType,
							genericType.contains("int") ? f.get(obj).toString()
									: f.get(obj));

				}
				properties.put(f.getName(), propertyValue);
				if (f.isAnnotationPresent(Index.class)) {
					propertyValue.put("indexed", true);
				}
			}
			return properties;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}
}
