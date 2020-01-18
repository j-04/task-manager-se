package ru.dragosh.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.Repository;
import ru.dragosh.tm.entity.Entity;
import ru.dragosh.tm.repository.ProjectRepository;
import ru.dragosh.tm.util.MyBatisUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class AbstractService <E extends Entity, R extends Repository<E>>{

}
