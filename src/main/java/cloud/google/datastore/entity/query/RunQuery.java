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

package cloud.google.datastore.entity.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDStatic;
import cloud.google.datastore.entity.DataAction;
import cloud.google.datastore.entity.ResponseHandle;
import cloud.google.datastore.entity.core.PartitionId;
import cloud.google.oauth2.AuthenticationFactory;
import cloud.google.util.ConnectionService;
import cloud.google.util.Utility;

/**
 * @author xuanhung2401
 * 
 */
public class RunQuery<T> extends DataAction<T> {

	private QueryRequestBody queryRequestBody;

	public RunQuery(GCDConfig config, Class<T> clazz) {
		DataAction.config = config;
		// init QueryRequestBody
		this.queryRequestBody = new QueryRequestBody();
		this.queryRequestBody.setPartitionId(PartitionId.getInstance(config
				.getProjectName()));

		this.type = clazz;
		// init Query
		Query q = new Query();
		HashMap<String, String> kindItem = new HashMap<String, String>();
		kindItem.put("name", this.type.getSimpleName());
		List<HashMap<String, String>> listKind = new ArrayList<HashMap<String, String>>();
		listKind.add(kindItem);
		q.setKinds(listKind);
		this.queryRequestBody.setQuery(q);
	}

	public RunQuery<T> offset(int offset) {
		this.queryRequestBody.getQuery().setOffset(offset);
		return this;
	}

	public RunQuery<T> order(String field, OrderDirection direction) {
		List<Order> listOrder = this.queryRequestBody.getQuery().getOrder();
		if (listOrder == null) {
			listOrder = new ArrayList<Order>();
		}
		HashMap<String, String> property = new HashMap<String, String>();
		property.put("name", field);
		Order order = new Order();
		order.setProperty(property);
		order.setDirection(direction.name());
		listOrder.add(order);
		this.queryRequestBody.getQuery().setOrder(listOrder);
		return this;
	}

	public RunQuery<T> limit(int limit) {
		this.queryRequestBody.getQuery().setLimit(limit);
		return this;
	}

	public RunQuery<T> filter(String field, Object value,
			FilterOperator operator) {
		Filter filter = this.queryRequestBody.getQuery().getFilter();
		if (filter == null) {
			filter = new Filter();
			filter.setPropertyFilter(initPropertyFilter(field, value, operator));
		} else {
			if (filter.getCompositeFilter() != null) {
				// add to composite filter.
				CompositeFilter compositeFilter = filter.getCompositeFilter();
				Filter[] filters = compositeFilter.getFilters();

				PropertyFilter pF = initPropertyFilter(field, value, operator);
				Filter f2 = new Filter();
				f2.setPropertyFilter(pF);
				filters = addFilterArray(filters, f2);

				compositeFilter.setFilters(filters);

				filter = new Filter();
				filter.setCompositeFilter(compositeFilter);
			} else if (filter.getPropertyFilter() != null) {
				// make new composite filter.
				Filter f1 = filter;
				PropertyFilter pF = initPropertyFilter(field, value, operator);
				Filter f2 = new Filter();
				f2.setPropertyFilter(pF);
				Filter[] filters = new Filter[] { f1, f2 };
				CompositeFilter compositeFilter = new CompositeFilter();
				compositeFilter.setFilters(filters);

				filter = new Filter();
				filter.setCompositeFilter(compositeFilter);
			} else {
				// make new property filter.
				filter.setPropertyFilter(initPropertyFilter(field, value,
						operator));
			}
		}
		this.queryRequestBody.getQuery().setFilter(filter);
		return this;
	}

	public List<T> list() {
		String result = ConnectionService
				.connect(
						GCDStatic.getDatastoreApiUrl()
								+ config.getProjectName() + "/runQuery")
				.body(this.queryRequestBody.getBody())
				.accessToken(
						AuthenticationFactory.getInstance(config)
								.getAccessToken()).post();
		if (result != null) {
			return ResponseHandle.handleQueryResponse(this.type, result);
		}
		return new ArrayList<T>();
	}

	private Filter[] addFilterArray(Filter[] a, Filter e) {
		a = Arrays.copyOf(a, a.length + 1);
		a[a.length - 1] = e;
		return a;
	}

	private PropertyFilter initPropertyFilter(String field, Object value,
			FilterOperator operator) {
		PropertyFilter fil = new PropertyFilter();

		HashMap<String, String> property = new HashMap<String, String>();
		property.put("name", field);
		fil.setProperty(property);

		HashMap<String, Object> val = new HashMap<String, Object>();
		String gType = Utility.generateGoogleDataType(value.getClass()
				.toString());
		if (gType.equals("dateTimeValue")) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			value = sdf.format(date);
		}
		val.put(gType, value);
		fil.setValue(val);
		fil.setOperator(operator.name());
		return fil;
	}
}
