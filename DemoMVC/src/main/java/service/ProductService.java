package service;

import configuration.ConnectDatabase;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService<Product> {
    private Connection connection;

    public ProductService() {
        this.connection = ConnectDatabase.getConnection();
    }

    @Override
    public void add(Product product) {
        String sql = "insert into product (name, image, price) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString (1, product.getName());
            preparedStatement.setString (2, product.getImage());
            preparedStatement.setDouble (3, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        String sql = "select * from product";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                Product p = new Product(id, name, price, image);
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void update(int id, Product product) {
        String sql = "update product name = ?, image = ?, price = ? where  id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString (1, product.getName());
                preparedStatement.setString (2, product.getImage());
                preparedStatement.setDouble (3, product.getPrice());
                preparedStatement.setInt (4, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public void delete(int id) {
        String sql = "delete from product where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt (1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int id) {
        String sql = "select * from product where id = ?";
        Product p = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt (1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                p = new Product(id, name, price, image);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

}
