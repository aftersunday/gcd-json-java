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

package cloud.google.oauth2;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.MessageFormat;

import org.apache.commons.codec.binary.Base64;

import cloud.google.datastore.GCDConfig;
import cloud.google.datastore.GCDStatic;
import cloud.google.util.ConnectionService;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author xuanhung2401
 */
public class MyWayAuthentication implements Authentication {

	private String iss;
	private String keystoreLoc;

	public MyWayAuthentication(GCDConfig config) {
		super();
		this.iss = config.getIss();
		this.keystoreLoc = config.getKeystoreLoc();
	}

	public String getAssertion(long exp, long iat) throws Exception {
		String header = "{\"alg\":\"RS256\",\"typ\":\"JWT\"}";
		String claimTemplate = "'{'\"iss\": \"{0}\", \"scope\": \"{1}\", \"aud\": \"{2}\", \"exp\": {3}, \"iat\": {4}'}'";
		StringBuffer token = new StringBuffer();

		/**
		 * Encode the JWT Header and add it to our string to sign
		 * */
		token.append(Base64.encodeBase64URLSafeString(header.getBytes("UTF-8")));

		/**
		 * Separate with a period
		 * */
		token.append(".");

		/**
		 * Create the JWT Claims Object
		 * */
		String[] claimArray = new String[6];
		claimArray[0] = this.iss;
		claimArray[1] = GCDStatic.getScope();
		claimArray[2] = GCDStatic.getAud();
		claimArray[3] = "" + exp;
		claimArray[4] = "" + iat;

		MessageFormat claims = new MessageFormat(claimTemplate);
		String payload = claims.format(claimArray);

		/**
		 * Add the encoded claims object
		 * */
		token.append(Base64.encodeBase64URLSafeString(payload.getBytes("UTF-8")));

		/**
		 * Load the private key
		 * */
		PrivateKey privateKey = getPrivateKey(this.keystoreLoc,
				GCDStatic.getPassword());
		byte[] sig = signData(token.toString().getBytes("UTF-8"), privateKey);

		String signedPayload = Base64.encodeBase64URLSafeString(sig);

		/**
		 * Separate with a period
		 * */
		token.append(".");

		/**
		 * Add the encoded signature
		 * */
		token.append(signedPayload);

		return token.toString();
	}

	/**
	 * Load p12 file get private key
	 * */
	private PrivateKey getPrivateKey(String keyFile, String password)
			throws KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		KeyStore keystore = KeyStore.getInstance("PKCS12");
		keystore.load(new FileInputStream(keyFile), password.toCharArray());
		PrivateKey privateKey = (PrivateKey) keystore.getKey(
				GCDStatic.getKeyAlias(), password.toCharArray());

		return privateKey;
	}

	/**
	 * Get signature from private key
	 * */
	public byte[] signData(byte[] data, PrivateKey privateKey)
			throws InvalidKeyException, SignatureException,
			NoSuchAlgorithmException {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * Just return access token
	 * */
	@Override
	public String getAccessToken() {
		try {
			long iat = (System.currentTimeMillis() / 1000) - 60;
			long exp = iat + 3600;
			String urlParameters = "grant_type=" + GCDStatic.getGrant()
					+ "&assertion=" + getAssertion(exp, iat);

			String result = ConnectionService
					.connect(GCDStatic.getAud())
					.body(urlParameters)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.post();
			JsonElement jelement = new JsonParser().parse(result);
			JsonObject jobject = jelement.getAsJsonObject();
			jelement = jobject.get("access_token");
			return jelement != null ? jelement.getAsString() : "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
