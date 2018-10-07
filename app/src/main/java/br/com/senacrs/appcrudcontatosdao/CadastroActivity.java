package br.com.senacrs.appcrudcontatosdao;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.senacrs.appcrudcontatosdao.dao.PetDao;
import br.com.senacrs.appcrudcontatosdao.dao.bd.CadastroDaoBd;
import br.com.senacrs.appcrudcontatosdao.model.Address;
import br.com.senacrs.appcrudcontatosdao.model.Pet;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CadastroActivity extends AppCompatActivity {

    private static final Object ONGOING_NOTIFICATION_ID = 1;
    @BindView(R.id.editCEPCadastro)
    EditText editCEP;
    @BindView(R.id.txtStreet)
    EditText txtStreet;
    @BindView(R.id.txtNeighborhood)
    EditText txtNeighborhood;
    @BindView(R.id.txtCity)
    EditText txtCity;
    @BindView(R.id.txtState)
    EditText txtState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);
    }

    public void cadastrarPet(View view){
        EditText textoNomeCadastroPet = (EditText) findViewById(R.id.textoNomeCadastroPet);
        EditText textoNomeCadastroDono = (EditText) findViewById(R.id.textoNomeCadastroDono);
        EditText textoTelefoneCadastro = (EditText) findViewById(R.id.textoTelefoneCadastro);
        EditText textoCepCadastro = (EditText) findViewById(R.id.editCEPCadastro);

        String nomePet = textoNomeCadastroPet.getText().toString();
        String nomeDono = textoNomeCadastroDono.getText().toString();
        String telefone = textoTelefoneCadastro.getText().toString();
        String cep = textoCepCadastro.getText().toString();

        EditText rua = (EditText) findViewById(R.id.txtStreet);
        EditText bairro = (EditText) findViewById(R.id.txtNeighborhood);
        EditText cidade = (EditText) findViewById(R.id.txtCity);
        EditText estado = (EditText) findViewById(R.id.txtState);
        String endereco = rua.getText().toString() + ", " +
                    bairro.getText().toString() + " - " +
                    cidade.getText().toString() + "/" +
                    estado.getText().toString();

        Pet pet = new Pet(R.mipmap.ic_pets, nomePet, nomeDono, telefone, cep, endereco);

        PetDao dao = new CadastroDaoBd(this);
        dao.inserir(pet);
        Toast.makeText(this,"Cadastro realizado com sucesso!",Toast.LENGTH_LONG)
                .show();
        finish();
    }

    @OnClick(R.id.btnSearch)
    public void onViewClicked() {

        Call<Address> call =
                ControlLifeCycle.service.detailCep(
                        editCEP.getText().toString());

        // assíncrona
        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(
                    Call<Address> call,
                    Response<Address> response) {
                if (response.isSuccessful()) {
                    Address address = response.body();
                    txtStreet.setText(address.logradouro);
                    txtNeighborhood.setText(address.bairro);
                    txtCity.setText(address.localidade);
                    txtState.setText(address.uf);
                }
            }

            @Override
            public void onFailure(
                    Call<Address> call,
                    Throwable t) {
            }
        });
    }

    public void cancelarCadastro(View view){
        finish();
    }

}
