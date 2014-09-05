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

import java.util.ArrayList;
import java.util.List;

import cloud.google.datastore.entity.RequestBody;
import cloud.google.datastore.entity.core.Key;

import com.google.gson.Gson;

/**
 * @author xuanhung2401
 * @param <T>
 * 
 */
public class LookupRequestBody<T> extends RequestBody {

	private List<Key<T>> keys;

	public LookupRequestBody(List<Key<T>> keys) {
		super();
		this.keys = keys;
	}

	public LookupRequestBody(Key<T> key) {
		super();
		if (this.keys == null) {
			this.keys = new ArrayList<Key<T>>();
		}
		this.keys.add(key);
	}

	@Override
	public String getBody() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public List<Key<T>> getKeys() {
		return keys;
	}

	public void setKeys(List<Key<T>> keys) {
		this.keys = keys;
	}
}
