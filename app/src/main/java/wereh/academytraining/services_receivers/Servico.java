package wereh.academytraining.services_receivers;

import android.app.Service;
import android.content.Intent;

import android.os.IBinder;

import android.util.Log;

/**
 * Created by wellington on 03/07/17.
 */

public class Servico extends Service {
    //    int id;
    //    int flag;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("Oncreate","passou");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //            id= startId;
        //            flag= flags;
            Worker w = new Worker(startId);
            w.start();

        return(START_NOT_STICKY);
    }

        //    public int startCronometer(Intent i, View view ) {
        //
        //        Worker w = new Worker(id, view);
        //        w.start();
        //
        //        return(START_NOT_STICKY);
        //    }


    class Worker extends Thread{
        //        public View view;
        //        public Chronometer chronometer;
        //        ContadorTreino contadorTreino = new ContadorTreino();

        public int count = 0;
        public int startId;
        public boolean ativo = true;



        public Worker(int startId){
            this.startId = startId;
        }

        //        public Worker(int startId, View view){
        //            this.startId = startId;
        //            this.view = view;
        //        }

        public void run(){
            while (ativo && count < 3){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                count++;
                Log.i("Contador", String.valueOf(count));
            }
           // contadorTreino.startCronometer(view);
            stopSelf(startId);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
