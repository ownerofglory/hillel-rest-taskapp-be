package ua.ithillel.hilleltask.di;

import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ua.ithillel.hilleltask.dao.*;
import ua.ithillel.hilleltask.model.mapper.*;
import ua.ithillel.hilleltask.service.*;

import java.sql.Connection;

public class CustomAppBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(new DatabaseConnectionFactory()).to(Connection.class).in(Singleton.class);

        bind(BoardMySqlJdbCDao.class).to(BoardDao.class).in(Singleton.class);
        bind(TaskListMySqlJdbcDao.class).to(TaskListDao.class).in(Singleton.class);
        bind(TaskMySqlJdbcDao.class).to(TaskDao.class).in(Singleton.class);

        bind(BoardMapperImpl.class).to(BoardMapper.class).in(Singleton.class);
        bind(TaskListMapperImpl.class).to(TaskListMapper.class).in(Singleton.class);
        bind(TaskMapperImpl.class).to(TaskMapper.class).in(Singleton.class);

        bind(BoardServiceDefault.class).to(BoardService.class).in(Singleton.class);
        bind(TaskListServiceDefault.class).to(TaskListService.class).in(Singleton.class);
        bind(TaskServiceDefault.class).to(TaskService.class).in(Singleton.class);
    }
}
