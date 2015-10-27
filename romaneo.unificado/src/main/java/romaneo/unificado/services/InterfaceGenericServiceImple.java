package romaneo.unificado.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import romaneo.unificado.daos.InterfaceDao;
import romaneo.unificado.domain.Interface;
import romaneo.unificado.exceptions.InterfaceAlreadyExistsException;

public abstract class InterfaceGenericServiceImple extends BaseServiceImple<Interface, InterfaceDao>
		implements InterfaceService {

	private String webAppFolder;
	private String folder;

	public String getWebAppFolder() {
		return webAppFolder;
	}

	public void setWebAppFolder(String webAppFolder) {
		this.webAppFolder = webAppFolder;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Override
	public Interface findByFilename(String filename, String type) {
		return dao.findByFilename(filename, type);
	}

	@Override
	public List<Interface> findUnprocessedAndAutoupload(String type) {
		return dao.findUnprocessedAndAutoupload(type);
	}

	@Override
	public List<Interface> findAll(String type) {
		return dao.findAll(type);
	}

	@Override
	public Interface uploadFile(InputStream dataFile, String filename, Boolean autoupload)
			throws InterfaceAlreadyExistsException, IOException {

		Interface interfaz = new Interface();
		FileOutputStream fos = null;
		Writer out = null;

		// Separo las carpetas
		String[] folders = getFolder().split("/");

		// Creo las carpetas si no existen
		String preFolders = "";
		for (String f : folders) {
			File folderIntDanone = new File(getWebAppFolder() + preFolders + "/" + f);
			if (!folderIntDanone.exists()) {
				folderIntDanone.mkdir();
			}
			preFolders = preFolders + "/" + f;
		}

		filename = getWebAppFolder() + getFolder() + "/" + filename;

		// Compruebo que la interface no este creada anteriormente
		if (dao.findByFilename(filename, getType()) == null) {

			// Persisto el archivo
			fos = new FileOutputStream(new File(filename));
			out = new OutputStreamWriter(fos, "UTF-8");
			int character;
			while ((character = dataFile.read()) != -1) {
				out.write(character);
			}
			out.flush();
			fos.flush();

			// Guardo la interface en la BD
			interfaz.setFilename(filename);
			interfaz.setUploadDate(new Date());
			interfaz.setAutoupload(autoupload);
			interfaz.setType(getType());

			dao.create(interfaz);

		} else
			throw new InterfaceAlreadyExistsException();

		if (out != null)
			out.close();
		if (fos != null)
			fos.close();

		return interfaz;
	}

	protected Date extractDateFromFilename(String filename) {
		Pattern pattern = Pattern.compile(".*([0-9]{4}.[0-9]{2}.[0-9]{2}).*");
		Matcher matcher = pattern.matcher(filename);
		if (matcher.find()) {
			try {
				return (new SimpleDateFormat("yyyy.MM.dd")).parse(matcher.group(1));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	protected Integer countLines(Interface interfaz) throws Exception {

		Scanner reader = new Scanner(new FileInputStream(interfaz.getFilename()), "UTF-8");

		Integer lines = 0;
		while (reader.hasNext()) {
			String line = reader.nextLine();

			if (line.length() != 0 && line.length() == 451)
				lines++;
		}
		reader.close();

		return lines;
	}

}