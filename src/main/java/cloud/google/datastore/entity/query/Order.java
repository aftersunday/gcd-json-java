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
public class Order {

	private HashMap<String, String> property;
	/**
	 * Acceptable values are: "ASCENDING": "DESCENDING":
	 * */
	private String direction = "ASCENDING";

	public HashMap<String, String> getProperty() {
		return property;
	}

	public void setProperty(HashMap<String, String> property) {
		this.property = property;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
