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

import java.util.List;

import cloud.google.datastore.entity.core.Result;

/**
 * @author xuanhung2401
 * 
 */
public class QueryResponseBatch<T> {

	private String entityResultType;
	private List<Result<T>> entityResults;
	private String endCursor;
	private String moreResults;
	private int skippedResults;

	public String getEntityResultType() {
		return entityResultType;
	}

	public void setEntityResultType(String entityResultType) {
		this.entityResultType = entityResultType;
	}

	public List<Result<T>> getEntityResults() {
		return entityResults;
	}

	public void setEntityResults(List<Result<T>> entityResults) {
		this.entityResults = entityResults;
	}

	public String getEndCursor() {
		return endCursor;
	}

	public void setEndCursor(String endCursor) {
		this.endCursor = endCursor;
	}

	public String getMoreResults() {
		return moreResults;
	}

	public void setMoreResults(String moreResults) {
		this.moreResults = moreResults;
	}

	public int getSkippedResults() {
		return skippedResults;
	}

	public void setSkippedResults(int skippedResults) {
		this.skippedResults = skippedResults;
	}

}
