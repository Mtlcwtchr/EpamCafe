package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IClientCommentRepository;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Comment;
import by.epam.mtlcwtchr.ecafe.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientCommentRepository implements IClientCommentRepository {

    @Override
    public List<Comment> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.client_comment", "id","author_name","author_phone","comment", "author_id")
                    .build(connection)){
                return getComments(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Comment> getList(String authorPhone) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.client_comment", "id","author_name","author_phone","comment", "author_id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "author_phone"), LogicConcatenator.AND)
                    .build(connection, Optional.of(authorPhone))){
                return getComments(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Comment> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.client_comment", "id","author_name","author_phone","comment", "author_id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "id"), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getComment(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Comment> find(String authorPhone) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.client_comment", "id","author_name","author_phone","comment", "author_id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "author_phone"), LogicConcatenator.AND)
                    .build(connection, Optional.of(authorPhone))){
                return getComment(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Comment> save(Comment comment) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert("epam_cafe.client_comment", "author_name","author_phone","comment", "author_id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "author_phone"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(comment.getAuthorName()),
                            Optional.of(comment.getAuthorPhone()),
                            Optional.of(comment.getMessage()),
                            Optional.of(comment.getAuthorId()))){
                preparedStatement.execute();
                return getCreated();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private Optional<Comment> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select("epam_cafe.client_comment", "id","author_name","author_phone","comment", "author_id")
                    .whereMaxId("epam_cafe.client_comment")
                    .build(connection)){
                return getComment(preparedStatement);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Comment> update(Comment comment) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update("epam_cafe.client_comment", "id", "author_name","author_phone","comment", "author_id")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "author_phone"), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(comment.getAuthorId()),
                            Optional.of(comment.getAuthorName()),
                            Optional.of(comment.getAuthorPhone()),
                            Optional.of(comment.getMessage()),
                            Optional.of(comment.getAuthorId()))){
                preparedStatement.execute();
                return Optional.of(comment);
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete("epam_cafe.client_comment")
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, "id"), LogicConcatenator.AND)
                    .build(connection,Optional.of(id))){
                    return preparedStatement.execute();
            } catch (SQLException ex) {
                throw new DAOException(ex);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }


    private List<Comment> getComments(PreparedStatement preparedStatement) throws DAOException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return List.of();
            } else{
                ArrayList<Comment> list = new ArrayList<>();
                do{
                    list.add(new Comment(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                } while (resultSet.next());
                return List.copyOf(list);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private Optional<Comment> getComment(PreparedStatement preparedStatement) throws DAOException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else {
                return Optional.of(new Comment(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

}
