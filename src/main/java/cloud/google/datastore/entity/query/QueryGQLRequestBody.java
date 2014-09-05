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

import cloud.google.datastore.entity.RequestBody;
import cloud.google.datastore.entity.core.PartitionId;

import com.google.gson.Gson;

/**
 * @author xuanhung2401
 * 
 */
public class QueryGQLRequestBody extends RequestBody {

	private PartitionId partitionId;
	private QueryGQL gqlQuery;

	public PartitionId getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(PartitionId partitionId) {
		this.partitionId = partitionId;
	}

	public QueryGQL getGqlQuery() {
		return gqlQuery;
	}

	public void setGqlQuery(QueryGQL gqlQuery) {
		this.gqlQuery = gqlQuery;
	}

	@Override
	public String getBody() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
