package mmb.b2f;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.util.Log;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;



public class FluxC extends AppWidgetProvider {
    private FluxCount FC;
    public static boolean Vstart=true;
    public static boolean widgetActive = false;
    public static int widgetId;
    public static AppWidgetManager appWidgetManager;
    public static Context context;
    
    
    public FluxC()
    {
    	FC = new FluxCount();
    }
    
    public void onUpdate(Context contextIn, AppWidgetManager appWidgetManagerIn,
			int[] appWidgetIds) {

    	if(!widgetActive)
    	{
     	 widgetId = appWidgetIds[0];
       	 context=contextIn;
       	 appWidgetManager=appWidgetManagerIn;
    	 FC.start();
    	 Log.i("FluxC", "UPDATE");
    	 widgetActive=true;
        }else
        {
          msg2usr("No more than one widget at the same time");	
        }
	}
    
    
    public void onDeleted(Context context, int[] appWidgetIds) {
    	
     	Log.i("FluxC", "Delete");
     	
     	if(appWidgetIds[0]== widgetId)
     	{
     	 try
     	 {
     	  FC.stop();
     	  widgetActive=false;
     	 }catch(Exception e)
     	 {
     		Log.i("FluxC", e.toString());	
     	 }
     	}
     	super.onDeleted(context, appWidgetIds);
     	
    }
    
    public void msg2usr(String msg)
    {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    
    public class FluxCount extends Handler {
    

     
     private int Vind;
     private int Lind;
     
     private boolean tmc;

     private int delayMillis=200;
     
     
     public FluxCount() {
    	    Vind=1;
    	    Lind=0;
    	    tmc=true;
     }
     

     public void handleMessage(Message msg) 
     {
    	 //Log.i("FluxC", "Vstart="+ FluxC.Vstart + " widgetId="+widgetId); 
    	 if(msg.what == 0 &&   	FluxC.Vstart)
    	 {
           animation();
    	 }
     }
     public void stop()
     {
    	FluxC.Vstart=false;     	 
     }
     public void start()
     {
    	 FluxC.Vstart=true;
    	 sendMessage(obtainMessage(0));
      }

     public void animation()
     {
    	 RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
    	 
    	 switch(Lind)
     	 {
     	   case 0:
     		   views.setImageViewResource(R.id.ifl, R.drawable.fcl);
     		   tmc=!tmc;
     		   Lind++;
     		   break;
     	   case 1:
      		   views.setImageViewResource(R.id.ifl, R.drawable.fcl2);
     		   Lind++;
     		   break;
     	   case 2:
      		   views.setImageViewResource(R.id.ifl, R.drawable.fcl3);
     		   Lind++;
     		   break;
     	   case 3:
      		   views.setImageViewResource(R.id.ifl, R.drawable.fcl4);
     		   Lind++;
     		   break;
     	   case 4:
      		   views.setImageViewResource(R.id.ifl, R.drawable.fcl5);Lind=0;
      		   tmc=!tmc;
     		   break;
     	}

        if(tmc)
    	{
     	 switch(Vind)
     	 {
     	   case 0:
     		   views.setImageViewResource(R.id.ifb, R.drawable.fc);
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc0);Vind++;
     		   break;
     	   case 1:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc1);Vind++;
     		   break;
     	   case 2:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc2);Vind++;
     		   break;
     	   case 3:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc3);Vind++;
     		   break;
     	   case 4:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc4);Vind++;
     		   break;
     	   case 5:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc5);Vind++;
     		   break;
     	   case 6:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc6);Vind++;
     		   break;
     	   case 7:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc7);Vind++;
     		   break;
     	   case 8:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc8);Vind++;
     		   break;
     	   case 9:
     		   views.setImageViewResource(R.id.ifn, R.drawable.fc9);Vind++;
     		   break;
      	   case 10:
     		   views.setImageViewResource(R.id.ifb, R.drawable.fcx);Vind++;
     		   views.setImageViewResource(R.id.ifn, R.drawable.fcxnb);
     		   break;
      	   case 11:
     		   views.setImageViewResource(R.id.ifb, R.drawable.fc);Vind++;
     		   views.setImageViewResource(R.id.ifn, R.drawable.fcxn);
     		   break;
      	   case 12:
     		   views.setImageViewResource(R.id.ifb, R.drawable.fcx);Vind=0;
     		   views.setImageViewResource(R.id.ifn, R.drawable.fcxnb);
     		   break;
            default:
     		  views.setImageViewResource(R.id.ifb, R.drawable.fc);Vind=0;
     	 }
     	 //Log.i("FluxC", "index=" + Vind);
    	}
     	
    	appWidgetManager.updateAppWidget(widgetId, views);
        sendMessageDelayed(obtainMessage(0),delayMillis);
     }
 
    
   }
}
