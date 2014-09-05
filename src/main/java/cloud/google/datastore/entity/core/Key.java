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

package cloud.google.datastore.entity.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cloud.google.datastore.annotation.Annotation.Id;

/**
 * @author xuanhung2401
 * @param <T>
 * 
 */
public class Key<T> {

	private PartitionId partitionId;
	private List<Path> path;

	public Key(T obj, String datasetId) {
		String pathKind = obj.getClass().getSimpleName();
		String pathName = "";
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.isAnnotationPresent(Id.class)) {
				try {
					pathName = f.get(obj).toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		List<Path> listPath = new ArrayList<>();
		listPath.add(Path.getInstance(pathKind, pathName));
		this.path = listPath;
		this.partitionId = PartitionId.getInstance(datasetId);
	}

	public Key(String datasetId, String clazzName, String id) {
		this.partitionId = new PartitionId(datasetId);
		if (this.path == null) {
			this.path = new ArrayList<Path>();
		}
		this.path.add(new Path(clazzName, id));
	}

	public Key(String datasetId, String clazzName, List<String> ids) {
		this.partitionId = new PartitionId(datasetId);
		if (this.path == null) {
			this.path = new ArrayList<Path>();
		}
		List<Path> listPath = new ArrayList<>();
		for (String id : ids) {
			listPath.add(Path.getInstance(clazzName, id));
		}
		this.path = listPath;
	}

	// /**
	// * @param t
	// * @param datasetId
	// * @param obj
	// */
	// public Key(T t, String datasetId, List<T> obj) {
	// this.partitionId = new PartitionId(datasetId);
	// String pathKind = t.getClass().getSimpleName();
	// List<String> ids = new ArrayList<String>();
	// for (T ob : obj) {
	// for (Field f : ob.getClass().getDeclaredFields()) {
	// f.setAccessible(true);
	// if (f.isAnnotationPresent(Id.class)) {
	// try {
	// ids.add(f.get(ob).toString());
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	//
	// List<Path> listPath = new ArrayList<>();
	// for (String id : ids) {
	// listPath.add(Path.getInstance(pathKind, id));
	// }
	// this.path = listPath;
	// }

	public PartitionId getPartitionId() {
		return partitionId;
	}

	public List<Path> getPath() {
		return path;
	}

}
