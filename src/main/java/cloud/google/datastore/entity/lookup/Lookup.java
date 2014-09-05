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

package cloud.google.datastore.entity.lookup;

import java.util.List;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.entity.DataAction;

/**
 * @author xuanhung2401
 * 
 */
public class Lookup<T> extends DataAction<T> {

	public Lookup(GCDConfig config, Class<T> clazz) {
		DataAction.config = config;
		this.type = clazz;
	}

	/**
	 * To get an entity by string id, return a lookupId object that have get()
	 * method.
	 * */
	public LookupId<T> id(String id) {
		return new LookupId<T>(this.type, id);
	}

	/**
	 * To get a list entity by list string id, return a lookupIds object that
	 * have list() method
	 * */
	public LookupIds<T> ids(List<String> ids) {
		return new LookupIds<T>(this.type, ids);
	}

	/**
	 * To get a list entity by varargs string id, return a lookupIds object that
	 * have list() method
	 * */
	public LookupIds<T> ids(String... ids) {
		return new LookupIds<T>(this.type, ids);
	}
}
