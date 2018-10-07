package br.com.senacrs.appcrudcontatosdao;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.senacrs.appcrudcontatosdao.adapter.PetAdapter;
import br.com.senacrs.appcrudcontatosdao.dao.PetDao;
import br.com.senacrs.appcrudcontatosdao.dao.bd.CadastroDaoBd;
import br.com.senacrs.appcrudcontatosdao.model.Pet;

public class MainActivity extends AppCompatActivity implements PetAdapter.PetOnClickListener {

    List<Pet> listaPets = new ArrayList<>();
    RecyclerView recyclerView;
    PetAdapter petAdapter;
    private CustomBroadCastReceiver receiver = new CustomBroadCastReceiver();
    private ComponentName componentName;
    private PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        petAdapter = new PetAdapter(getBaseContext(),listaPets,this);
        recyclerView.setAdapter(petAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Use LocalBroadcast to use it only on this app
        LocalBroadcastManager.getInstance(this).
                registerReceiver(receiver, new IntentFilter(CustomBroadCastReceiver.SHOW_NOTIFICATION));
    }

    @Override
    public void onResume() {
        super.onResume();
        PetDao dao = new CadastroDaoBd(this);
        listaPets = dao.listar();
        petAdapter.setListaPets(listaPets);
        petAdapter.notifyDataSetChanged();
        Log.e("Atividade retomada...", "onDestroy()");
    }

    @Override
    public void onClickPet(View view, int pos) {
        Intent it = new Intent(this, DetalheActivity.class);
        Pet pet = listaPets.get(pos);
        it.putExtra("pet",pet);
        startActivity(it);
    }

    public void abrirFormulario(View v){
        Intent it = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(it);
    }

    public void onStart() {
        super.onStart();

        // Conta quanto tempo o app está aberto - intent explícita
        Intent intent = new Intent(this, MyService.class);
        this.startService(intent);
        Log.w("Atividade: ", "iniciou o app");

        componentName = new ComponentName(this, CustomBroadCastReceiver.class);
        packageManager = getPackageManager();

        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onStop() {

        Log.e("Atividade interrompida.", "onStop()");
        super.onStop();
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onDestroy() {

        // Unregister the receiver
        LocalBroadcastManager.getInstance(this).
                unregisterReceiver(receiver);

        Log.e("Atividade encerrada!", "onDestroy()");
        super.onDestroy();
    }
}
