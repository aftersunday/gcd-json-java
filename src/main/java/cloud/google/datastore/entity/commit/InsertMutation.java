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

package cloud.google.datastore.entity.commit;

import java.util.ArrayList;
import java.util.List;

import cloud.google.datastore.entity.core.Entity;

/**
 * @author xuanhung2401
 * @param <T>
 * 
 */
public class InsertMutation<T> extends Mutation {

	private List<Entity<T>> insert;

	public InsertMutation(List<T> obj, String datasetId) {
		insert = new ArrayList<Entity<T>>();
		for (T t : obj) {
			insert.add(new Entity<T>(t, datasetId));
		}
	}

	public List<Entity<T>> getInsert() {
		return insert;
	}

	public void setInsert(List<Entity<T>> insert) {
		this.insert = insert;
	}

}
