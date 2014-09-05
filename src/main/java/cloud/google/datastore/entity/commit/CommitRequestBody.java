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

import cloud.google.datastore.entity.RequestBody;

import com.google.gson.Gson;

/**
 * @author xuanhung2401
 */
public class CommitRequestBody extends RequestBody {

	private String transaction = "";
	private Mutation mutation;

	public <T> CommitRequestBody(Mutation mutation, String transaction) {
		this.mutation = mutation;
		this.transaction = transaction;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public Mutation getMutation() {
		return this.mutation;
	}

	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}

	@Override
	public String getBody() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
