package ZipFactoryPackage;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;
/**
 * Implementation d'un zipper et d'un unzipper
 * @author Ostro
 *
 */
public class ZipFactory {

	/**
	 * Unzip le fichier ou répertoire spécifié
	 * @param path Chemin vers le fichier ou répertoire à unzipper
	 * @throws IOException
	 */
	public static void Unzipper(String path) throws IOException{

		int BUFFER = 2048;
		File file = new File(path);

		ZipFile zip = new ZipFile(file);
		
		String newPath = path.substring(0, path.length() - 4);

		new File(newPath).mkdir();
		Enumeration zipFileEntries = zip.entries();

		// Process each entry
		while (zipFileEntries.hasMoreElements())
		{
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
			String currentEntry = entry.getName();
			File destFile = new File(newPath, currentEntry);
			//destFile = new File(newPath, destFile.getName());
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			if (!entry.isDirectory())
			{
				BufferedInputStream is = new BufferedInputStream(zip
						.getInputStream(entry));
				int currentByte;
				// establish buffer for writing file
				byte data[] = new byte[BUFFER];

				// write the current file to disk
				FileOutputStream fos = new FileOutputStream(destFile);
				BufferedOutputStream dest = new BufferedOutputStream(fos,
						BUFFER);

				// read and write until last byte is encountered
				while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, currentByte);
				}
				dest.flush();
				dest.close();
				is.close();

				
				
			
			}

			if (currentEntry.endsWith(".zip"))
			{
				// found a zip file, try to open
				Unzipper(destFile.getAbsolutePath());
			}
		}
		

		zip.close();

	}
	/**
	 * Unzip le fichier ou répertoire spécifié
	 * @param path Chemin vers le fichier ou répertoire a zipper	
	 * @param pathOut Chemin où mettre le fichier zip produit
	 * @throws IOException
	 */
	public static void Zipper (String path, String pathOut) throws IOException{


		File inFolder=new File(path);
		File outFolder=new File(pathOut);
		ZipOutputStream out = new ZipOutputStream(new 
				BufferedOutputStream(new FileOutputStream(outFolder)));
		BufferedInputStream in = null;
		byte[] data  = new byte[1000];
		String files[] = inFolder.list();
		for (int i=0; i<files.length; i++)
		{
			in = new BufferedInputStream(new FileInputStream
					(inFolder.getPath() + "/" + files[i]), 1000);  
			out.putNextEntry(new ZipEntry(files[i])); 
			int count;
			while((count = in.read(data,0,1000)) != -1)
			{
				out.write(data, 0, count);
			}
			out.closeEntry();
		}
		out.flush();
		out.close();
		in.close();
	}

}


