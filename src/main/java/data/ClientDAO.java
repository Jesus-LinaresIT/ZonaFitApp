package data;

import zona_fit.conection.Conection;
import zona_fit.domain.ClientFit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientDAO {
    @Override
    public List<ClientFit> listClients() {
        List<ClientFit> clients = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = Conection.getConection();
        String sql = "SELECT * FROM client ORDER BY id";

        try{
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientFit client = new ClientFit();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastname"));
                client.setSuscription(resultSet.getInt("suscription"));
                clients.add(client);
            }
        }catch (Exception e){
            System.out.println("Error to listed users. " + e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                System.out.println("Error to close connection to database. " + e.getMessage());
            }
        }
        return clients;
    }

    @Override
    public boolean searchClientById(ClientFit client) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = Conection.getConection();
        String sql = "SELECT * FROM client WHERE id = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, client.getId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastname"));
                client.setSuscription(resultSet.getInt("suscription"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error to get user by id. " + e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (Exception e){
                System.out.println("Error to close connection to database. " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean addClient(ClientFit client) {
        PreparedStatement preparedStatement;
        Connection connection = Conection.getConection();
        String sql = "INSERT INTO client(name, lastname, suscription) "
                + "values(?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getSuscription());
            preparedStatement.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error to add user. " + e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Error to close connection to database. " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean updateClient(ClientFit client) {
        PreparedStatement preparedStatement;
        Connection connection = Conection.getConection();
        String sql = "UPDATE client SET name = ?, lastname = ?, suscription = ? " + " WHERE id = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getSuscription());
            preparedStatement.setInt(4, client.getId());
            preparedStatement.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error to update user. " + e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Error to close connection to database. " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean deleteClient(ClientFit client) {
        PreparedStatement preparedStatement;
        Connection connection = Conection.getConection();
        String sql = "DELETE FROM client WHERE id = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error to delete user from list " + e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Error to close connection to database. " + e.getMessage());
            }
        }
        return false;
    }

}


