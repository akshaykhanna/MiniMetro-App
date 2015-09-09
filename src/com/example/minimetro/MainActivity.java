package com.example.minimetro;







import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemSelectedListener, OnClickListener {

    Spinner spS,spD; 
    TextView tvD; 
    Button bVM,bGF;
    String station[]={"Noida","Akshardham","Rajiv Chowk","Rajouri Garden","Dwarka"};
    String st[]=new String[station.length-1];
    double time[]={0,5,13.2,20.6,29.5};
    double t,cost,basic_cost=7;
    
    String text="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_activity_main);
		intial();
	}

	private void intial() {
		// TODO Auto-generated method stub
		spS=(Spinner)findViewById(R.id.spSource);
		spD=(Spinner)findViewById(R.id.spDestination);
		tvD=(TextView)findViewById(R.id.tvData);
		bVM=(Button)findViewById(R.id.btViewImage);
		bGF=(Button)findViewById(R.id.btFare);
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,station);
		spS.setAdapter(adapter1);
		ArrayAdapter<String> adapter2=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,station);
		spD.setAdapter(adapter2);
		spS.setOnItemSelectedListener(this);
		spD.setOnItemSelectedListener(this);
		bGF.setOnClickListener(this);
		bVM.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	int si=0,di=0,d=0;
	float temp_t;
	 public void methodDest()
     {
		//tvD.setText("Trip Estimate");
		text="";
		 si=spS.getSelectedItemPosition();
			di=spD.getSelectedItemPosition();
	          if(si<di)
	        	  t=time[di]-time[si];
	          else
	        	  t=time[si]-time[di];
	           if(si<di)
	        	   d=di-si;
	           else
	        	   d=si-di;
	           cost=basic_cost + (d)*3;
	           temp_t=(float)t;
	           /*if(temp_t.indexOf('.')!=-1)
	           {
	        	   temp_t=temp_t.substring(0, temp_t.indexOf('.')) + temp_t.substring( temp_t.indexOf('.'),temp_t.indexOf('.')+3);
	           }*/
	           text+="Time: "+ temp_t+" seconds";
	           text+="\n Cost: Rs "+ cost+"/-";
	           tvD.setText(text);
     }
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		tvD.setText("Spinner selected");
		int k=0;
		switch(arg0.getId())
		{
		case R.id.spSource:
			//p=sp1.getSelectedItemPosition();
			methodDest();
			
			break;
		case R.id.spDestination:
		//p=sp2.getSelectedItemPosition();
			methodDest();
		
		}
		/*
		for(int i=0;i<station.length;i++)
		{
			if(i==spS.getSelectedItemPosition())
				continue;
			
			st[k]=station[i];
			k++;
		}*/
		//ArrayAdapter<String> adapter2=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,station);
		//spD.setAdapter(adapter2);
		/*switch(arg1.getId())
		{
		
		case R.id.spDestination: 
		*/
			/*
			si=spS.getSelectedItemPosition();
			di=spD.getSelectedItemPosition();
	          if(si<di)
	        	  t=time[di]-time[si];
	          else
	        	  t=time[si]-time[di];
	           if(si<di)
	        	   d=di-si;
	           else
	        	   d=si-di;
	           cost=basic_cost + (--d)*3;
	           text+="\n Time: "+ time+" min";
	           text+="\n Cost: Rs"+ cost+"/-";
	           tvD.setText(text);
	        	 
		break;
		default:
			
		} */
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
  
	int so,dest;
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.btFare:/// no 
		methodDest();
		break;
		case R.id.btViewImage:
			so=spS.getSelectedItemPosition();
			dest=spD.getSelectedItemPosition();
			Bundle suitcase=new Bundle();
			suitcase.putInt("s",so);
			suitcase.putInt("d",dest);
			Intent id=new Intent(MainActivity.this,Map.class);
			id.putExtras(suitcase);
				startActivity(id);
			break;	
		}
	}

}
