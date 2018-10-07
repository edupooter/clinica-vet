package br.com.senacrs.appcrudcontatosdao.dao;

import java.util.List;

import br.com.senacrs.appcrudcontatosdao.model.Pet;

public interface PetDao {

    public void inserir(Pet pet);
    public void excluir(Pet pet);
    public void atualizar(Pet pet);
    public List<Pet> listar();
    public Pet procurarPorId(int id);

}
