/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.utileria;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

public class HttpHelper {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private String base;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public HttpHelper(String base) {
		this.base = base;
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	public String doGet(String path) {
		return doRequest(path, "GET");
	}

	public String doRequest(String path, String modo) {
		try {
			URL url = new URL(base + path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod(modo);
			con.setRequestProperty("Accept", "application/json");

			int responseCode = con.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				return null;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);

			in.close();

			return response.toString();
		} catch (Exception e) {
			return null;
		}
	}
}