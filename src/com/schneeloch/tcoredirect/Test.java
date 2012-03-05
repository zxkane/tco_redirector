package com.schneeloch.tcoredirect;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Test extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		final EditText text = (EditText) findViewById(R.id.editText1);
		
		Button browse = (Button) findViewById(R.id.button1);
		browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = String.valueOf(text.getText());
				if (!(url.startsWith("http") || url.startsWith("https")))
					url = "http://" + url;
				Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				Test.this.startActivity(newIntent);
			}
		});
	}

}
