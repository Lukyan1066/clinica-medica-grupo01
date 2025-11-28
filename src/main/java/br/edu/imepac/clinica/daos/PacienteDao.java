/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.imepac.clinica.daos;

import br.edu.imepac.clinica.entidades.Paciente;
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
public class PacienteDao extends BaseDao {
      
    public boolean salvar(Paciente entidade) {
        String sql = "INSERT INTO paciente (cpf, nome, n_convenio) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, entidade.getCpf());
            stmt.setString(2, entidade.getNome());
            stmt.setInt(3, entidade.getN_convenio());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o paciente: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos(conn, stmt);
        }
    }


    
    public boolean atualizar (Paciente entidade){
        String sql = "UPDATE paciente SET nome = ?, n_convenio = ? WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, entidade.getNome());
            stmt.setInt(2, entidade.getN_convenio());
            stmt.setString(3, entidade.getCpf());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos(conn, stmt);
        }
    }
    
 
    public boolean excluir(String cpf) {
        String sql = "DELETE FROM pacientes WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cpf);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir paciente: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos(conn, stmt);
        }
    }
    
    
    public Paciente buscarPorCpf(String cpf) {
        String sql = "SELECT cpf, nome, n_convenio FROM pacientes WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Paciente paciente = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setN_convenio(rs.getInt("n_convenio"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente por CPF: " + e.getMessage());

        } finally {
            fecharRecursos(conn, stmt, rs);
        }

        return paciente;
    }
    
    public List<Paciente> listarTodos() {
        String sql = "SELECT cpf, nome, n_convenio FROM pacientes";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> lista = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setN_convenio(rs.getInt("n_convenio"));
                lista.add(paciente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());

        } finally {
            fecharRecursos(conn, stmt, rs);
        }

        return lista;
    }

}
    
    
