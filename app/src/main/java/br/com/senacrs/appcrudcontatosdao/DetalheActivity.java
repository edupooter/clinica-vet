package br.com.senacrs.appcrudcontatosdao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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


public class DetalheActivity extends AppCompatActivity {

    @BindView(R.id.editCEPDetalhe)
    EditText editCEP;
    @BindView(R.id.txtStreetDetalhe)
    EditText txtStreetDetalhe;
    @BindView(R.id.txtNeighborhoodDetalhe)
    EditText txtNeighborhoodDetalhe;
    @BindView(R.id.txtCityDetalhe)
    EditText txtCityDetalhe;
    @BindView(R.id.txtStateDetalhe)
    EditText txtStateDetalhe;

    private EditText textoNomePetDetalhe, textoNomeDonoDetalhe, textoTelefoneDetalhe;
    private TextView ViewEnderecoDetalhe;
    Pet petEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        petEditar = (Pet) intent.getSerializableExtra("pet");

        textoNomePetDetalhe = (EditText) findViewById(R.id.textoNomePetDetalhe);
        textoNomePetDetalhe.setText(petEditar.getNomePet());
        textoNomeDonoDetalhe = (EditText) findViewById(R.id.textoNomeDonoDetalhe);
        textoNomeDonoDetalhe.setText((CharSequence) petEditar.getNomeDono());
        textoTelefoneDetalhe = (EditText) findViewById(R.id.textoTelefoneDetalhe);
        textoTelefoneDetalhe.setText(petEditar.getTelefone());
        editCEP = (EditText) findViewById(R.id.editCEPDetalhe);
        editCEP.setText(petEditar.getCep());
        ViewEnderecoDetalhe = (TextView) findViewById(R.id.textViewEnderecoDetalhe);
        ViewEnderecoDetalhe.setText(petEditar.getEndereco());
        txtStreetDetalhe = (EditText) findViewById(R.id.txtStreetDetalhe);
        //txtStreetDetalhe.setText(petEditar.getEndereco());
        txtNeighborhoodDetalhe = (EditText) findViewById(R.id.txtNeighborhoodDetalhe);
        //txtNeighborhoodDetalhe.setText(petEditar.getEndereco());
        txtCityDetalhe = (EditText) findViewById(R.id.txtCityDetalhe);
        //txtCityDetalhe.setText(petEditar.getEndereco());
        txtStateDetalhe = (EditText) findViewById(R.id.txtStateDetalhe);
        //txtStateDetalhe.setText(petEditar.getEndereco());
    }

    public void editarPet(View v){
        petEditar.setNomePet(textoNomePetDetalhe.getText().toString());
        petEditar.setNomeDono(textoNomeDonoDetalhe.getText().toString());
        petEditar.setTelefone(textoTelefoneDetalhe.getText().toString());
        petEditar.setCep(editCEP.getText().toString());

        EditText rua = (EditText) findViewById(R.id.txtStreetDetalhe);
        EditText bairro = (EditText) findViewById(R.id.txtNeighborhoodDetalhe);
        EditText cidade = (EditText) findViewById(R.id.txtCityDetalhe);
        EditText estado = (EditText) findViewById(R.id.txtStateDetalhe);
        String endereco = rua.getText().toString() + ", " +
                bairro.getText().toString() + " - " +
                cidade.getText().toString() + "/" +
                estado.getText().toString();

        petEditar.setEndereco(endereco);

        PetDao dao = new CadastroDaoBd(this);
        dao.atualizar(petEditar);
        Toast.makeText(this,"Edição realizada com sucesso!", Toast.LENGTH_LONG)
                .show();
        finish();
    }

    public void excluirPet(View v){
        PetDao dao = new CadastroDaoBd(this);
        dao.excluir(petEditar);
        Toast.makeText(this,"Exclusão realizada com sucesso!",Toast.LENGTH_LONG)
                .show();
        finish();
    }

    // Exemplo de intent implícita
    public void sharePet(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Pet: " + textoNomePetDetalhe.getText().toString() +
                     "\nDono: " + textoNomeDonoDetalhe.getText().toString() +
                     "\nTelefone: " + textoTelefoneDetalhe.getText().toString() +
                     "\nEndereço: " + ViewEnderecoDetalhe.getText().toString() +
                     "\nCEP: " + editCEP.getText().toString()
        );
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }

    @OnClick(R.id.btnSearchDetalhe)
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
                    txtStreetDetalhe.setText(address.logradouro);
                    txtNeighborhoodDetalhe.setText(address.bairro);
                    txtCityDetalhe.setText(address.localidade);
                    txtStateDetalhe.setText(address.uf);
                }
            }

            @Override
            public void onFailure(
                    Call<Address> call,
                    Throwable t) {
            }
        });
    }

}
