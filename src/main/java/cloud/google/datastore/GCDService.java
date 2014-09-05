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

package cloud.google.datastore;

import cloud.google.datastore.entity.commit.Commit;
import cloud.google.datastore.entity.lookup.Lookup;
import cloud.google.datastore.entity.query.RunQuery;
import cloud.google.datastore.entity.query.RunQueryGQL;

/**
 * @author xuanhung2401
 * 
 */

/**
 * This class help developer process google cloud datastore through
 * <b>non-appengine-application</b> using json api. In this version, developer
 * can do action commit, lookup or query.
 * */
public class GCDService {

	protected GCDConfig config;

	public GCDService(GCDConfig config) {
		super();
		this.config = config;
	}

	/**
	 * Insert, update, delete an entity to datastore. Use this action.
	 * 
	 * @param <T>
	 * */

	public <T> Commit<T> commit(Class<T> clazz) {
		return new Commit<T>(this.config, clazz);
	}

	/**
	 * Get entity from datastore by id or list of id. Use this action.
	 * */
	public <T> Lookup<T> lookup(Class<T> clazz) {
		return new Lookup<T>(this.config, clazz);
	}

	/**
	 * Query, select, filter, order, limit, offset by parameters
	 * */
	public <T> RunQuery<T> query(Class<T> clazz) {
		return new RunQuery<T>(this.config, clazz);
	}

	/**
	 * Query using your query string
	 * */
	public <T> RunQueryGQL<T> gqlQuery(Class<T> clazz) {
		return new RunQueryGQL<T>(this.config, clazz);
	}

}
