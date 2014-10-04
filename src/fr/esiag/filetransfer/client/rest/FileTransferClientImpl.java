package fr.esiag.filetransfer.client.rest;

import java.io.File;














import fr.esiag.filetransfer.service.FileTransferService;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;
import android.os.AsyncTask;
import android.util.Log;

public class FileTransferClientImpl extends AsyncTask<Void, Void, Void> implements FileTransferClient  {

	/**
	 * @TODO : put the SERVER_UPLOAD_URL in a properties file for the code review and the .apk
	 */
	//public static final String SERVER_UPLOAD_URL = "http://192.168.43.223:8080/archicloud-tp1-filetransfer_rest/rest";
	public static final String SERVER_UPLOAD_URL = "http://1-dot-fr-esiag-isin-filetransfer.appspot.com/rest";
	//public static final String SERVER_UPLOAD_URL = "http://192.168.43.223:8888/rest";
	
	protected File file;
	
	public FileTransferClientImpl(){
		
	}
	
	public FileTransferClientImpl(File file){
		this.file = file;
	}
	
	@Override
	public void setFile(File file){
		this.file = file;
	}
	
	/**
	 * This method uses Retrofit library to create a REST Client 
	 * using the interface FileTransferService and the SERVER_UPLOAD_URL.
	 * 
	 * It needs that the file attribute be set with the File that we want
	 * to send to the server (by the Constructor or the setter)
	 * 
	 */
	@Override
	public void uploadFile() {

		
		RestAdapter restAdapter = new RestAdapter.Builder()
											.setEndpoint(SERVER_UPLOAD_URL)
											.build();
		
		FileTransferService service = restAdapter.create(FileTransferService.class);
				
		/**
		 * 	 The method exposed by the FileTransferService accept "Typed parameters" :
		 *  - TypedFile that takes the file and the Type/Mime (which the Rest Service 
		 *  	has to know
		 *  - TypedString that is instanciated by the name of the file to be saved on
		 *  	the server
		 */
		TypedFile typedFile = new TypedFile("multipart/form-data", file);
		TypedString typedString = new TypedString(file.getName());
		
		try{
			
			service.uploadFile(typedFile, typedString);
			
		} catch(RetrofitError e){

			String error = e.getCause().toString();

			Log.e("uploadFile", error);
		}
		
	}


	@Override
	protected Void doInBackground(Void... params) {
		uploadFile();
		return null;
	}
	
	
	
}
