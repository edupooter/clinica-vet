package br.com.senacrs.appcrudcontatosdao;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;

public class MyService extends Service implements Runnable {

    private static final int MAX = 1000000000;
    private static final String LOG = "serviço";
    public String tempo = "0";
    protected int count;
    private boolean ativo;

    @BindView(R.id.uptime)
    TextView uptime;

    @Override
    public void onCreate() {
        Log.i(LOG, "ExemploServico.onCreate()");
        ativo = true;
        // delega para uma thread
        new Thread(this).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(LOG, "ExemploServico.onStart()");
//        uptime.setText("0");
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }

    @Override
    public void onDestroy() {
        // ao encerrar altera a flag para thread parar
        ativo = false;
        Log.e(LOG, "ExemploServico.onDestroy()");
    }

    @Override
    public void run() {
        while (ativo && count < MAX) {
            fazAlgumaCoisa();
            Log.i(LOG, "Executando. count=" + count);
            count++;
        }
        Log.i(LOG, "ExemploServico.Fim");
        stopSelf();
    }

    private void fazAlgumaCoisa() {
        try {
            Thread.sleep(1000);
            //tempo = String.valueOf(count);
            //uptime.setText(tempo);
            Log.i(LOG, tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
