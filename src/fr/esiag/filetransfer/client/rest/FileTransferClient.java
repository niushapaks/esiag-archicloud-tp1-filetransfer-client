package fr.esiag.filetransfer.client.rest;

import java.io.File;


public interface FileTransferClient {
	
	void uploadFile();

	void setFile(File file);
}
