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

import java.util.HashMap;
import java.util.List;

/**
 * @author xuanhung2401
 * 
 */
public class Query {

	private List<Projection> projection;
	private List<HashMap<String, String>> kinds;
	private Filter filter;
	private List<Order> order;

	private List<HashMap<String, String>> groupBy;

	private String startCursor;
	private String endCursor;
	private int offset;
	private int limit = 10;

	public List<Projection> getProjection() {
		return projection;
	}

	public void setProjection(List<Projection> projection) {
		this.projection = projection;
	}

	public List<HashMap<String, String>> getKinds() {
		return kinds;
	}

	public void setKinds(List<HashMap<String, String>> kinds) {
		this.kinds = kinds;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<HashMap<String, String>> getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(List<HashMap<String, String>> groupBy) {
		this.groupBy = groupBy;
	}

	public String getStartCursor() {
		return startCursor;
	}

	public void setStartCursor(String startCursor) {
		this.startCursor = startCursor;
	}

	public String getEndCursor() {
		return endCursor;
	}

	public void setEndCursor(String endCursor) {
		this.endCursor = endCursor;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
