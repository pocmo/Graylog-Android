/*
Graylog - Android Client

Copyright 2009 Sebastian Kaspari

This file is part of Graylog (Android Client).

Graylog (Android Client) is free software: you can redistribute it
and/or modify it under the terms of the GNU General Public License
as published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

Graylog (Android Client) is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Graylog (Android Client). If not, see <http://www.gnu.org/licenses/>.
*/
package com.jimdo.graylog.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jimdo.graylog.R;

/**
 * The Login Activity - Everything start's here...
 * 
 * @author Sebastian Kaspari <s.kaspari@googlemail.com>
 */
public class LoginActivity extends Activity implements OnClickListener {
	public static final String TAG = "Graylog/LoginActivity";
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(this);
    }

	/**
	 * OnClickListener..
	 */
	public void onClick(View v)
	{
		Log.d(TAG, "Login...");
		startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
	}
}
