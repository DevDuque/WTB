package br.team.wtb.Database;

import java.util.List;
import java.util.UUID;

public interface DAO <Type> {

    // Encontrar o todos dados
    public abstract List<Type> findAll();

    // Encontrar dados pelo ID
    public abstract Type findByID(UUID ID);

    // Inserir dados
    public abstract Type insert(Type OBJ);

    // Atualizar Dados
    public abstract boolean update(Type OBJ);

    // Deletar um dado
    public abstract boolean delete(UUID ID);
}