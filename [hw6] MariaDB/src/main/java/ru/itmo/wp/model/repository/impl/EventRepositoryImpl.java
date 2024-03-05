package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.EventRepository;

import javax.sql.DataSource;
import java.sql.*;

public class EventRepositoryImpl implements EventRepository {

    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void save(User user, Event.Type type) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `Event` (`userId`, `type`, `creationTime`) VALUES (?, ?, NOW())",
                    Statement.RETURN_GENERATED_KEYS
            )) {
                statement.setString(1, String.valueOf(user.getId()));
                statement.setString(2, String.valueOf(type));

                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't save Event.");
                } else if (!statement.getGeneratedKeys().next()) {
                    throw new RepositoryException("Can't save Event [no autogenerated fields].");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
