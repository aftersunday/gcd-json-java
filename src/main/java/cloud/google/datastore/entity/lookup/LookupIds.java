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

import cloud.google.datastore.GCDStatic;
import cloud.google.datastore.entity.DataAction;
import cloud.google.datastore.entity.ResponseHandle;
import cloud.google.datastore.entity.core.Key;
import cloud.google.oauth2.AuthenticationFactory;
import cloud.google.util.ConnectionService;

/**
 * @author xuanhung2401
 * 
 */
public class LookupIds<T> extends DataAction<T> {

	private LookupRequestBody<T> lookupRequestBody;

	public LookupIds(Class<T> type, List<String> ids) {
		this.type = type;
		List<Key<T>> listKey = new ArrayList<Key<T>>();
		for (String str : ids) {
			listKey.add(new Key<T>(config.getProjectName(), this.type
					.getSimpleName(), str));
		}
		this.lookupRequestBody = new LookupRequestBody<T>(listKey);
	}

	public LookupIds(Class<T> type, String... ids) {
		this.type = type;
		List<Key<T>> listKey = new ArrayList<Key<T>>();
		for (String str : ids) {
			listKey.add(new Key<T>(config.getProjectName(), this.type
					.getSimpleName(), str));
		}
		this.lookupRequestBody = new LookupRequestBody<T>(listKey);
	}

	/**
	 * Return list object by type T
	 * */
	public List<T> list() {
		String result = ConnectionService
				.connect(
						GCDStatic.getDatastoreApiUrl()
								+ config.getProjectName()
								+ "/lookup?fields=found")
				.body(this.lookupRequestBody.getBody())
				.accessToken(
						AuthenticationFactory.getInstance(config)
								.getAccessToken()).post();
		if (result != null) {
			return ResponseHandle.handleLookupIdsResponse(this.type, result);
		}
		return new ArrayList<T>();
	}
}
