package fr.esiag.filetransfer.service;

import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public interface FileTransferService {
	
	/**
	 * Multipart POST rest upload service method definition
	 * 
	 *  used by Retrofit to create a RestAdapter in order to use the Rest service 
	 *  in an easy way
	 * 
	 * @param file TypedFile
	 * @param filename TypedString
	 * @return
	 */
	@Multipart
	@POST("/filetransfer/upload")
	public Response uploadFile(@Part("file") TypedFile file, @Part("filename") TypedString filename);
	
}
