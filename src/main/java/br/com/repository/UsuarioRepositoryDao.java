package br.com.repository;

import br.com.entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuarioRepositoryDao extends CrudRepository<Usuario,Long> {

    @Query(value = "select u from Usuario u where u.email like %?1% and u.senha like %?2%")
    Usuario logar(String email,String senha);

    @Query(value = "select u from Usuario u where u.email = ?1 and u.senha =  ?2")
    Usuario logarNovo(String email,String senha);

    @Query(value = "select u from Usuario u where u.email like %?1%")
    List<Usuario> buscarPorEmail(String email);

    @Query(value = "select u from Usuario u where u.email = :email")
    Usuario buscarEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "delete from Usuario u where u.email = ?1")
    public void delePorNome(String email);

    @Modifying
    @Transactional
    @Query(value = "update Usuario  u set u.senha = ?1 where u.email = ?2 ")
    public void atualizaSenha(String senha,String email);
}
