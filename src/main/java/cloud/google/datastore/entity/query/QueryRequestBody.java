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
public class QueryRequestBody extends RequestBody {

	private PartitionId partitionId;

	private Query query;

	public QueryRequestBody() {

	}

	public PartitionId getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(PartitionId partitionId) {
		this.partitionId = partitionId;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	@Override
	public String getBody() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
