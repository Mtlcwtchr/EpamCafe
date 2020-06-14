package by.epam.mtlcwtchr.ecafe.dao.repository.impl;

import by.epam.mtlcwtchr.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.Limiter;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LimiterMapGenerator;
import by.epam.mtlcwtchr.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.mtlcwtchr.ecafe.dao.exception.DAOException;
import by.epam.mtlcwtchr.ecafe.dao.impl.ConnectionPool;
import by.epam.mtlcwtchr.ecafe.dao.repository.IClientCommentRepository;
import by.epam.mtlcwtchr.ecafe.entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientCommentRepository implements IClientCommentRepository {

    private static final String sourceTableName = "epam_cafe.client_comment";
    private static final String[] selectionColumnNames =
            new String[]{"id","author_name","author_phone","message"};
    private static final String[] insertionColumnNames =
            new String[]{ "author_name","author_phone","message"};
    private static final String[] updatingColumnNames =
            new String[]{"id","author_name","author_phone","message"};
    private static final String idColumnName = "id";
    private static final String authorPhoneColumnName = "phone";

    @Override
    public List<Comment> getList() throws DAOException {
        try(Connection connection = ConnectionPool.CONNECTION_POOL_INSTANCE.retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(sourceTableName, selectionColumnNames)
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
                    .select(sourceTableName, selectionColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, authorPhoneColumnName), LogicConcatenator.AND)
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
                    .select(sourceTableName, selectionColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
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
                    .select(sourceTableName, selectionColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, authorPhoneColumnName), LogicConcatenator.AND)
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
                    .insert(sourceTableName, insertionColumnNames)
                    .build(connection,
                            Optional.of(comment.getAuthorName()),
                            Optional.of(comment.getAuthorPhone()),
                            Optional.of(comment.getMessage()))){
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
                    .select(sourceTableName, selectionColumnNames)
                    .whereMaxId(sourceTableName)
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
                    .update(sourceTableName, updatingColumnNames)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(comment.getId()),
                            Optional.of(comment.getAuthorName()),
                            Optional.of(comment.getAuthorPhone()),
                            Optional.of(comment.getMessage()),
                            Optional.of(comment.getId()))){
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
                    .delete(sourceTableName)
                    .where(LimiterMapGenerator.generateOfSingleType(Limiter.EQUALS, idColumnName), LogicConcatenator.AND)
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
                            resultSet.getString(4)
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
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

}
