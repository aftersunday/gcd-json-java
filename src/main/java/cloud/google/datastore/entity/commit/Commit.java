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

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDStatic;
import cloud.google.datastore.entity.DataAction;
import cloud.google.datastore.entity.core.Entity;
import cloud.google.datastore.entity.core.Key;
import cloud.google.oauth2.AuthenticationFactory;
import cloud.google.util.ConnectionService;
import cloud.google.util.Utility;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author xuanhung2401
 * 
 */
public class Commit<T> extends DataAction<T> {

	private boolean success = false;

	private List<T> entities = null;

	private CommitRequestBody commitRequestBody;

	public Commit(GCDConfig config, Class<T> clazz) {
		DataAction.config = config;
		this.type = clazz;
	}

	public Commit<T> entities(List<T> listObj) {
		this.entities = listObj;
		return this;
	}

	@SuppressWarnings("unchecked")
	public Commit<T> entities(T... objs) {
		this.entities = new ArrayList<T>();
		for (T t : objs) {
			this.entities.add(t);
		}
		return this;
	}

	public List<Key<T>> insert() {
		List<Key<T>> listKey = new ArrayList<Key<T>>();
		if (this.entities != null) {
			List<List<T>> splittedList = Utility.splitList(this.entities, 5);
			for (List<T> list : splittedList) {
				InsertMutation<T> inserter = new InsertMutation<T>(list,
						config.getProjectName());
				this.commitRequestBody = new CommitRequestBody(inserter,
						getTransaction());
				execute();
				if (success) {
					for (Entity<T> entity : inserter.getInsert()) {
						listKey.add(entity.getKey());
					}
				}
			}
		}
		return listKey;
	}

	public List<Key<T>> update() {
		List<Key<T>> listKey = new ArrayList<Key<T>>();
		if (this.entities != null) {
			List<List<T>> splittedList = Utility.splitList(this.entities, 5);
			for (List<T> list : splittedList) {
				UpdateMutation<T> updater = new UpdateMutation<T>(list,
						config.getProjectName());
				this.commitRequestBody = new CommitRequestBody(updater,
						getTransaction());
				execute();
				if (success) {
					for (Entity<T> entity : updater.getUpdate()) {
						listKey.add(entity.getKey());
					}
				}
			}
		}
		return listKey;
	}

	public boolean delete() {
		if (this.entities != null) {
			List<List<T>> splittedList = Utility.splitList(this.entities, 5);
			for (List<T> list : splittedList) {
				this.commitRequestBody = new CommitRequestBody(
						new DeleteMutation<T>(list, config.getProjectName()),
						getTransaction());
				execute();
			}
		}
		return success;
	}

	private String getTransaction() {
		String result = ConnectionService
				.connect(
						GCDStatic.getDatastoreApiUrl()
								+ config.getProjectName() + "/beginTransaction")
				.body("")
				.accessToken(
						AuthenticationFactory.getInstance(config)
								.getAccessToken()).post();
		JsonElement jelement = new JsonParser().parse(result);
		JsonObject jobject = jelement.getAsJsonObject();
		jelement = jobject.get("transaction");
		return jelement != null ? jelement.getAsString() : "";
	}

	private void execute() {
		String result = ConnectionService
				.connect(
						GCDStatic.getDatastoreApiUrl()
								+ config.getProjectName() + "/commit")
				.body(this.commitRequestBody.getBody())
				.accessToken(
						AuthenticationFactory.getInstance(config)
								.getAccessToken()).post();
		if (result == null) {
			success = false;
		} else {
			success = true;
		}
	}

}
