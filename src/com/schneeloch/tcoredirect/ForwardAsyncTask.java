package com.schneeloch.tcoredirect;

import java.net.URI;
import java.net.URISyntaxException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

public class ForwardAsyncTask extends AsyncTask<Object, Integer, Object>
{
	private String exceptionMessage;
	private ProgressDialog progressDialog;
	private final Main context;
	private String newUrl;
	private final Uri oldUri;
	
	public ForwardAsyncTask(Main context, Uri uri)
	{
		this.context = context;
		this.oldUri = uri;
	}
	
	
	@Override
	protected void onPreExecute() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("Opening " + oldUri);
		progressDialog.show();
	}
	
	@Override
	protected Object doInBackground(Object... arg0) {
		doStuff();
		return null;
	}
	
	
	protected Object doStuff()
	{
		try {
			
			final URI uri = new URI(oldUri.getScheme(), oldUri.getUserInfo(), "233.im", 
					oldUri.getPort(), "/" + oldUri.getHost() + oldUri.getPath(), oldUri.getQuery(), oldUri.getFragment());

			newUrl = uri.toString();
		}
		catch (URISyntaxException e) {
			report("Problem parsing uri", e);
		}
		catch (Throwable t)
		{
			report("Unknown problem", t);
		}
		
		return null;
	}
	
	private void report(String message)
	{
		exceptionMessage = message;
	}
	
	private void report(String message, Throwable e) {
		report(message + ": " + e.toString());
	}


	@Override
	protected void onPostExecute(Object result) {
		progressDialog.dismiss();
		
		if (newUrl != null)
		{
			Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newUrl));
			context.startActivity(newIntent);
		}
		else if (exceptionMessage != null)
		{
			Toast.makeText(context, exceptionMessage, Toast.LENGTH_LONG).show();
		}
		
		context.finish();
	}
}
