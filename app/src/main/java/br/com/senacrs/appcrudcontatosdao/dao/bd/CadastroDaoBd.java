package br.com.senacrs.appcrudcontatosdao.dao.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.senacrs.appcrudcontatosdao.R;
import br.com.senacrs.appcrudcontatosdao.dao.PetDao;
import br.com.senacrs.appcrudcontatosdao.model.Pet;

public class CadastroDaoBd implements PetDao {
    private BancoDadosOpenHelper bdOpenHelper;

    public CadastroDaoBd(Context contexto){
        bdOpenHelper = new BancoDadosOpenHelper(contexto);
    }

    @Override
    public void inserir(Pet pet) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nomePet",pet.getNomePet());
        valores.put("nomeDono",pet.getNomeDono());
        valores.put("telefone",pet.getTelefone());
        valores.put("cep",pet.getCep());
        valores.put("endereco",pet.getEndereco());
        banco.insert("pet",null,valores);
        banco.close();
    }

    @Override
    public void excluir(Pet pet) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();
        banco.delete("pet","id=?",
                new String[]{String.valueOf(pet.getId())});
        banco.close();
    }

    @Override
    public void atualizar(Pet pet) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nomePet",pet.getNomePet());
        valores.put("nomeDono",pet.getNomeDono());
        valores.put("telefone",pet.getTelefone());
        valores.put("cep",pet.getCep());
        valores.put("endereco",pet.getEndereco());

        banco.update("pet",valores,"id=?",
                new String[]{String.valueOf(pet.getId())});
        banco.close();
    }

    @Override
    public List<Pet> listar() {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("pet",
                new String[]{"id","nomePet","nomeDono","telefone","cep","endereco"},
                null,null,null,null,"id");

        List<Pet> listaPets = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nomePet = cursor.getString(cursor.getColumnIndex("nomePet"));
            String nomeDono = cursor.getString(cursor.getColumnIndex("nomeDono"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            Pet pet = new Pet(id, R.mipmap.ic_pets, nomePet, nomeDono, telefone, cep, endereco);
            listaPets.add(pet);
        }
        return listaPets;
    }

    @Override
    public Pet procurarPorId(int id) {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("pet",
                new String[]{"id","nomePet","nomeDono","telefone","cep","endereco"},
                "id = ?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor.moveToNext()){
            int idX = cursor.getInt(cursor.getColumnIndex("id"));
            String nomePet = cursor.getString(cursor.getColumnIndex("nomePet"));
            String nomeDono = cursor.getString(cursor.getColumnIndex("nomeDono"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            Pet pet = new Pet(idX, R.mipmap.ic_pets, nomePet, nomeDono, telefone, cep, endereco);
            return pet;
        }
        return null;
    }
}
