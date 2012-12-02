package com.sumioturk.sashimi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.sumioturk.sashimi.core.AbstractSashimiApiService;
import com.sumioturk.sashimi.core.SashimiJoinService;
import com.sumioturk.sashimi.core.SashimiLoginService;
import com.sumioturk.sashimi.core.SashimiOAuthUrlService;
import com.sumioturk.sashimi.core.SashimiServiceExecutor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private View v;
	private EditText name;
	private EditText pass;
	private SeekBar d;
	private SeekBar h;
	private SeekBar m;
	private TextView dn;
	private TextView hn;
	private TextView mn;
	private Button b;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		d = (SeekBar) findViewById(R.id.d);
		h = (SeekBar) findViewById(R.id.h);
		m = (SeekBar) findViewById(R.id.m);
		b = (Button) findViewById(R.id.b);
		name = (EditText) findViewById(R.id.name);
		pass = (EditText) findViewById(R.id.pass);
		SharedPreferences settings = getSharedPreferences("UserPreferences", 0);
		String pref = settings.getString("SessionKey", "nope");

		d.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView wn = (TextView) findViewById(R.id.dn);
				int week = (int) Math.floor(30 * progress / 100);
				wn.setText(week + "");
			}
		});

		h.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView wn = (TextView) findViewById(R.id.hn);
				int week = (int) Math.floor(23 * progress / 100);
				wn.setText(week + "");
			}
		});

		m.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView wn = (TextView) findViewById(R.id.mn);
				int week = (int) Math.floor(59 * progress / 100);
				wn.setText(week + "");
			}
		});

		b.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dn = (TextView) findViewById(R.id.dn);
				hn = (TextView) findViewById(R.id.hn);
				mn = (TextView) findViewById(R.id.mn);
				String nameInput = name.getText().toString();
				String passInput = pass.getText().toString();
				int dayInput = new Integer(dn.getText().toString()).intValue();
				int hourInput = new Integer(hn.getText().toString()).intValue();
				int minuteInput = new Integer(mn.getText().toString())
						.intValue();
				int sashimi = dayInput * (1440) + hourInput * (60)
						+ minuteInput;
				if (nameInput == "" || passInput == "" || sashimi == 0) {
					if (nameInput == "") {
						name.setTextColor(128);
					}
					if (passInput == "") {
						pass.setTextColor(128);
					}
					if (minuteInput == 0) {
						mn.setTextColor(128);
					}
				} else {
					ArrayList<AbstractSashimiApiService> services = new ArrayList<AbstractSashimiApiService>();
					services.add(new SashimiJoinService(nameInput, passInput,
							new String("" + sashimi)));
					services.add(new SashimiLoginService(nameInput, passInput));
					SashimiServiceExecutor se = new SashimiServiceExecutor(
							services);
					ArrayList<String> results = null;
					try {
						results = se.execute();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					SharedPreferences settings = getSharedPreferences(
							"UserPreferences", 0);

					JSONObject json = null;
					String sessionKey = null;
					try {
						json = new JSONObject(results.get(1));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						sessionKey = json.get("session_key").toString();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("SessionKey", sessionKey);
					editor.commit();
					
					// oauth url 
					
					ArrayList<AbstractSashimiApiService> oauthservice = new ArrayList<AbstractSashimiApiService>();
					String oAuthUrl = null;
					oauthservice.add(new SashimiOAuthUrlService(sessionKey));
					SashimiServiceExecutor oauthse = new SashimiServiceExecutor(oauthservice);
					ArrayList<String> oauthResult = null;
					try {
						oauthResult = oauthse.execute();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						json = new JSONObject(oauthResult.get(0));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						oAuthUrl = json.get("auth_url").toString();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(oAuthUrl));
					startActivity(intent);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
