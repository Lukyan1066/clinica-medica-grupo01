/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica.daos;
import br.edu.imepac.clinica.entidades.Usuario;
import br.edu.imepac.clinica.interfaces.Persistente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author ypth
 */
public class UsuarioDao extends BaseDao implements Persistente<Usuario>  {
    
  
    @Override
    public boolean salvar(Usuario entidade) {
        String sql = "INSERT INTO usuarios (usuario, senha, cargo) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, entidade.getUsuario());
            stmt.setString(2, entidade.getSenha());
            stmt.setString(3, entidade.getCargo());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuario: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos(conn, stmt);
        }
    }
    
    @Override
    public boolean atualizar(Usuario entidade) {
        String sql = "UPDATE usuarios SET usuario = ?, senha = ?, cargo = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, entidade.getUsuario());
            stmt.setString(2, entidade.getSenha());
            stmt.setString(3, entidade.getCargo());
            stmt.setLong(4, entidade.getId());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuario: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos(conn, stmt);
        }
    }
    
    @Override
    public boolean excluir(long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuario: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos(conn, stmt);
        }
    }
    
    public boolean alterarStatus(long id, Object status) {
        String sql = "UPDATE usuarios SET status = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        
        String statusString = status.equals("ativo") ? "inativo" : "ativo";

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, statusString);
            stmt.setLong(2, id);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao alterar status: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos(conn, stmt);
        }
    }
    
    @Override
    public Usuario buscarPorId(long id) {
        String sql = "SELECT id, usuario, senha FROM usuarios WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setUsuario(rs.getString("usuario"));
                String senhaString = rs.getString("senha");
                usuario.setSenha(senhaString);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuario por ID: " + e.getMessage());

        } finally {
            fecharRecursos(conn, stmt, rs);
        }

        return usuario;
    }
    
    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT id, usuario, senha, cargo, status FROM usuarios";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setUsuario(rs.getString("usuario"));
                String senhaString = rs.getString("senha");
                usuario.setSenha(senhaString);
                usuario.setCargo(rs.getString("cargo"));
                usuario.setStatus(rs.getString("status"));
                lista.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuarios: " + e.getMessage());

        } finally {
            fecharRecursos(conn, stmt, rs);
        }

        return lista;
    }
    
    public Usuario autenticar(String usuario, String senha) throws SQLException {
    String sql = "SELECT id, usuario, senha, cargo, status FROM usuarios " +
                 "WHERE usuario = ? AND senha = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, senha);
        rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getLong("id"));
            u.setUsuario(rs.getString("usuario"));
            u.setSenha(rs.getString("senha"));
            u.setCargo(rs.getString("cargo"));
            u.setStatus(rs.getString("status"));
            return u;
        }
        return null;
    } finally {
        fecharRecursos(conn, stmt, rs);
    }
}


}
    
    
