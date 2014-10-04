package fr.esiag.filetransfer.client;

import java.io.File;

import fr.esiag.archicloud_tp1_filetransfer.client.R;
import fr.esiag.filetransfer.client.rest.FileTransferClientImpl;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class FileTransferActivity extends ActionBarActivity {

	/**
	 * Method called at the opening of the Application
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_transfer);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		/**
		 * Instanciation of the REST Asynchronous Clients and the Files to be sent to the Server
		 */
		FileTransferClientImpl fileTransferClient1 = new FileTransferClientImpl(new File("/sdcard/Download/cours1-1.pdf"));
		FileTransferClientImpl fileTransferClient2 = new FileTransferClientImpl(new File("/sdcard/dcim/Camera/IMG_20140919_161537.jpg"));
		FileTransferClientImpl fileTransferClient3 = new FileTransferClientImpl(new File("/sdcard/Music/PV Nova/Goodbye.mp3"));
		FileTransferClientImpl fileTransferClient4 = new FileTransferClientImpl(new File("/sdcard/Music/Wes Burden/Brave New World/Mirror.mp3"));
		
		/**
		 * Executing the Asynchronous doInBackround() methods which will do the jobs in differents Threads
		 */
		fileTransferClient1.execute();
		fileTransferClient2.execute();
		fileTransferClient3.execute();
		fileTransferClient4.execute();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_transfer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_file_transfer,
					container, false);
			return rootView;
		}
	}
}
