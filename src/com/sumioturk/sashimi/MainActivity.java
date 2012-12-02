package com.sumioturk.sashimi;

import java.util.ArrayList;

import com.sumioturk.sashimi.core.AbstractSashimiApiService;
import com.sumioturk.sashimi.core.SashimiJoinService;
import com.sumioturk.sashimi.core.SashimiLoginService;
import com.sumioturk.sashimi.core.SashimiServiceExecutor;

import android.app.Activity;
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
					SashimiServiceExecutor se = new SashimiServiceExecutor(services);
					ArrayList<String> results = se.execute();
					System.out.print("c");
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
