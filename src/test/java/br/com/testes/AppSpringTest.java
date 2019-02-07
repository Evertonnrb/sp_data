package br.com.testes;

import br.com.entity.Contato;
import br.com.entity.Usuario;
import br.com.repository.ContatoRepositoryDao;
import br.com.repository.UsuarioRepositoryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/spring-config.xml"})
public class AppSpringTest {


    @Autowired
    private UsuarioRepositoryDao usuarioDao;
    @Autowired
    private ContatoRepositoryDao contatoDao;

    @Test
    public void testInit(){
        System.out.println("Carregou spring");
    }

    @Test
    public void salvarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("marola");
        usuario.setSenha("marola");
        usuarioDao.save(usuario);
        /*Usuario usuario1 = new Usuario("teste2@hotmail.com","ht32");
        usuarioDao.save(usuario1);
        Usuario usuario2 = new Usuario("teste3@yahooo.com.br","opop");
        usuarioDao.save(usuario2);
        System.out.println("Total de uusuario cadastrados "+usuarioDao.count());
        */
    }

    @Test
    public void pesquisarPorCodigo(){
        Optional<Usuario> usuario = usuarioDao.findById(1L);
        System.out.println(usuario.toString());
        System.out.println(usuario.get().getEmail());
    }

    @Test
    public void findAll(){
        Iterable<Usuario> usuario = usuarioDao.findAll();
        for(Usuario u : usuario){
            System.out.println(u.toString());
            System.out.println("===================");
        }
    }

    @Test
    public void atualizar(){
        Optional<Usuario> u = usuarioDao.findById(1L);
        Usuario usu = u.get();
        usu.setEmail("email alterado");
        usu.setSenha("senha alterada");
        usuarioDao.save(usu);
        Optional<Usuario> usuarioatualizado = usuarioDao.findById(1L);
        System.out.println("====================== ");
        System.out.println(usuarioatualizado.get().getEmail());
        System.out.println(usuarioatualizado.get().getSenha());
    }

    @Test
    public void deletar(){
      usuarioDao.deleteById(1L);
    }

    @Test
    public void buscarPorEmail(){
        List<Usuario> usuarios = usuarioDao.buscarPorEmail("teste");
        for(Usuario d : usuarios){
            System.out.println(d.toString());
        }
    }

    @Test
    public void testSaveLogar(){
      //  Usuario ao = new Usuario("ao","123");
        //usuarioDao.save(ao);
        Usuario p = usuarioDao.logar("ao","123");
        System.out.println(p.getEmail());
        System.out.println(p.getSenha());
    }

    @Test
    public void testSaveLogarNovo(){
        //  Usuario ao = new Usuario("ao","123");
        //usuarioDao.save(ao);
        Usuario p = usuarioDao.logarNovo("ao","123");
        System.out.println(p.getEmail());
        System.out.println(p.getSenha());
    }

    @Test
    public void buscarEmail(){
        Usuario usuario = usuarioDao.buscarEmail("ao");
        System.out.println(usuario.getEmail());
    }

    @Test
    public void deletarPorNome(){
        usuarioDao.delePorNome("usu");
    }

    @Test
    public void atualizarSenha(){
        usuarioDao.atualizaSenha("123456789","usu");
    }

    @Test
    public void testaDeveCadastrarContato(){
        Optional<Usuario> usuario = usuarioDao.findById(4L);
        Contato contato = new Contato();
        contato.setTipo("Celular");
        contato.setTelefone("67-xxxxxxxx");
        contato.setUsuario(usuario.get());
        contatoDao.save(contato);
    }

    @Test
    public void consultar(){
        Optional<Usuario> usu = usuarioDao.findById(2L);
        System.out.println(usu.toString());
        for(Contato contato : contatoDao.findAllById(Collections.singleton(2L))){
            System.out.println(contato.getTelefone());
            System.out.println(contato.getTipo());
        }
        System.out.println();
    }
}
