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

/**
 * @author xuanhung2401
 * 
 */
public class Projection {

	private HashMap<String, String> property;

	/**
	 * Optional. Acceptable values are: "FIRST". Aggregation functions: first
	 * selects the first result as determined by the query's order.
	 * */
	private String aggregationFunction;

	public HashMap<String, String> getProperty() {
		return property;
	}

	public void setProperty(HashMap<String, String> property) {
		this.property = property;
	}

	public String getAggregationFunction() {
		return aggregationFunction;
	}

	public void setAggregationFunction(String aggregationFunction) {
		this.aggregationFunction = aggregationFunction;
	}
}
