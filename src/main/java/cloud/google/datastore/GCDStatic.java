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
 * This class contain variable to authentication with google datastore json api.
 * Don't change anything.
 * */
public class GCDStatic {

	private static String scope = "https://www.googleapis.com/auth/datastore https://www.googleapis.com/auth/userinfo.email";
	private static String aud = "https://accounts.google.com/o/oauth2/token";
	private static String keyAlias = "privatekey";
	private static String password = "notasecret";
	private static String grant = "urn:ietf:params:oauth:grant-type:jwt-bearer";

	private static String datastoreApiUrl = "https://www.googleapis.com/datastore/v1beta2/datasets/";

	public static String getDatastoreApiUrl() {
		return datastoreApiUrl;
	}

	public static String getScope() {
		return scope;
	}

	public static String getAud() {
		return aud;
	}

	public static String getKeyAlias() {
		return keyAlias;
	}

	public static String getPassword() {
		return password;
	}

	public static String getGrant() {
		return grant;
	}
}
