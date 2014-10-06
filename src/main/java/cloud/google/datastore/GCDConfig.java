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

/**
 * <p>
 * <b> You need this class to initialize CGDService.</b>
 * </p>
 * This class save your project information include project id, account email
 * address and P12 key file location.
 * */
public class GCDConfig {

	private String projectName = "";
	private String iss = "";
	private String keystoreLoc = "";
	private String accessToken = "";

	/**
	 * <p>
	 * <b>Variable :</b>
	 * </p>
	 * <br/>
	 * 
	 * <p>
	 * - <b>projectName : </b>project id (appengine project id).
	 * </p>
	 * <p>
	 * - <b>iss : </b>service account email address. Visit <a
	 * href='https://console.developers.google.com'>https://console
	 * .developers.google.com</a> >> choose project >> credentials tab >> create
	 * new client ID >> choose Service account >> download PK12 key and then you
	 * can see service account email address.
	 * </p>
	 * <p>
	 * - <b>keystoreLoc : </b>PK12 key location. You can download this file from
	 * the same folder with iss.
	 * </p>
	 * */
	public GCDConfig(String projectName, String iss, String keystoreLoc) {
		super();
		this.projectName = projectName;
		this.iss = iss;
		this.keystoreLoc = keystoreLoc;
	}

	public GCDConfig(String projectName, String accessToken) {
		super();
		this.projectName = projectName;
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getKeystoreLoc() {
		return keystoreLoc;
	}

	public void setKeystoreLoc(String keystoreLoc) {
		this.keystoreLoc = keystoreLoc;
	}

}
