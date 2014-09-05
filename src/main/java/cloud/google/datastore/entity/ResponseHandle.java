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

package cloud.google.datastore.entity;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cloud.google.datastore.entity.core.Entity;
import cloud.google.datastore.entity.core.Result;
import cloud.google.datastore.entity.lookup.LookupResponse;
import cloud.google.datastore.entity.query.QueryResponse;
import cloud.google.util.Utility;

import com.google.gson.internal.LinkedTreeMap;

/**
 * @author xuanhung2401
 * 
 */

/**
 * This class used to handle google api response, convert response string to
 * entity or list entity by type T.
 * */
public class ResponseHandle {

	public static <T> List<T> handleLookupIdsResponse(Class<T> clazz,
			String resp) {
		List<T> list = new ArrayList<T>();
		LookupResponse l = Utility.fromJsonToObject(LookupResponse.class, resp);
		if (l != null && l.getFound() != null && l.getFound().size() > 0) {
			for (Result<?> res : l.getFound()) {
				T obj = convertEntity(res.getEntity(), clazz);
				if (obj != null) {
					list.add(obj);
				}
			}
		}
		return list;
	}

	public static <T> T handleLookupIdResponse(Class<T> clazz, String resp) {
		LookupResponse l = Utility.fromJsonToObject(LookupResponse.class, resp);
		if (l != null && l.getFound() != null && l.getFound().size() > 0) {
			for (Result<?> res : l.getFound()) {
				T obj = convertEntity(res.getEntity(), clazz);
				if (obj != null) {
					return obj;
				}
			}
		}
		return null;
	}

	public static <T> List<T> handleQueryResponse(Class<T> clazz, String resp) {
		List<T> list = new ArrayList<T>();
		QueryResponse l = Utility.fromJsonToObject(QueryResponse.class, resp);
		if (l != null) {
			for (Result<?> res : l.getBatch().getEntityResults()) {
				T obj = convertEntity(res.getEntity(), clazz);
				if (obj != null) {
					list.add(obj);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> T convertEntity(Entity<?> entity, Class<T> clazz) {
		HashMap<String, HashMap<String, Object>> properties = entity
				.getProperties();
		try {
			Object obj = Class.forName(clazz.getName()).newInstance();

			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				HashMap<String, Object> fValue = properties.get(f.getName());
				if (fValue != null) {
					Object value = fValue.get(Utility.generateGoogleDataType(f
							.getGenericType().toString()));
					if (Utility.isStringField(f.getGenericType().toString())) {
						f.set(obj, (String) value);
					} else if (Utility.isIntegerField(f.getGenericType()
							.toString())) {
						f.set(obj, Integer.parseInt((String) value));
					} else if (Utility.isBooleanField(f.getGenericType()
							.toString())) {
						f.set(obj, Boolean.parseBoolean((String) value));
					} else if (Utility.isDateTimeField(f.getGenericType()
							.toString())) {
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						try {
							cal.setTime(sdf.parse((String) value));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						f.set(obj, cal.getTime());
					} else if (f.getGenericType() instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) f
								.getGenericType();
						if (pt.getActualTypeArguments().length > 0) {
							Type t = pt.getActualTypeArguments()[0];

							if (Utility.isStringField(t.toString())) {
								List<String> listObj = new ArrayList<String>();
								List<LinkedTreeMap<String, String>> listL = (List<LinkedTreeMap<String, String>>) value;
								for (LinkedTreeMap<String, String> linkedTreeMap : listL) {
									listObj.add(linkedTreeMap
											.get("stringValue"));
								}
								f.set(obj, listObj);
							} else if (Utility.isIntegerField(t.toString())) {
								List<Integer> listObj = new ArrayList<Integer>();
								List<LinkedTreeMap<String, String>> listL = (List<LinkedTreeMap<String, String>>) value;
								for (LinkedTreeMap<String, String> linkedTreeMap : listL) {
									listObj.add(Integer.parseInt(linkedTreeMap
											.get("integerValue")));
								}
								f.set(obj, listObj);
							} else if (Utility.isDoubleField(t.toString())) {
								List<Double> listObj = new ArrayList<Double>();
								List<LinkedTreeMap<String, Double>> listL = (List<LinkedTreeMap<String, Double>>) value;
								for (LinkedTreeMap<String, Double> linkedTreeMap : listL) {
									listObj.add(linkedTreeMap
											.get("doubleValue"));
								}
								f.set(obj, listObj);
							}
						}
					}
				}

			}
			return clazz.cast(obj);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
