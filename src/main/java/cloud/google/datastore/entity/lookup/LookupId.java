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
public class LookupId<T> extends DataAction<T> {

	private LookupRequestBody<T> lookupRequestBody;

	public LookupId(Class<T> clazz, String id) {
		this.type = clazz;
		this.lookupRequestBody = new LookupRequestBody<T>(new Key<T>(
				config.getProjectName(), this.type.getSimpleName(), id));
	}

	/**
	 * Return an object type T
	 * */
	public T get() {
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
			return ResponseHandle.handleLookupIdResponse(this.type, result);
		}
		return null;
	}
}
