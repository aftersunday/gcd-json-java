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

import java.util.ArrayList;
import java.util.List;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDStatic;
import cloud.google.datastore.entity.DataAction;
import cloud.google.datastore.entity.ResponseHandle;
import cloud.google.datastore.entity.core.PartitionId;
import cloud.google.oauth2.AuthenticationFactory;
import cloud.google.util.ConnectionService;

/**
 * @author xuanhung2401
 * @param <T>
 * 
 */
public class RunQueryGQL<T> extends DataAction<T> {

	private QueryGQLRequestBody queryGQLRequestBody;

	public RunQueryGQL(GCDConfig config, Class<T> clazz) {
		DataAction.config = config;
		this.type = clazz;
		this.queryGQLRequestBody = new QueryGQLRequestBody();
		this.queryGQLRequestBody.setPartitionId(PartitionId.getInstance(config
				.getProjectName()));
		this.queryGQLRequestBody.setGqlQuery(new QueryGQL());
	}

	public RunQueryGQL<T> allowLiteral(boolean allow) {
		this.queryGQLRequestBody.getGqlQuery().setAllowLiteral(allow);
		return this;
	}

	public RunQueryGQL<T> queryString(String queryString) {
		this.queryGQLRequestBody.getGqlQuery().setQueryString(queryString);
		return this;
	}

	public List<T> list() {
		String result = ConnectionService
				.connect(
						GCDStatic.getDatastoreApiUrl()
								+ config.getProjectName() + "/runQuery")
				.body(this.queryGQLRequestBody.getBody())
				.accessToken(
						AuthenticationFactory.getInstance(config)
								.getAccessToken()).post();
		if (result != null) {
			return ResponseHandle.handleQueryResponse(type, result);
		}
		return new ArrayList<T>();
	}
}
