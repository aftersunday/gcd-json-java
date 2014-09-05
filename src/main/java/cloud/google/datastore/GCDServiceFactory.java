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

package cloud.google.datastore;

/**
 * @author xuanhung2401
 * 
 */

public class GCDServiceFactory {

	private static GCDService gcdService;

	public static GCDService getInstance(GCDConfig config) {
		if (gcdService == null) {
			gcdService = new GCDService(config);
		}
		return gcdService;
	}

	public static GCDService getDefaultInstance() {
		if (gcdService == null) {
			String projectName = System.getProperty("projectName");
			String iss = System.getProperty("iss");
			String keystoreLoc = System.getProperty("keystoreLoc");
			gcdService = new GCDService(new GCDConfig(projectName, iss,
					keystoreLoc));
		}
		return gcdService;
	}

	public static void main(String[] args) {
		GCDServiceFactory.getDefaultInstance();
	}
}
